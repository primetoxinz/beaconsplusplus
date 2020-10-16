package com.madxmike.beaconsplusplus;

import com.madxmike.beaconsplusplus.effects.*;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class BeaconsPlusPlus implements ModInitializer {

    public static final String MOD_ID = "beaconsplusplus";
    public static final Logger LOGGER = LogManager.getLogger("beaconsplusplus");
    public static BlockEntityType<BeaconBlockEntity> BEACON_BLOCK_ENTITY;

    @Override
    public void onInitialize() {
        BEACON_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, MOD_ID, BlockEntityType.Builder.create(BeaconBlockEntity::new, Blocks.BEACON).build(null));
        System.out.println("Hello Fabric world!");


        EffectRegistry.register(new PotionEffect<>(StatusEffects.HASTE, PlayerEntity.class));
        EffectRegistry.register(new PotionEffect<>(StatusEffects.GLOWING, HostileEntity.class));
        EffectRegistry.register(new PotionEffect<>(StatusEffects.SPEED, PlayerEntity.class));
        EffectRegistry.register(new PotionEffect<>(StatusEffects.STRENGTH, PlayerEntity.class));
        EffectRegistry.register(new PotionEffect<>(StatusEffects.JUMP_BOOST, PlayerEntity.class));
        EffectRegistry.register(new PotionEffect<>(StatusEffects.REGENERATION, PlayerEntity.class));
        EffectRegistry.register(new PotionEffect<>(StatusEffects.RESISTANCE, PlayerEntity.class));
        EffectRegistry.register(new PotionEffect<>(StatusEffects.FIRE_RESISTANCE, PlayerEntity.class));
        EffectRegistry.register(new PotionEffect<>(StatusEffects.WATER_BREATHING, PlayerEntity.class));
        EffectRegistry.register(new PotionEffect<>(StatusEffects.INVISIBILITY, PlayerEntity.class));
        EffectRegistry.register(new PotionEffect<>(StatusEffects.NIGHT_VISION, PlayerEntity.class));
        EffectRegistry.register(new PotionEffect<>(StatusEffects.HEALTH_BOOST, PlayerEntity.class));
        EffectRegistry.register(new PotionEffect<>(StatusEffects.ABSORPTION, PlayerEntity.class));
        EffectRegistry.register(new PotionEffect<>(StatusEffects.SATURATION, PlayerEntity.class));
        EffectRegistry.register(new PotionEffect<>(StatusEffects.GLOWING, PlayerEntity.class));
        EffectRegistry.register(new PotionEffect<>(StatusEffects.LEVITATION, PlayerEntity.class));
        EffectRegistry.register(new PotionEffect<>(StatusEffects.SLOW_FALLING, PlayerEntity.class));
        EffectRegistry.register(new PotionEffect<>(StatusEffects.HERO_OF_THE_VILLAGE, PlayerEntity.class));

        EffectRegistry.register(new CosmeticEffect());
    }
}
