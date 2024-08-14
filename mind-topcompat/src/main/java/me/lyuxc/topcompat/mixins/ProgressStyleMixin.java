package me.lyuxc.topcompat.mixins;

import mcjty.theoneprobe.api.ElementAlignment;
import mcjty.theoneprobe.api.NumberFormat;
import mcjty.theoneprobe.apiimpl.styles.ProgressStyle;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

@Mixin(ProgressStyle.class)
public class ProgressStyleMixin {
    @Shadow private ElementAlignment alignment;

    @Shadow private Component prefix;

    @Shadow private Component suffix;

    @Shadow private NumberFormat numberFormat;

    @Shadow private int backgroundColor;

    @Shadow private int borderColor;

    @Inject(method = "<init>",at = @At("RETURN"))
    private void ProgressStyle(CallbackInfo ci) {
        this.alignment = ElementAlignment.ALIGN_TOPLEFT;
        this.prefix = Component.empty();
        this.suffix = Component.empty();
        this.numberFormat = NumberFormat.FULL;
        this.backgroundColor = 0xFFFFFF;
        this.borderColor = Color.GRAY.getRGB();
    }
}
