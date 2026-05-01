package oxded.creativetools.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.ryanhcode.sable.physics.floating_block.FloatingBlockCluster;
import dev.ryanhcode.sable.physics.floating_block.FloatingBlockMaterial;
import dev.ryanhcode.sable.physics.floating_block.FloatingClusterContainer;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import net.minecraft.core.BlockPos;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3d;
import org.joml.Vector3dc;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import oxded.creativetools.CreativeTools;
import oxded.creativetools.FloatingMaterialSupplier;
import oxded.creativetools.mixinterfaces.FloatingBlockAdder;

import java.util.Map;

@Mixin(FloatingClusterContainer.class)
public class ClusterMixin implements FloatingBlockAdder {
	@Unique
	public final Long2ObjectMap<FloatingMaterialSupplier> creativeLevitite$addedBlocks = new Long2ObjectOpenHashMap<>();
	@Unique
	public final Long2ObjectMap<FloatingMaterialSupplier> creativeLevitite$removedBlocks = new Long2ObjectOpenHashMap<>();

	@ModifyReturnValue(method = "needsTicking", at = @At("RETURN"))
	private boolean needsTicking(boolean bool) {
		return bool || !creativeLevitite$addedBlocks.isEmpty();
	}
	@Inject(method = "processBlockChanges", at = @At("HEAD"))
	private void atHead(Vector3dc centerOfMass, CallbackInfo ci) {
		this.creativeLevitite$removedBlocks.forEach((key, value) -> {
			final BlockPos blockPos = BlockPos.of(key);
			final Vector3d pos = new Vector3d(blockPos.getX() + 0.5, blockPos.getY() + 0.5, blockPos.getZ() + 0.5).sub(centerOfMass);
			this.creativeLevitite$removeFloatingBlock(value.getMaterial(), value.getScale(), pos);
		});
	}
	@Inject(method = "processBlockChanges", at = @At("TAIL"))
	private void atTail(Vector3dc centerOfMass, CallbackInfo ci) {
		this.creativeLevitite$addedBlocks.forEach((key, value) -> {
			final BlockPos blockPos = BlockPos.of(key);
			final Vector3d pos = new Vector3d(blockPos.getX() + 0.5, blockPos.getY() + 0.5, blockPos.getZ() + 0.5).sub(centerOfMass);
			this.creativeLevitite$addFloatingBlock(value.getMaterial(), value.getScale(), pos);
		});

		this.creativeLevitite$addedBlocks.clear();
		this.creativeLevitite$removedBlocks.clear();
	}
	@Unique
	public void creativeLevitite$addFloatingBlock(@NotNull FloatingBlockMaterial material, double scale, Vector3d pos) {
		FloatingClusterContainer self = (FloatingClusterContainer)(Object)this;
		FloatingBlockCluster foundCluster = null;
		for (final FloatingBlockCluster cluster : self.clusters) {
			if (cluster.getMaterial().equals(material)) {
				foundCluster = cluster;
				break;
			}
		}
		if (foundCluster == null) {
			foundCluster = new FloatingBlockCluster(material);
			self.clusters.add(foundCluster);
		}
		foundCluster.getBlockData().addFloatingBlock(pos, scale);
	}
	@Unique
	public void creativeLevitite$removeFloatingBlock(@NotNull FloatingBlockMaterial material, double scale, Vector3d pos) {
		FloatingClusterContainer self = (FloatingClusterContainer)(Object)this;
		FloatingBlockCluster foundCluster = null;
		for (final FloatingBlockCluster cluster : self.clusters) {
			if (cluster.getMaterial().equals(material)) {
				foundCluster = cluster;
				break;
			}
		}
		if (foundCluster != null) {
			foundCluster.getBlockData().removeFloatingBlock(pos, scale);
			if (((BlockCountAccessor)foundCluster.getBlockData()).getBlockCount() == 0)
				self.clusters.remove(foundCluster);
		}
	}
	@Unique
	public void creativeLevitite$queueAddFloatingBlock(@NotNull final FloatingMaterialSupplier supplier, final BlockPos pos) {
		final long longKey = pos.asLong();
		if(!this.creativeLevitite$removedBlocks.remove(longKey,supplier)) {
			this.creativeLevitite$addedBlocks.put(longKey, supplier);
		}
	}

	@Unique
	public void creativeLevitite$queueRemoveFloatingBlock(@NotNull final FloatingMaterialSupplier supplier, final BlockPos pos) {
		final long longKey = pos.asLong();
		if(!this.creativeLevitite$addedBlocks.remove(longKey,supplier)) {
			this.creativeLevitite$removedBlocks.put(longKey, supplier);
		}
	}
}
