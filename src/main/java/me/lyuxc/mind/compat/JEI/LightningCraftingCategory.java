package me.lyuxc.mind.compat.JEI;

import me.lyuxc.mind.Star;
import me.lyuxc.mind.recipes.LightningCraftingRecipes;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Items;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class LightningCraftingCategory implements IRecipeCategory<LightningCraftingRecipes> {
    private final IGuiHelper helper;
    public LightningCraftingCategory(IGuiHelper helper) {
        this.helper = helper;
    }
    @Override
    public RecipeType<LightningCraftingRecipes> getRecipeType() {
        return TestStarJEIPlugin.CATEGORY_LIGHTNING;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("ts.tips.jei.lightning_category");
    }

    @Override
    public IDrawable getBackground() {
        return helper.createDrawable(Star.rl("textures/gui/jei/jei_crafting.png"),98,0,80,20);
    }

    @Override
    public IDrawable getIcon() {
        return helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, Items.LIGHTNING_ROD.getDefaultInstance());
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, LightningCraftingRecipes recipes, IFocusGroup iFocusGroup) {
        builder.addSlot(RecipeIngredientRole.INPUT,2,2).addItemStack(recipes.input());
        builder.addSlot(RecipeIngredientRole.OUTPUT,62,2).addItemStack(recipes.output());
    }
}
