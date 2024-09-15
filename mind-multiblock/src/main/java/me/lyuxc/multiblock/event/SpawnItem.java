package me.lyuxc.multiblock.event;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@EventBusSubscriber
public class SpawnItem {
    @SubscribeEvent
    public static void rightBlockToSpawnItem(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        BlockPos pos = event.getHitVec().getBlockPos();
        AtomicBoolean isDown = new AtomicBoolean(false);
        //判断底部方块
        if (level.getBlockState(pos.offset(0, -2, 0)).is(Blocks.BEDROCK)) {
            //获取底部方块上的物品
            List<Entity> entity = level.getEntities(null, new AABB(pos.offset(0, -1, 0)));
            //遍历方块的实体
            entity.forEach(e -> {
                //判断是否为物品实体
                if (e instanceof ItemEntity item) {
                    //判断实体物品
                    if (item.getItem().is(Items.TNT)) {
                        // 伪方块下落的效果 -- isDown：防止吞方块
                        if(!isDown.get()) {
                            level.setBlockAndUpdate(pos.offset(0, -1, 0), level.getBlockState(pos));
                            level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                            isDown.set(true);
                        }
                        //变化后的物品
                        ItemStack newItemStack = Items.FIREWORK_STAR.getDefaultInstance();
                        //使新的ItemStack数量与带合成数一致
                        newItemStack.setCount(item.getItem().getCount());
                        ItemEntity newItem = new ItemEntity(level,pos.getX(), pos.getY()+1, pos.getZ(),newItemStack);
                        newItem.setPickUpDelay(10); // 防止立即被拾取
                        level.addFreshEntity(newItem); //添加到世界

                        // 删除原来的Item实体
                        item.kill();
                    }
                }
            });
        }
    }
}
