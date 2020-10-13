package com.madxmike.beaconsplusplus.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;

import java.util.List;

public class SubdueEffect implements Effect<HostileEntity> {

    public static final SubdueStatusEffect SUBDUE = new SubdueStatusEffect();
    private static final double SUBDUE_CHANCE = 0.25D;

    private HeroEffect heroEffect;

    public SubdueEffect() {
        this.heroEffect = new HeroEffect();
    }

    @Override
    public String getName() {
        return "Subdue";
    }

    @Override
    public int getDuration(int level) {
        return 4 * 20;
    }

    @Override
    public void apply(WorldAccess world, BlockPos pos, int level) {
        List<HostileEntity> hostileEntities = this.getEntitiesWithinRange(world, HostileEntity.class, pos, level);
        hostileEntities.forEach(hostileEntity -> {
            if(Math.random() <= SUBDUE_CHANCE * level) {
                hostileEntity.applyStatusEffect(new StatusEffectInstance(SUBDUE, getDuration(level), 0, true, true));
            }
        });

        if(level >= 4) {
            this.heroEffect.apply(world, pos, level);
        }
    }


    public static class SubdueStatusEffect extends StatusEffect {
        protected SubdueStatusEffect() {
            super(StatusEffectType.NEUTRAL, 0xFFC0CB);
        }

        @Override
        public void applyUpdateEffect(LivingEntity entity, int amplifier) {
            MobEntity mob = (MobEntity) entity;
            mob.setAttacking(null);
            mob.setTarget(null);
        }

        @Override
        public boolean canApplyUpdateEffect(int duration, int amplifier) {
            return true;
        }
    }
}
