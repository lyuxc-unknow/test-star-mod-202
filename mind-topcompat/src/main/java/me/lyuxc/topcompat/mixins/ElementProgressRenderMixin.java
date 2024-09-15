package me.lyuxc.topcompat.mixins;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import mcjty.theoneprobe.TheOneProbe;
import mcjty.theoneprobe.api.IProgressStyle;
import mcjty.theoneprobe.apiimpl.client.ElementProgressRender;
import mcjty.theoneprobe.apiimpl.elements.ElementProgress;
import mcjty.theoneprobe.rendering.RenderHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ElementProgressRender.class)
public class ElementProgressRenderMixin {
    @Unique
    private static final ResourceLocation testStarMod_1_20_6_neoforge$ICONS = ResourceLocation.fromNamespaceAndPath(TheOneProbe.MODID, "textures/gui/vanliia_icon.png");

    /**
     * @author lyuxc_
     * @reason 修复:图标未找到[图标路径与原来不一致]
     */
    @Overwrite
    private static void renderArmorBar(long current, GuiGraphics graphics, int x, int y, int w, int h) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, testStarMod_1_20_6_neoforge$ICONS);
        PoseStack matrixStack = graphics.pose();
        Matrix4f matrix = matrixStack.last().pose();
        if (current * 4 >= w) {
            RenderHelper.drawTexturedModalRect(matrix, x, y, 31, 14, 9, 9);
            RenderHelper.renderText(Minecraft.getInstance(), graphics, x + 12, y, ChatFormatting.WHITE + String.valueOf((current / 2)));
        } else {
            for (int i = 0; i < current / 2; i++) {
                RenderHelper.drawTexturedModalRect(matrix, x, y, 31, 14, 9, 9);
                x += 8;
            }
            if (current % 2 != 0) {
                RenderHelper.drawTexturedModalRect(matrix, x, y, 31, 23, 9, 9);
            }
        }
    }

    /**
     * @author lyuxc_
     * @reason 修复:图标未找到[图标路径与原来不一致]
     */
    @Overwrite
    private static void renderLifeBar(long current, GuiGraphics graphics, int x, int y, int w, int h) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, testStarMod_1_20_6_neoforge$ICONS);
        PoseStack matrixStack = graphics.pose();
        Matrix4f matrix = matrixStack.last().pose();
        if (current * 4 >= w) {
            RenderHelper.drawTexturedModalRect(matrix, x, y, 31, 0, 7, 7);
            RenderHelper.renderText(Minecraft.getInstance(), graphics, x + 12, y, ChatFormatting.WHITE + String.valueOf((current / 2)));
        } else {
            for (int i = 0; i < current / 2; i++) {
                RenderHelper.drawTexturedModalRect(matrix, x, y, 31, 0, 7, 7);
                x += 8;
            }
            if (current % 2 != 0) {
                RenderHelper.drawTexturedModalRect(matrix, x, y, 31, 7, 7, 7);
            }
        }
    }

    /**
     * @author lyuxc_
     * @reason 自用方法(应该没人会想不显示文字后还自己加文字吧)
     */
    @Overwrite
    private static void renderText(GuiGraphics graphics, int x, int y, int w, long current, IProgressStyle style) {
        Minecraft mc = Minecraft.getInstance();
        Font render = mc.font;
        if (style.isShowText()) {
            Component s = style.getPrefixComp().copy().append(ElementProgress.format(current, style.getNumberFormat(), style.getSuffixComp()));
            testStarMod$renderText(graphics, x, y, w, style, mc, render, s);
        } else {
            Component s = style.getPrefixComp();
            testStarMod$renderText(graphics, x, y, w, style, mc, render, s);
        }
    }

    @Unique
    private static void testStarMod$renderText(GuiGraphics graphics, int x, int y, int w, IProgressStyle style, Minecraft mc, Font render, Component s) {
        int textWidth = render.width(s.getVisualOrderText());
        switch (style.getAlignment()) {
            case ALIGN_BOTTOMRIGHT -> RenderHelper.renderText(mc, graphics, x + w - 3 - textWidth, y + 2, s);
            case ALIGN_CENTER -> RenderHelper.renderText(mc, graphics, x + w / 2 - textWidth / 2, y + 2, s);
            case ALIGN_TOPLEFT -> RenderHelper.renderText(mc, graphics, x + 3, y + 2, s);
        }
    }
}
