package me.lyuxc.tp.item;

import me.lyuxc.tp.DataComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class TPScroll extends Item {
    public TPScroll() {
        super(new Properties()
                .stacksTo(1)
        );
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pUsedHand) {
        ItemStack itemStack = ItemRegistry.TP_SCROLL_USED.toStack();
        itemStack.set(DataComponent.X,pPlayer.getX());
        itemStack.set(DataComponent.Y,pPlayer.getY());
        itemStack.set(DataComponent.Z,pPlayer.getZ());
        pPlayer.setItemInHand(pUsedHand,itemStack);
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
