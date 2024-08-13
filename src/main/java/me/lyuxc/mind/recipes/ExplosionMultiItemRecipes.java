package me.lyuxc.mind.recipes;

import me.lyuxc.mind.utils.Utils;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ParametersAreNonnullByDefault
public record ExplosionMultiItemRecipes(List<ItemStack> inputs,int inputCount,ItemStack output,double change) {
    public static Set<ExplosionMultiItemRecipes> RECIPES = new HashSet<>();
    public static void addExplosionMultiRecipes(String recipe) {
        String[] v = recipe.split("@");
        RECIPES.add(new ExplosionMultiItemRecipes(List.of(Utils.getItemStack(v[0]), Utils.getItemStack(v[1])), Integer.parseInt(v[2]), Utils.getItemStack(v[3]), Double.parseDouble(v[4])));
    }
    @SuppressWarnings("unused")
    public static void addExplosionMultiRecipes(String[] input, int inputCount, String output, double change) {
        addExplosionMultiRecipes(input[0] + "@" + input[1] + "@" + inputCount + "@" + output + "@" + change);
    }
    @SuppressWarnings("unused")
    public static void addExplosionMultiRecipes(List<ItemStack> input, int inputCount, Item output, double change) {
        RECIPES.add(new ExplosionMultiItemRecipes(input, inputCount, output.getDefaultInstance(), change));
    }
}
