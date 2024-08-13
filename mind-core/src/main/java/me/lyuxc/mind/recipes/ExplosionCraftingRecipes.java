package me.lyuxc.mind.recipes;

import me.lyuxc.mind.utils.Utils;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashSet;
import java.util.Set;

@ParametersAreNonnullByDefault
public record ExplosionCraftingRecipes(ItemStack input, int inputCount, ItemStack output, double change) {
    public static Set<ExplosionCraftingRecipes> RECIPES = new HashSet<>();
    public static void addExplosionRecipes(String recipe) {
        String[] v = recipe.split("@");
        RECIPES.add(new ExplosionCraftingRecipes(Utils.getItemStack(v[0]), Integer.parseInt(v[1]), Utils.getItemStack(v[2]), Double.parseDouble(v[3])));
    }
    @SuppressWarnings("unused")
    public static void addExplosionRecipes(String input, int inputCount, String output, double change) {
        addExplosionRecipes(input + "@" + inputCount + "@" + output + "@" + change);
    }
    @SuppressWarnings("unused")
    public static void addExplosionRecipes(Item input, int inputCount, Item output, double change) {
        RECIPES.add(new ExplosionCraftingRecipes(input.getDefaultInstance(), inputCount, output.getDefaultInstance(), change));
    }
}
