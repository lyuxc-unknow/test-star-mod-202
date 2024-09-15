package me.lyuxc.mind.mixins.Minecraft;

import net.minecraft.SharedConstants;
import net.minecraft.world.level.LevelSettings;
import net.minecraft.world.level.storage.PrimaryLevelData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PrimaryLevelData.class)
public abstract class PrimaryLevelDataMixin {
    @Shadow private LevelSettings settings;

    @Inject(method = "isAllowCommands", at = @At("RETURN"), cancellable = true, remap = false)
    public void getAllowCommands$mixin(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(this.settings.allowCommands() || SharedConstants.IS_RUNNING_IN_IDE);
    }
}
