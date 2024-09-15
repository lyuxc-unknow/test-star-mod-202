package me.lyuxc.mind.event;

import me.lyuxc.mind.Tiers;
import me.lyuxc.mind.Variables;
import me.lyuxc.mind.item.tools.AllOurposeTool;
import me.lyuxc.mind.item.tools.MySword;
import me.lyuxc.mind.item.tools.TetanusBlade;
import me.lyuxc.mind.utils.TextUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;


@EventBusSubscriber(modid = Variables.MOD_ID, value = Dist.CLIENT)
public class onItemToolTip {
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onToolTip(ItemTooltipEvent event) {
        if (event.getItemStack().getItem() instanceof MySword) {
            for (int x = 0; x < event.getToolTip().size(); x++) {
                if (event.getToolTip().get(x).contains(Component.translatable("attribute.name.generic.attack_damage"))) {
                    event.getToolTip().set(x,
                            Component.literal(" ").withStyle(ChatFormatting.BLUE)
                                    .append(TextUtils.apply(Component.translatable("ts.attribute.damage")))
                                    .append(" ")
                                    .withStyle(ChatFormatting.DARK_GREEN));
                }
            }
        }
        if (event.getItemStack().getItem() instanceof AllOurposeTool allOurposeTool) {
            if(allOurposeTool.getTier() == Tiers.LEVEL_INF) {
                for (int x = 0; x < event.getToolTip().size(); x++) {
                    if (event.getToolTip().get(x).contains(Component.translatable("attribute.name.generic.attack_damage"))) {
                        event.getToolTip().set(x,
                                Component.literal(" ").withStyle(ChatFormatting.BLUE)
                                        .append(TextUtils.apply(Component.translatable("ts.attribute.damage")))
                                        .append(" ")
                                        .withStyle(ChatFormatting.DARK_GREEN));
                    }
                }
            }
        }
        if (event.getItemStack().getItem() instanceof TetanusBlade) {
            for (int x = 0; x < event.getToolTip().size(); x++) {
                if (event.getToolTip().get(x).contains(Component.translatable("attribute.name.generic.attack_damage"))) {
                    event.getToolTip().set(x,
                            Component.literal(" ").withStyle(ChatFormatting.BLUE)
                                .append(TextUtils.apply(Component.translatable("ts.attribute.damage_tetanus_blade")))
                                .append(" ")
                                .append(Component.translatable("attribute.name.generic.attack_damage"))
                                .withStyle(ChatFormatting.DARK_GREEN));
                }
            }
        }
    }
}
