package me.lyuxc.mind.event;

import me.lyuxc.mind.recipes.DeputyCraftingRecipes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber
public class onCrafting {
    @SubscribeEvent
    public static void onCraftEvent(PlayerEvent.ItemCraftedEvent event) {
        ItemStack craftOutputItem = event.getCrafting();
        Player player = event.getEntity();
        ItemStack offhandItem = player.getItemInHand(InteractionHand.OFF_HAND);
        if(!player.level().isClientSide()) {
           for (DeputyCraftingRecipes recipes : DeputyCraftingRecipes.RECIPES) {
               if(craftOutputItem.is(recipes.craftingOutputItem().getItem())) {
                   if(offhandItem.is(recipes.inputItem().getItem()) && offhandItem.getCount() >= recipes.inputCount()) {
                       ItemStack itemStack = recipes.outputItem();
                       itemStack.setCount(recipes.outputCount());
                       ItemEntity outputItem = new ItemEntity(player.level(),player.getX(),player.getY(),player.getZ(),itemStack);
                       offhandItem.setCount(offhandItem.getCount() - recipes.inputCount());
                       player.level().addFreshEntity(outputItem);
                       break;
                   }
               }
           }
       }
    }
}
