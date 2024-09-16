package me.lyuxc.mind.compat.JEI.category;

import appeng.core.AppEng;
import com.glodblock.github.extendedae.common.EAESingletons;
import com.glodblock.github.extendedae.recipe.CircuitCutterRecipe;
import me.lyuxc.mind.Variables;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class CircuitCutterRecipeCategory implements IRecipeCategory<CircuitCutterRecipe> {
    public static final RecipeType<CircuitCutterRecipe> TYPE = RecipeType.create(Variables.MOD_ID,"circult", CircuitCutterRecipe.class);
    private final IGuiHelper helper;

    public CircuitCutterRecipeCategory(IGuiHelper helper) {
        this.helper = helper;
    }

    @Override
    public RecipeType<CircuitCutterRecipe> getRecipeType() {
        return TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("jei.category.circuitCutter");
    }

    @Override
    public IDrawable getBackground() {
        return helper.createDrawable(AppEng.makeId("textures/guis/circuit_cutter.png"),43,32,94,26);
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return helper.createDrawableItemStack(EAESingletons.CIRCUIT_CUTTER.asItem().getDefaultInstance());
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CircuitCutterRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT,3,5).addIngredients(recipe.getInput().getIngredient());
        builder.addSlot(RecipeIngredientRole.OUTPUT,66,5).addItemStack(recipe.output);
    }
}
