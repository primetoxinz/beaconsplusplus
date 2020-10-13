package com.madxmike.beaconsplusplus.effects;

import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import java.util.List;
import java.util.Optional;

public class ExperienceEffect implements Effect<ExperienceOrbEntity> {

    @Override
    public String getName() {
        return "Experience";
    }

    @Override
    public void apply(WorldAccess world, BlockPos pos, int level) {
        List<ExperienceOrbEntity> xpOrbs = this.getEntitiesWithinRange(world, ExperienceOrbEntity.class, pos, level);

        int totalExperienceCollected = 0;
        for (ExperienceOrbEntity xpOrb : xpOrbs) {
            totalExperienceCollected += xpOrb.getExperienceAmount();
            xpOrb.remove();
        }

        ExperienceOrbEntity collectionOrb = new ExperienceOrbEntity((World) world, pos.getX() + 0.5D, pos.getY() + 1, pos.getZ() + 0.5D, totalExperienceCollected);
        collectionOrb.setVelocity(0, 0, 0);
        world.spawnEntity(collectionOrb);
    }
}
