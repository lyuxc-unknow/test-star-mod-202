package me.lyuxc.mind.compat.JEI.category;

import me.lyuxc.mind.Star;
import me.lyuxc.mind.compat.JEI.TestStarJEIPlugin;
import me.lyuxc.mind.recipes.DropCraftingRecipes;
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
public class DropRecipeCategory implements IRecipeCategory<DropCraftingRecipes> {
    private final IGuiHelper helper;
    public DropRecipeCategory(IGuiHelper helper) {
        this.helper = helper;
    }
    @Override
    public RecipeType<DropCraftingRecipes> getRecipeType() {
        return TestStarJEIPlugin.CATEGORY_DROP;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("ts.tips.jei.drop_category");
    }

    @Override
    public IDrawable getBackground() {
        return helper.createDrawable(Star.rl("textures/gui/jei/jei_crafting.png"),0,0,98,20);
    }

    @Override
    public IDrawable getIcon() {
        return helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, Items.ANVIL.getDefaultInstance());
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder iRecipeLayoutBuilder, DropCraftingRecipes dropCraftingRecipes, IFocusGroup iFocusGroup) {
        iRecipeLayoutBuilder.addSlot(RecipeIngredientRole.INPUT,2,2).addItemStack(dropCraftingRecipes.input());
        iRecipeLayoutBuilder.addSlot(RecipeIngredientRole.INPUT,21,2).addItemStack(dropCraftingRecipes.offhandItems());
        iRecipeLayoutBuilder.addSlot(RecipeIngredientRole.OUTPUT,80,2).addItemStack(dropCraftingRecipes.output());
    }
}
