package com.madxmike.beaconsplusplus.effects;

import com.madxmike.beaconsplusplus.BeaconsPlusPlus;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

public class EffectRegistry {

    private static final Map<Tag<Block>, Effect<?>> REGISTERED_EFFECTS = new HashMap<>();

    public static void register(Effect<?> effect, Tag<Block> tag) {
        Effect<?> current = REGISTERED_EFFECTS.putIfAbsent(tag, effect);
        if(current != null) {
            BeaconsPlusPlus.LOGGER.info("Beacon effect " + effect.getName() + " was already registered");
            return;
        }
        BeaconsPlusPlus.LOGGER.info("Registered beacon effect " + effect.getName());
    }

    public static void register(Effect<?> effect, String namespace, String tagName) {
        register(effect, TagRegistry.block(new Identifier(namespace, tagName)));
    }

    public static void register(Effect<?> effect, String namespace) {
        register(effect, namespace, "beacon_" + effect.getName().toLowerCase().replaceAll(" ", "_"));
    }

    public static void register(Effect<?> effect) {
        register(effect, BeaconsPlusPlus.MOD_ID);
    }

    @Nullable
    public static Effect<?> getEffect(Tag<Block> tag) {
       return REGISTERED_EFFECTS.get(tag);
    }

    @NotNull
    public static Optional<Tag<Block>> getTag(BlockState blockState) {
         return REGISTERED_EFFECTS.keySet().stream()
                 .filter(blockState::isIn)
                 .findAny();
    }
}
