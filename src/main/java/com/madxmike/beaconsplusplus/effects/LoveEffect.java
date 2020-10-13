package com.madxmike.beaconsplusplus.effects;

import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;

import java.util.List;

public class LoveEffect implements Effect<AnimalEntity> {
    @Override
    public String getName() {
        return "Love";
    }

    @Override
    public void apply(WorldAccess world, BlockPos pos, int level) {
        if(world.isClient()) {
            return;
        }
        List<AnimalEntity> animals = this.getEntitiesWithinRange(world, AnimalEntity.class, pos, level);

        animals.stream().filter(AnimalEntity::canEat).forEach(animalEntity -> animalEntity.lovePlayer(null));

    }
}
