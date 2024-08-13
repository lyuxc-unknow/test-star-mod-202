package me.lyuxc.multiblock.event;

import me.lyuxc.multiblock.utils.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.UseItemOnBlockEvent;

import java.util.HashMap;
import java.util.Map;

import static me.lyuxc.multiblock.utils.Utils.getBlock;

@EventBusSubscriber
public class LodeStoneAltar {
    private static final String[] matrix = {
            "XXXXXXOXXXXXX",
            "XGMXXXONNNXXX",
            "XMXXXXOXXXXXX",
            "XXXXSSOXXGXNX",
            "XXXSXXOXXXXNX",
            "XXXSXXOXXXXNX",
            "OOOOOOXOOOOOO",
            "XNXXXXOXXSXXX",
            "XNXXXXOXXSXXX",
            "XNXGXXOSSXXXX",
            "XXXXXXOXXXXMX",
            "XXXNNNOXXXMGX",
            "XXXXXXOXXXXXX"
    };

    private static final String[] matrix2 = {
            "RRRRR",
            "RXXXR",
            "RXXXR",
            "RXXXR",
            "RRRRR"
    };

    private static final Map<Character, Block> blockMap = new HashMap<>();
    static {
        blockMap.put('O',Blocks.OBSIDIAN);
        blockMap.put('G',Blocks.GLOWSTONE);
        blockMap.put('M',Blocks.MAGMA_BLOCK);
        blockMap.put('S',Blocks.SOUL_SAND);
        blockMap.put('N',Blocks.NETHERRACK);
        blockMap.put('R',Blocks.REDSTONE_WIRE);
    }

    @SubscribeEvent
    public static void onPlayerRightClickLodeStone(UseItemOnBlockEvent event) {
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        Player player = event.getPlayer();
        ItemStack itemStack = ItemStack.EMPTY;
        if (player != null) {
            itemStack = player.getItemBySlot(EquipmentSlot.MAINHAND);
        }
        if(!level.isClientSide()) {
            if(event.getUsePhase() == UseItemOnBlockEvent.UsePhase.ITEM_AFTER_BLOCK) {
                if(getBlock(level,pos) == Blocks.LODESTONE && getBlock(level,pos.offset(0,1,0)) == Blocks.LIGHTNING_ROD) {
                    if(isValid(level,pos) && itemStack.is(Items.GUNPOWDER)) {
                        spawnEntity(level,pos);
                        itemStack.setCount(itemStack.getCount() - 1);
                    }
                }
            }
        }
    }

    private static void spawnEntity(Level level, BlockPos pos) {
        LightningBolt lightningBolt = new LightningBolt(EntityType.LIGHTNING_BOLT,level);
        lightningBolt.moveTo(pos.offset(0,1,0),0,0);
        level.addFreshEntity(lightningBolt);
    }

    private static boolean isValid(Level level,BlockPos pos) {
        return Utils.isValid(level,pos,matrix,blockMap,-1,13,6) &&
                Utils.isValid(level,pos,matrix2,blockMap,0,5,2);
    }
}
