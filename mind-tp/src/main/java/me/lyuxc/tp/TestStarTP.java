package me.lyuxc.tp;

import me.lyuxc.tp.item.ItemRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(TestStarTP.MOD_ID)
public class TestStarTP {
    public static final String MOD_ID = "test_star_tp";
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TestStarTP.MOD_ID);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TP_TAB = CREATIVE_MODE_TABS.register("tstp_creative_tab",() -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.test_star_tp"))
            .icon(ItemRegistry.TP_SCROLL_USED::toStack)
            .build());


    public TestStarTP(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
        ItemRegistry.init(eventBus);
        DataComponent.init(eventBus);
        Attachments.init(eventBus);
    }
}
