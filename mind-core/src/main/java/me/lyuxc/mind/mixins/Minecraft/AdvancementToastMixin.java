package me.lyuxc.mind.mixins.Minecraft;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.toasts.AdvancementToast;
import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.client.gui.components.toasts.ToastComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AdvancementToast.class)
public class AdvancementToastMixin {
    @Inject(method = "render",at = @At("RETURN"), cancellable = true)
    public void render$mixin(GuiGraphics pGuiGraphics, ToastComponent pToastComponent, long pTimeSinceLastVisible, CallbackInfoReturnable<Toast.Visibility> cir) {
        cir.setReturnValue(Toast.Visibility.HIDE);
    }
}
