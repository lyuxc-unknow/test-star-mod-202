package me.lyuxc.tp.item;

import me.lyuxc.tp.DataComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class TPScrollUsed extends Item {
    public TPScrollUsed() {
        super(new Properties()
                .stacksTo(1)
        );
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        double playerX = itemStack.get(DataComponent.X)!=null?itemStack.get(DataComponent.X):0;
        double playerY = itemStack.get(DataComponent.Y)!=null?itemStack.get(DataComponent.Y):0;
        double playerZ = itemStack.get(DataComponent.Z)!=null?itemStack.get(DataComponent.Z):0;
        pPlayer.setPos(playerX,playerY,playerZ);
//        pPlayer.setItemInHand(pUsedHand, Items.AIR.getDefaultInstance());
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
