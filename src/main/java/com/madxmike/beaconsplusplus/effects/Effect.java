package com.madxmike.beaconsplusplus.effects;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.WorldAccess;

import java.util.ArrayList;
import java.util.List;

public interface Effect<T extends Entity> {

    String getName();

    void apply(WorldAccess world, BlockPos pos, int level);

    default double getRange(int level) {
        return level * 10 + 10;
    }

    default int getDuration(int level) {
        return (9 + level * 2) * 20;
    }

    default int getAmplification(int level) {
        return level >= 4 ? 1 : 0;
    }

    default List<T> getEntitiesWithinRange(WorldAccess world, Class<T> entityType, BlockPos pos, int level) {

        if(world.isClient()) {
            return new ArrayList<>();
        }

        Box box = new Box(pos);
        box = box.expand(getRange(level));
        box = box.stretch(0.0D, world.getHeight(), 0.0D);

        return world.getNonSpectatingEntities(entityType, box);
    }


}
