package com.theendercore.narrus_yeetus.mixin;

import net.minecraft.client.gui.screen.option.AccessibilityOptionsScreen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;

import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;

@Mixin(AccessibilityOptionsScreen.class)
public class AccessibilityOptionsScreenMixin {

    @Inject(
            method = "getOptions",
            at = @At("RETURN"),
            cancellable = true
    )
    private static void removeNarrator(GameOptions gameOptions, CallbackInfoReturnable<SimpleOption<?>[]> cir) {
        SimpleOption<?>[] original = cir.getReturnValue();

        // Filter out the narrator option
        SimpleOption<?>[] filtered = Arrays.stream(original)
                .filter(option -> option != gameOptions.getNarrator())
                .toArray(SimpleOption[]::new);

        cir.setReturnValue(filtered);
    }
}


