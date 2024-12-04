package me.lyuxc.mind.recipes;

import me.lyuxc.mind.utils.Utils;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashSet;
import java.util.Set;

@ParametersAreNonnullByDefault
public record LightningCraftingRecipes(ItemStack input,ItemStack output) {
    public static Set<LightningCraftingRecipes> RECIPES = new HashSet<>();

    public static void addLightningCraftingRecipes(String recipe) {
        String[] v = recipe.split("@");
        ItemStack inputItem = Utils.getItemStack(v[0]);
        ItemStack outputItem = Utils.getItemStack(v[1]);
        RECIPES.add(new LightningCraftingRecipes(inputItem, outputItem));
    }

    @SuppressWarnings("unused")
    public static void addLightningCraftingRecipes(String input, String output) {
        addLightningCraftingRecipes(input + "@" + output);
    }

    @SuppressWarnings("unused")
    public static void addLightningCraftingRecipes(Item input, Item output) {
        ItemStack inputItem = input.getDefaultInstance();
        ItemStack outputItem = output.getDefaultInstance();
        RECIPES.add(new LightningCraftingRecipes(inputItem, outputItem));
    }
}
