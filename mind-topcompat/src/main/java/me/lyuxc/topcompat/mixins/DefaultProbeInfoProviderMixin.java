package me.lyuxc.topcompat.mixins;

import mcjty.theoneprobe.api.*;
import mcjty.theoneprobe.apiimpl.ProbeConfig;
import mcjty.theoneprobe.apiimpl.elements.ElementProgress;
import mcjty.theoneprobe.apiimpl.providers.DefaultProbeInfoProvider;
import mcjty.theoneprobe.config.Config;
import me.lyuxc.topcompat.theoneprobe.TextFixProgressStyle;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.FluidStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Objects;

@Mixin(DefaultProbeInfoProvider.class)
public class DefaultProbeInfoProviderMixin {
    /**
     * @author mcjty,lyuxc
     * @reason 流体进度条背景透明
     */
    @Overwrite
    private void addFluidInfo(IProbeInfo probeInfo, ProbeConfig config, FluidStack fluidStack, int maxContents) {
        int contents = fluidStack.getAmount();
        if (config.getTankMode() == 1) {
            int tintColor = IClientFluidTypeExtensions.of(fluidStack.getFluid()).getTintColor(fluidStack);
            Color color = new Color(tintColor);
            if (Objects.equals(fluidStack.getFluid(), Fluids.LAVA)) {
                color = new Color(255, 139, 27);
            }

            MutableComponent text = Component.literal("");
            text.append(ElementProgress.format(contents, Config.tankFormat.get(), Component.literal("mB")));
            text.append("/");
            text.append(ElementProgress.format(maxContents, Config.tankFormat.get(), Component.literal("mB")));
            probeInfo.tankSimple(maxContents, fluidStack, new TextFixProgressStyle()
                    .fixProgress(fluidStack.getHoverName().getString()+":"+text.getString())
                    .numberFormat(NumberFormat.NONE)
                    .borderlessColor(color.getRGB(), 0xFFFFFF));
        } else {
            if (!fluidStack.isEmpty()) {
                probeInfo.text(CompoundText.create().style(TextStyleClass.NAME).text("Liquid:").info(fluidStack.getHoverName()));
            }

            if (config.getTankMode() == 2) {
                probeInfo.progress(contents, maxContents, probeInfo.defaultProgressStyle()
                        .suffix("mB")
                        .filledColor(Config.tankbarFilledColor)
                        .alternateFilledColor(Config.tankbarAlternateFilledColor)
                        .borderColor(Config.tankbarBorderColor)
                        .numberFormat(Config.tankFormat.get()));
            } else {
                probeInfo.text(CompoundText.create().style(TextStyleClass.PROGRESS).text(ElementProgress.format(contents, Config.tankFormat.get(), Component.literal("mB"))));
            }
        }

    }
}
