package me.lyuxc.tp;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber
public class RandomTP {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        Player player = event.getEntity();
        if(!event.isEndConquered()) {
            if(player instanceof ServerPlayer player1) {
                RandomSource randomSource = RandomSource.create();
                double x = player1.getX();
                double z = player1.getZ();
                double newX = x + randomSource.nextIntBetweenInclusive(-10000,10000);
                double newZ = z + randomSource.nextIntBetweenInclusive(-10000,10000);
                player1.teleportRelative(newX,player1.getY(),newZ);
            }
        }
    }
}
