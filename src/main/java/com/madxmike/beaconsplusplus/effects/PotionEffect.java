package com.madxmike.beaconsplusplus.effects;

import com.madxmike.beaconsplusplus.effects.Effect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;

public class PotionEffect<T extends LivingEntity> extends Effect<T> {

    private StatusEffect statusEffect;

    public PotionEffect(StatusEffect statusEffect, Class<T> applyTo) {
        super(applyTo);
        this.statusEffect = statusEffect;
    }

    @Override
    public void apply(WorldAccess world, BlockPos pos, int level) {
        this.getEntitiesWithinRange(world, pos, level).forEach(entity -> {
            entity.applyStatusEffect(new StatusEffectInstance(this.statusEffect, getDuration(level), getAmplification(level), true, true));
        });
    }

    @Override
    public String getName() {
        return statusEffect.getName().getString();
    }

}
