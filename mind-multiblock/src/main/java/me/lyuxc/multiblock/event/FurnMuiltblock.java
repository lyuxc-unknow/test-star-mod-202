package me.lyuxc.multiblock.event;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FurnaceBlock;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

import static me.lyuxc.multiblock.utils.Utils.getBlock;

@EventBusSubscriber
public class FurnMuiltblock {
    @SubscribeEvent
    public static void rightClickEvent(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        Block block = getBlock(level,pos);
        if(block instanceof FurnaceBlock) {
            if(!isValid(level,pos) || level.isRaining()) {
                event.setCanceled(true);
            }
        }
    }

    private static Boolean isValid(Level level,BlockPos pos) {
        return getBlock(level,pos.offset(0,2,0)) == Blocks.WATER_CAULDRON;
    }
}
