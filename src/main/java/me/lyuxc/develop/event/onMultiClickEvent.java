package me.lyuxc.develop.event;

import me.lyuxc.develop.AttachmentTypes;
import me.lyuxc.develop.Variables;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.entity.player.UseItemOnBlockEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.Objects;


@EventBusSubscriber(modid = Variables.MOD_ID)
public class onMultiClickEvent {
    /**
    * FTB方案，旧版本看Github 历史记录，由于用到客户端专有的东西而导致服务器无法使用而替换
    * */
    @SubscribeEvent
    public static void onPlayerRightBlock(UseItemOnBlockEvent event) {
        if(!Objects.requireNonNull(event.getPlayer()).level().isClientSide()) {
            if(event.getUsePhase() == UseItemOnBlockEvent.UsePhase.ITEM_AFTER_BLOCK) {
                clickE(event.getPlayer());
            }
        }
    }
    @SubscribeEvent
    public static void onPlayerLeftBlock(PlayerInteractEvent.LeftClickBlock event) {
        if(!event.getEntity().level().isClientSide && event.getAction() == PlayerInteractEvent.LeftClickBlock.Action.STOP) {
            clickE(event.getEntity());
        }
    }
    private static void clickE(Player player) {
        player.setData(AttachmentTypes.DIGGING_ATTACHMENT.get(), player.getData(AttachmentTypes.DIGGING_ATTACHMENT.get()) + 1);
        if(player.getData(AttachmentTypes.DIGGING_ATTACHMENT.get()) > 12) {
            if(player instanceof ServerPlayer _player) {
                _player.connection.disconnect(Component.translatable("ts.server.kick"));
            }
        } else if(player.getData(AttachmentTypes.DIGGING_ATTACHMENT.get()) > 9) {
            player.setData(AttachmentTypes.THROTTLED_ATTACHMENT.get(), true);
            player.setData(AttachmentTypes.THROTTLED_TIMER_ATTACHMENT.get(), 0);
            player.displayClientMessage(Component.translatable("ts.click.much"),true);
        }
    }
    @SubscribeEvent
    public static void onTick(PlayerTickEvent.Pre event) {
        Player player = event.getEntity();
        if(player.tickCount % 4 == 0 && player.getData(AttachmentTypes.DIGGING_ATTACHMENT.get()) > 0) {
            player.setData(AttachmentTypes.DIGGING_ATTACHMENT, player.getData(AttachmentTypes.DIGGING_ATTACHMENT.get()) - 1);
        }
        if(player.getData(AttachmentTypes.THROTTLED_ATTACHMENT.get())) {
           player.setData(AttachmentTypes.THROTTLED_TIMER_ATTACHMENT.get(), player.getData(AttachmentTypes.THROTTLED_TIMER_ATTACHMENT.get()) + 1);
        }
        if(player.getData(AttachmentTypes.THROTTLED_TIMER_ATTACHMENT.get()) > 40) {
            event.getEntity().displayClientMessage(Component.translatable("ts.click.enable"), true);
            player.setData(AttachmentTypes.DIGGING_ATTACHMENT.get(), 0);
            player.setData(AttachmentTypes.THROTTLED_ATTACHMENT.get(), false);
            player.setData(AttachmentTypes.THROTTLED_TIMER_ATTACHMENT.get(), 0);
        }
    }
}
