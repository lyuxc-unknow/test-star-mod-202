package me.lyuxc.mind.event;

import me.lyuxc.mind.Variables;
import me.lyuxc.mind.utils.I18N;
import me.lyuxc.mind.utils.Utils;
import net.minecraft.server.level.ServerLevel;
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
        if (event.getEntity().level() instanceof ServerLevel serverLevel) {
            int playerCount = serverLevel.getServer().getPlayerList().getPlayers().size();
            if (playerCount == 1) {
                Utils.executeCommand(serverLevel,"publish",player);
                System.out.println("1");
            }
        }
    }
}
