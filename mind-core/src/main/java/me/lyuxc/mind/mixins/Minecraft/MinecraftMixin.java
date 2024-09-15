package me.lyuxc.mind.mixins.Minecraft;

import me.lyuxc.mind.Variables;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.I18n;
import net.neoforged.fml.ModList;
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
        sb.append(I18n.get("ts.tips.modpack_name"));
        sb.append(Variables.title==null?I18n.get("ts.tips.user_title"):Variables.title);
        if (ModList.get() != null) {
            sb.append("|");
            sb.append(I18n.get("ts.tips.mods")).append(ModList.get().size());
        }
        return sb.toString();
    }
}
