package com.madxmike.beaconsplusplus.effects;

import com.madxmike.beaconsplusplus.BeaconsPlusPlus;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tag.Tag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

public class EffectRegistry {

    private static final Map<Tag<Block>, Class<? extends Effect<?>>> REGISTERED_EFFECTS = new HashMap<>();

    @SafeVarargs
    public static void RegisterEffect(Class<? extends Effect<?>> clazz, Tag<Block> ...tags) {
        for (Tag<Block> tag : tags) {
            Class<? extends Effect<?>> current = REGISTERED_EFFECTS.putIfAbsent(tag, clazz);
            if(current != null) {
                BeaconsPlusPlus.LOGGER.info(current.getName() + " was already registered for " + tag.toString() );
                continue;
            }

            BeaconsPlusPlus.LOGGER.info("Registered effect " + clazz.getSimpleName() + " for tag " + tag.toString());
        }
    }

    @Nullable
    public static Effect<?> getEffect(Tag<Block> tag) {
       Class<? extends Effect<?>> clazz = REGISTERED_EFFECTS.get(tag);
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            return null;
        }
    }

    @NotNull
    public static Optional<Tag<Block>> getTag(BlockState blockState) {
        return REGISTERED_EFFECTS.keySet().stream().filter(blockState::isIn).findAny();
    }
}
