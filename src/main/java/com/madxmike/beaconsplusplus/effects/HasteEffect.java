package com.madxmike.beaconsplusplus.effects;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectUtil;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;

import java.util.List;

public class HasteEffect implements Effect<PlayerEntity> {

    @Override
    public String getName() {
        return "Haste";
    }

    @Override
    public void apply(WorldAccess world, BlockPos pos, int level) {
        if(world.isClient()) {
            return;
        }

        List<PlayerEntity> players = this.getEntitiesWithinRange(world, PlayerEntity.class, pos, level);
        players.forEach(playerEntity -> {
            playerEntity.applyStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, getDuration(level), getAmplification(level), true, true));
        });
    }
}
