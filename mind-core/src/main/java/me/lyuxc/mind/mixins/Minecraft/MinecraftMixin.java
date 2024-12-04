package me.lyuxc.mind.mixins.Minecraft;

import me.lyuxc.mind.Variables;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {

    /**
     * @author lyuxc_
     * @reason Change Title
     */
    @Overwrite
    private String createTitle() {
        StringBuilder sb = new StringBuilder();
        Minecraft mc = Minecraft.getInstance();
        sb.append(I18n.get("ts.tips.modpack_name"));
        sb.append(Variables.title.isEmpty()?I18n.get("ts.tips.user_title"):Variables.title);
        return sb.toString();
    }
}
