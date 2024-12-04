package me.lyuxc.tp;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber
public class RandomTP {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        Player player = event.getEntity();
        Level level = player.level();
        if(!event.isEndConquered()) {
            if(player instanceof ServerPlayer player1) {
                RandomSource randomSource = RandomSource.create();
                double x = player1.getX();
                double y = player1.getY();
                double z = player1.getZ();
                double newX = x + randomSource.nextIntBetweenInclusive(-10000,10000);
                double newY = y + randomSource.nextIntBetweenInclusive(70,180);
                double newZ = z + randomSource.nextIntBetweenInclusive(-10000,10000);
                if (level.isEmptyBlock(new BlockPos((int) newX, (int) newY, (int) newZ))) {
                    player1.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING,600,10,false,false));
                }
                player1.teleportRelative(newX, newY, newZ);
            }
        }
    }
}
