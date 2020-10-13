package com.madxmike.beaconsplusplus.effects;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;

import java.util.List;

public class HeroEffect implements Effect<PlayerEntity> {

    @Override
    public String getName() {
        return "Hero";
    }

    @Override
    public void apply(WorldAccess world, BlockPos pos, int level) {
        List<PlayerEntity> players = this.getEntitiesWithinRange(world, PlayerEntity.class, pos, level);
        players.forEach(playerEntity -> {
            playerEntity.applyStatusEffect(new StatusEffectInstance(StatusEffects.HERO_OF_THE_VILLAGE, getDuration(level), getAmplification(level), true, true));
        });
    }
}
