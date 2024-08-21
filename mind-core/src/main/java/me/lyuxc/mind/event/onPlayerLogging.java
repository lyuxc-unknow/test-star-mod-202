package me.lyuxc.mind.event;

import me.lyuxc.mind.Variables;
import me.lyuxc.mind.utils.I18N;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;


@EventBusSubscriber(modid = Variables.MOD_ID)
public class onPlayerLogging {
    @SubscribeEvent
    public static void onLogging(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        //开发者标签添加
        if(player.getName().getString().equals(Variables.DEVELOPER_NAME) && player.getStringUUID().equals(Variables.DEVELOPER_UUID)) {
            Variables.DEVELOPER = true;
            Variables.title = "Mind2-开发";
        }
        player.sendSystemMessage(I18N.getComponent("ts.tips.modpack"));
//        player.sendSystemMessage(Utils.textToOpenLinks("点我打开链接","https://www.bilibili.com/video/BV1nz421i7AF?vd_source=9470c41d2b30479fe22e371031d2da96"));
    }
}
