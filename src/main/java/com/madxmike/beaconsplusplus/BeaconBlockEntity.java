package com.madxmike.beaconsplusplus;

import com.madxmike.beaconsplusplus.effects.Effect;
import com.madxmike.beaconsplusplus.effects.EffectRegistry;
import com.madxmike.beaconsplusplus.mixin.BeaconBlockEntityAccessor;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tag.Tag;
import net.minecraft.util.math.BlockPos;

import java.util.Optional;

public class BeaconBlockEntity extends net.minecraft.block.entity.BeaconBlockEntity {

    private Tag<Block> currentTag;

    private void determineTag() {
        this.currentTag = null;
        if(this.getWorld() == null) {
            return;
        }

        BlockState down = this.getWorld().getBlockState(this.pos.down());
        Optional<Tag<Block>> foundTag = EffectRegistry.getTag(down);
        if(!foundTag.isPresent()) {
            return;
        }
        this.currentTag = foundTag.get();
    }

    @Override
    protected void updateLevel(int x, int y, int z) {
        determineTag();
        if(this.currentTag == null) {
            return;
        }

        int level = 0;
        for(int i = 1; i <= 4; level = i++) {
            int j = y - i;
            if (j < 0) {
                break;
            }

            boolean bl = true;

            for(int k = x - i; k <= x + i && bl; ++k) {
                for(int l = z - i; l <= z + i; ++l) {
                    if (!this.world.getBlockState(new BlockPos(k, j, l)).isIn(this.currentTag)) {
                        bl = false;
                        break;
                    }
                }
            }

            if (!bl) {
                break;
            }
        }


        ((BeaconBlockEntityAccessor) this).setLevel(level);
    }

    @Override
    protected void applyPlayerEffects() {
        Effect<?> currentEffect = EffectRegistry.getEffect(this.currentTag);

        if(currentEffect != null) {
            currentEffect.apply(this.world, this.pos, this.getLevel());
        }
    }
}
