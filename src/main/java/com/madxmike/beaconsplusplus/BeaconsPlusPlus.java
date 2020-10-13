package com.madxmike.beaconsplusplus;

import com.madxmike.beaconsplusplus.effects.EffectRegistry;
import com.madxmike.beaconsplusplus.effects.HasteEffect;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class BeaconsPlusPlus implements ModInitializer {

    public static final Logger LOGGER = LogManager.getLogger("beaconsplusplus");
    public static BlockEntityType<BeaconBlockEntity> BEACON_BLOCK_ENTITY;

    @Override
    public void onInitialize() {
        BEACON_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "beaconsplusplus", BlockEntityType.Builder.create(BeaconBlockEntity::new, Blocks.BEACON).build(null));
        System.out.println("Hello Fabric world!");

        EffectRegistry.RegisterEffect(HasteEffect.class, TagRegistry.block(new Identifier("minecraft", "beacon_base_blocks")));
    }
}
