package me.lyuxc.mind.recipes;

import me.lyuxc.mind.utils.Utils;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashSet;
import java.util.Set;

@ParametersAreNonnullByDefault
public record DropCraftingRecipes(ItemStack input, ItemStack offhandItems, int quantityConsumed, ItemStack output,
                                  int outputCount) {
    //丢东西合成
    public static Set<DropCraftingRecipes> RECIPES = new HashSet<>();

    public static void addPlayerPickupRecipes(String recipe) {
        String[] v = recipe.split("@");
        ItemStack inputItem = Utils.getItemStack(v[0]);
        ItemStack offhandItem = Utils.getItemStack(v[1]);
        ItemStack outputItem = Utils.getItemStack(v[3]);
        RECIPES.add(new DropCraftingRecipes(inputItem, offhandItem, Integer.parseInt(v[2]), outputItem, Integer.parseInt(v[4])));
    }

    @SuppressWarnings("unused")
    public static void addPlayerPickupRecipes(String input, String offHandItem, int quantityConsumed, String output, int outputCount) {
        addPlayerPickupRecipes(input + "@" + offHandItem + "@" + quantityConsumed + "@" + output + "@" + outputCount);
    }
    @SuppressWarnings("unused")
    public static void addPlayerPickupRecipes(Item input, Item offHandItem, int quantityConsumed, Item output, int outputCount) {
        ItemStack inputItem = input.getDefaultInstance();
        ItemStack offhandItem = offHandItem.getDefaultInstance();
        ItemStack outputItem = output.getDefaultInstance();
        RECIPES.add(new DropCraftingRecipes(inputItem, offhandItem, quantityConsumed, outputItem, outputCount));
    }
}
