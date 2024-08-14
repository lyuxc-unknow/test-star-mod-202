package me.lyuxc.topcompat;

import me.lyuxc.topcompat.theoneprobe.TOPRegister;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.InterModComms;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(TestStarTopCompat.MOD_ID)
public class TestStarTopCompat {
    public static final String MOD_ID = "test_star_topcampat";
    public TestStarTopCompat(IEventBus modEventbus) {
        modEventbus.addListener(this::commonSetup);
    }
    private void commonSetup(FMLCommonSetupEvent event) {
        InterModComms.sendTo("theoneprobe", "getTheOneProbe", TOPRegister::new);
    }
    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID,path);
    }
}
