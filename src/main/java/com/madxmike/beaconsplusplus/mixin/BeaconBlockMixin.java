package com.madxmike.beaconsplusplus.mixin;

import com.madxmike.beaconsplusplus.BeaconBlockEntity;
import net.minecraft.block.BeaconBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.OptionalInt;

@Mixin(BeaconBlock.class)
public class BeaconBlockMixin {

    @Inject(at = @At("RETURN"), cancellable = true, method = "createBlockEntity(Lnet/minecraft/world/BlockView;)Lnet/minecraft/block/entity/BlockEntity;")
    public void replaceBlockEntity(CallbackInfoReturnable<BlockEntity> cir) {
        cir.setReturnValue(new BeaconBlockEntity());
    }

    @Redirect(method = "onUse",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;openHandledScreen(Lnet/minecraft/screen/NamedScreenHandlerFactory;)Ljava/util/OptionalInt;"))
    public OptionalInt replaceOpenHandledScreen(PlayerEntity invoked, @Nullable NamedScreenHandlerFactory factory) {
        return OptionalInt.empty();
    }

}
