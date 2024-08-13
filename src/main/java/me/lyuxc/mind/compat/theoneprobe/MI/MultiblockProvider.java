package me.lyuxc.mind.compat.theoneprobe.MI;

import aztech.modern_industrialization.MIText;
import aztech.modern_industrialization.machines.multiblocks.MultiblockMachineBlockEntity;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ProbeMode;
import me.lyuxc.mind.Star;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class MultiblockProvider implements IProbeInfoProvider {
    @Override
    public ResourceLocation getID() {
        return Star.rl("test");
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, Player player, Level level, BlockState blockState, IProbeHitData iProbeHitData) {
        BlockEntity blockEntity = level.getBlockEntity(iProbeHitData.getPos());
        if (blockEntity instanceof MultiblockMachineBlockEntity entity) {
            if (entity.isShapeValid()) {
                iProbeInfo.text(MIText.MultiblockShapeValid.text().getString());
            } else {
                iProbeInfo.text(MIText.MultiblockShapeInvalid.text().getString());
            }
        }
    }
}
