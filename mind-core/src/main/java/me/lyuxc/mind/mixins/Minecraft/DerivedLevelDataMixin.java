package me.lyuxc.mind.mixins.Minecraft;

import net.minecraft.SharedConstants;
import net.minecraft.world.level.storage.DerivedLevelData;
import net.minecraft.world.level.storage.WorldData;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DerivedLevelData.class)
public abstract class DerivedLevelDataMixin {

    @Shadow @Final private WorldData worldData;

    @Inject(method = "isAllowCommands", at = @At("RETURN"), cancellable = true, remap = false)
    public void isAllowCommands(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue((this.worldData.isAllowCommands()) || SharedConstants.IS_RUNNING_IN_IDE);
    }
}
