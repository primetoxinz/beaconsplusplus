package com.madxmike.beaconsplusplus.effects;

import com.sun.org.apache.regexp.internal.RE;
import net.minecraft.block.SlimeBlock;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;

import java.util.List;
import java.util.Random;

public class SaturationEffect implements Effect<PlayerEntity> {

    private static final Random random = new Random();

    @Override
    public String getName() {
        return "Saturation";
    }

    @Override
    public int getDuration(int level) {
        // Roll [0, 100/level], if its 1 then apply effect for 1 tick
        return random.nextInt(48 / level) == 1 ? 1 : 0;
    }

    @Override
    public void apply(WorldAccess world, BlockPos pos, int level) {
        List<PlayerEntity> players = this.getEntitiesWithinRange(world, PlayerEntity.class, pos, level);
        players.forEach(playerEntity -> {
            playerEntity.applyStatusEffect(new StatusEffectInstance(StatusEffects.SATURATION, getDuration(level), getAmplification(level), true, true));
        });
    }
}
