package me.lyuxc.mind.event;

import me.lyuxc.mind.utils.Utils;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AddReloadListenerEvent;


@EventBusSubscriber
public class onReload {
    @SubscribeEvent
    public static void onReloadEvent(AddReloadListenerEvent event) {
        event.addListener((ResourceManagerReloadListener) resourceManager -> Utils.loadModResource(event.getRegistryAccess(),event.getServerResources().getRecipeManager()));
    }
}
