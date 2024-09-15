package me.lyuxc.topcompat.theoneprobe;

import mcjty.theoneprobe.api.ITheOneProbe;
import me.lyuxc.topcompat.theoneprobe.MI.*;
import me.lyuxc.topcompat.theoneprobe.TestStar.SuperGeneratorProvider;
import me.lyuxc.topcompat.theoneprobe.applied.Applied;
import me.lyuxc.topcompat.theoneprobe.extendedAE.EAEProvider;
import me.lyuxc.topcompat.theoneprobe.minecraft.VillagerProvider;

import java.util.function.Function;

public class TOPRegister implements Function<ITheOneProbe, Void> {
    @Override
    public Void apply(ITheOneProbe iTheOneProbe) {
//        iTheOneProbe.registerProvider(new QuarryPlus());
        iTheOneProbe.registerProvider(new OverclockProvider());
        iTheOneProbe.registerProvider(new NetworkTierProvider());
        iTheOneProbe.registerProvider(new MachineTierProvider());
        iTheOneProbe.registerProvider(new PipeDataProvider());
        iTheOneProbe.registerProvider(new MultiblockProvider());
        iTheOneProbe.registerProvider(new MachineProgressProvider());
        iTheOneProbe.registerProvider(new MachineComponentProvider());
        iTheOneProbe.registerProvider(new SuperGeneratorProvider());
        iTheOneProbe.registerProvider(new Applied());
        iTheOneProbe.registerEntityProvider(new VillagerProvider());
        iTheOneProbe.registerProvider(new EAEProvider());
        return null;
    }
}
