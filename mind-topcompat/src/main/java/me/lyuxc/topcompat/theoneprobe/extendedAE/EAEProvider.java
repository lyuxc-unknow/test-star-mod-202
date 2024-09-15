package me.lyuxc.topcompat.theoneprobe.extendedAE;

import appeng.api.util.AEColor;
import com.glodblock.github.extendedae.common.tileentities.TileCrystalFixer;
import com.glodblock.github.extendedae.common.tileentities.TileWirelessConnector;
import mcjty.theoneprobe.api.*;
import me.lyuxc.mind.Star;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class EAEProvider implements IProbeInfoProvider {
    @Override
    public ResourceLocation getID() {
        return Star.rl("eae_provider");
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, Player player, Level level, BlockState blockState, IProbeHitData iProbeHitData) {
        BlockEntity blockEntity = level.getBlockEntity(iProbeHitData.getPos());
        if(blockEntity instanceof TileCrystalFixer crystalFixer) {
            iProbeInfo.progress(crystalFixer.getProgress(),100,iProbeInfo.defaultProgressStyle()
                    .suffix("%")
                    .alignment(ElementAlignment.ALIGN_CENTER)
            );
        } else if (blockEntity instanceof TileWirelessConnector tile) {
            AEColor color = tile.getColor();
            iProbeInfo.text(Component.translatable("jade.wireless_connector.color", Component.translatable(color.toString())));
        }
    }
}
