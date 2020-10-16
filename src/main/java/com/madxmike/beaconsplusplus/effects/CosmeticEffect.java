package com.madxmike.beaconsplusplus.effects;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;

public class CosmeticEffect extends Effect<Entity> {

    public CosmeticEffect() {
        super(Entity.class);
    }

    @Override
    public void apply(WorldAccess world, BlockPos pos, int level) {
        //NO-OP
    }

    @Override
    public String getName() {
        return "cosmetic";
    }
}
