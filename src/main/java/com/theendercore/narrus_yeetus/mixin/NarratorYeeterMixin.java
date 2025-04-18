package com.theendercore.narrus_yeetus.mixin;

import com.mojang.text2speech.Narrator;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Narrator.class)
public interface NarratorYeeterMixin {
    @Inject(at = @At("HEAD"), method = "getNarrator", cancellable = true, remap = false)
    private static void getNarrator(@NotNull CallbackInfoReturnable<Narrator> cir) {

        Narrator.LOGGER.info("NarrusYeetus has disabled the narrator!");
        cir.setReturnValue(new Narrator() {
            @Override public void say(String msg, boolean interrupt, float pitch) {}
            @Override public void clear() {}
            @Override public boolean active() {return false;}
            @Override public void destroy() {}
        });
    }
}
