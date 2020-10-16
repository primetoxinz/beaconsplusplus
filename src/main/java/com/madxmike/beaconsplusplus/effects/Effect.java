package com.madxmike.beaconsplusplus.effects;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.WorldAccess;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public abstract class Effect<T extends Entity> {

    private Function<Integer, Integer> range = (level) -> level * 10 + 10;
    private Function<Integer, Integer> ampliflication = (level) -> level >= 4 ? 1 : 0;
    private Function<Integer, Integer> duration = (level) -> (9 + level * 2) * 20;

    private Class<T> applyTo;

    public Effect(Class<T> applyTo) {
        this.applyTo = applyTo;
    }

    public final List<T> getEntitiesWithinRange(WorldAccess world, BlockPos pos, int level) {

        if(world.isClient()) {
            return new ArrayList<>();
        }

        Box box = new Box(pos);
        box = box.expand(range.apply(level));
        box = box.stretch(0.0D, world.getHeight(), 0.0D);

        return world.getNonSpectatingEntities(applyTo, box);
    }

    public abstract void apply(WorldAccess world, BlockPos pos, int level);
    public abstract String getName();

    public Effect setRange(Function<Integer, Integer> range) {
        this.range = range;
        return this;
    }

    public int getRange(int level) {
        return this.range.apply(level);
    }

    public Effect setAmpliflication(Function<Integer, Integer> ampliflication) {
        this.ampliflication = ampliflication;
        return this;
    }

    public int getAmplification(int level) {
        return this.ampliflication.apply(level);
    }

    public Effect setDuration(Function<Integer, Integer> duration) {
        this.duration = duration;
        return this;
    }

    public int getDuration(int level) {
        return this.duration.apply(level);
    }
}
