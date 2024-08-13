package me.lyuxc.multiblock.event;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.BlockEvent;

import static me.lyuxc.multiblock.utils.Utils.getBlock;
import static me.lyuxc.multiblock.utils.Utils.setBlock;

@EventBusSubscriber
public class SummonPortal {
    @SubscribeEvent
    public static void disableSpawnNetherPortal(BlockEvent.PortalSpawnEvent event) {
        event.setCanceled(true);
    }

    @SubscribeEvent
    public static void spawnPortal(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        Player player = event.getEntity();
        ItemStack itemStack = player.getItemBySlot(EquipmentSlot.MAINHAND);
        if(!level.isClientSide()) {
            if(getBlock(level,pos) == Blocks.LODESTONE && itemStack.is(Items.FIRE_CHARGE)) {
                if(isValid(level,pos)) {
                    setBlocks(level,pos);
                    itemStack.setCount(itemStack.getCount() - 1);
                    player.setItemSlot(EquipmentSlot.MAINHAND,itemStack);
                    event.setCanceled(true);
                }
            }
        }
    }

    private static Boolean isValid(Level level, BlockPos pos) {
        return getBlock(level,pos.offset(1,0,0)) == Blocks.CRYING_OBSIDIAN &&
                getBlock(level,pos.offset(-1,0,0)) == Blocks.CRYING_OBSIDIAN &&
                getBlock(level,pos.offset(2,1,0)) == Blocks.CRYING_OBSIDIAN &&
                getBlock(level,pos.offset(-2,1,0)) == Blocks.CRYING_OBSIDIAN &&
                getBlock(level,pos.offset(2,2,0)) == Blocks.LODESTONE &&
                getBlock(level,pos.offset(-2,2,0)) == Blocks.LODESTONE &&
                getBlock(level,pos.offset(2,3,0)) == Blocks.CRYING_OBSIDIAN &&
                getBlock(level,pos.offset(-2,3,0)) == Blocks.CRYING_OBSIDIAN &&
                getBlock(level,pos.offset(1,4,0)) == Blocks.CRYING_OBSIDIAN &&
                getBlock(level,pos.offset(-1,4,0)) == Blocks.CRYING_OBSIDIAN &&
                getBlock(level,pos.offset(0,4,0)) == Blocks.LODESTONE
                ;
    }

    private static void setBlocks(Level level,BlockPos pos) {
        setBlock(level,pos.offset(0,1,0),Blocks.NETHER_PORTAL);
        setBlock(level,pos.offset(1,1,0),Blocks.NETHER_PORTAL);
        setBlock(level,pos.offset(-1,1,0),Blocks.NETHER_PORTAL);
        setBlock(level,pos.offset(0,2,0),Blocks.NETHER_PORTAL);
        setBlock(level,pos.offset(1,2,0),Blocks.NETHER_PORTAL);
        setBlock(level,pos.offset(-1,2,0),Blocks.NETHER_PORTAL);
        setBlock(level,pos.offset(0,3,0),Blocks.NETHER_PORTAL);
        setBlock(level,pos.offset(1,3,0),Blocks.NETHER_PORTAL);
        setBlock(level,pos.offset(-1,3,0),Blocks.NETHER_PORTAL);
    }
}
