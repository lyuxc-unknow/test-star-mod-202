package me.lyuxc.mind.compat.JEI.category;

import appeng.core.AppEng;
import com.glodblock.github.extendedae.common.EAESingletons;
import com.glodblock.github.extendedae.recipe.CrystalAssemblerRecipe;
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
import java.util.Arrays;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class CrystalAssemblerRecipeCategory implements IRecipeCategory<CrystalAssemblerRecipe> {
    public static final RecipeType<CrystalAssemblerRecipe> TYPE = RecipeType.create(Variables.MOD_ID,"crystal_assembler", CrystalAssemblerRecipe.class);
    private final IGuiHelper helper;

    public CrystalAssemblerRecipeCategory(IGuiHelper helper) {
        this.helper = helper;
    }

    @Override
    public RecipeType<CrystalAssemblerRecipe> getRecipeType() {
        return TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("emi.extendedae.category.assembler");
    }

    @Override
    public IDrawable getBackground() {
        return helper.createDrawable(AppEng.makeId("textures/guis/crystal_assembler.png"),23,19,135,58);
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return helper.createDrawableItemStack(EAESingletons.CRYSTAL_ASSEMBLER.asItem().getDefaultInstance());
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CrystalAssemblerRecipe recipe, IFocusGroup focuses) {
        int x = 2;
        int y = 2;
        for (var in : recipe.getInputs()) {
            if (!in.isEmpty()) {
                builder.addSlot(RecipeIngredientRole.INPUT, x+1, y+1).addIngredients(in.getIngredient());
                x += 18;
                if (x >= 18 * 3) {
                    y += 18;
                    x = 2;
                }
            }
        }
        if (recipe.getFluid() != null) {
            builder.addSlot(RecipeIngredientRole.INPUT,58,39).addFluidStack(Arrays.stream(recipe.getFluid().getIngredient().getStacks()).toList().getFirst().getFluid(),recipe.getFluid().getAmount());
        }
        builder.addSlot(RecipeIngredientRole.OUTPUT,107,21).addItemStack(recipe.output);
    }
}
