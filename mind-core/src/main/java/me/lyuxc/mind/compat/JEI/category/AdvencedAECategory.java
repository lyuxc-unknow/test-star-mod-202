package me.lyuxc.mind.compat.JEI.category;

import appeng.core.AppEng;
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
import net.pedroksl.advanced_ae.common.definitions.AAEBlocks;
import net.pedroksl.advanced_ae.recipes.ReactionChamberRecipe;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Arrays;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class AdvencedAECategory implements IRecipeCategory<ReactionChamberRecipe> {
    public static final RecipeType<ReactionChamberRecipe> TYPE = RecipeType.create(Variables.MOD_ID,"reaction", ReactionChamberRecipe.class);
    private final IGuiHelper helper;
    private static final int PADDING = 5;

    public AdvencedAECategory(IGuiHelper helper) {
        this.helper = helper;
    }


    @Override
    public RecipeType<ReactionChamberRecipe> getRecipeType() {
        return TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("jei.category.advenced_aecategory");
    }

    @Override
    public IDrawable getBackground() {
        return helper.createDrawable(AppEng.makeId("textures/guis/reaction_chamber.png"),16,18,135,58);
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return helper.createDrawableItemStack(AAEBlocks.REACTION_CHAMBER.stack());
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ReactionChamberRecipe recipe, IFocusGroup focuses) {
        int x = 11;
        for (var in : recipe.getInputs()) {
            if (!in.isEmpty()) {
                builder.addSlot(RecipeIngredientRole.INPUT,x + PADDING,5 + PADDING).addIngredients(in.getIngredient());
                x += 18;
            }
        }
        if (!recipe.getFluid().isEmpty()) {
            builder.addSlot(RecipeIngredientRole.INPUT,29 + PADDING,24 + PADDING).addFluidStack(Arrays.stream(recipe.getFluid().getIngredient().getStacks()).toList().getFirst().getFluid(),recipe.getFluid().getAmount());
        }

        var output = recipe.getResultItem();
        builder.addSlot(RecipeIngredientRole.OUTPUT,99 + PADDING, 5 + PADDING).addItemStack(output);
    }

}
