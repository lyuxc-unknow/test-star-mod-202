package me.lyuxc.mind.compat.JEI;

import me.lyuxc.mind.Star;
import me.lyuxc.mind.recipes.ExplosionCraftingRecipes;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Items;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ExplosionRecipeCategory implements IRecipeCategory<ExplosionCraftingRecipes> {
    private final IGuiHelper helper;

    public ExplosionRecipeCategory(IGuiHelper helper) {
        this.helper = helper;
    }

    @Override
    public RecipeType<ExplosionCraftingRecipes> getRecipeType() {
        return TestStarJEIPlugin.CATEGORY_EXPLOSION;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("ts.tips.jei.explosion_category");
    }

    @Override
    public IDrawable getBackground() {
        return helper.createDrawable(Star.rl("textures/gui/jei/jei_crafting.png"),0,20,80,20);
    }

    @Override
    public IDrawable getIcon() {
        return helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, Items.TNT.getDefaultInstance());
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ExplosionCraftingRecipes recipes, IFocusGroup iFocusGroup) {
        builder.addSlot(RecipeIngredientRole.INPUT,2,2).addItemStack(recipes.input());
        builder.addSlot(RecipeIngredientRole.OUTPUT,62,2).addItemStack(recipes.output());
    }

    @Override
    public List<Component> getTooltipStrings(ExplosionCraftingRecipes recipe, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
        return List.of(Component.translatable("ts.tips.jei.explosion_probability",recipe.change()));
    }
}
