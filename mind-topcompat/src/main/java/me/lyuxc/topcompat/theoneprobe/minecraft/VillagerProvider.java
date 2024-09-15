package me.lyuxc.topcompat.theoneprobe.minecraft;

import mcjty.theoneprobe.api.IProbeHitEntityData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoEntityProvider;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class VillagerProvider implements IProbeInfoEntityProvider {
    @Override
    public String getID() {
        return "villager";
    }

    @Override
    public void addProbeEntityInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, Player player, Level level, Entity entity, IProbeHitEntityData iProbeHitEntityData) {
        if(entity instanceof Villager villager) {
            VillagerData villagerData = villager.getVillagerData();
            iProbeInfo.text(String.format("Level: %d", villagerData.getLevel()));
        }
    }
}
