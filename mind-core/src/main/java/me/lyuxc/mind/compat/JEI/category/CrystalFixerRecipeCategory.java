package me.lyuxc.mind.compat.JEI.category;

import appeng.core.AppEng;
import com.glodblock.github.extendedae.common.EAESingletons;
import com.glodblock.github.extendedae.recipe.CrystalFixerRecipe;
import me.lyuxc.mind.Variables;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.ITooltipBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class CrystalFixerRecipeCategory implements IRecipeCategory<CrystalFixerRecipe> {
    public static final RecipeType<CrystalFixerRecipe> TYPE = RecipeType.create(Variables.MOD_ID,"crystal_fixer", CrystalFixerRecipe.class);
    private static final DecimalFormat F = new DecimalFormat("#.#%", new DecimalFormatSymbols());
    private final IGuiHelper helper;

    public CrystalFixerRecipeCategory(IGuiHelper helper) {
        this.helper = helper;
    }

    @Override
    public RecipeType<CrystalFixerRecipe> getRecipeType() {
        return TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.literal("jei.category.crystal_fixer");
    }

    @Override
    public IDrawable getBackground() {
        return helper.createDrawable(AppEng.makeId("textures/xei/crystal_fixer.png"),0,0,120,63);
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return helper.createDrawableItemStack(EAESingletons.CRYSTAL_FIXER.asItem().getDefaultInstance());
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CrystalFixerRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.RENDER_ONLY,50,29)
                .addItemStack(EAESingletons.CRYSTAL_FIXER.asItem().getDefaultInstance());

        builder.addSlot(RecipeIngredientRole.INPUT,1,19).addItemStack(recipe.getInput().asItem().getDefaultInstance());
        builder.addSlot(RecipeIngredientRole.CATALYST,50,12).addIngredients(recipe.getFuel().getIngredient());
        builder.addSlot(RecipeIngredientRole.OUTPUT,97,19).addItemStack(recipe.getOutput().asItem().getDefaultInstance());
    }

    @Override
    public void getTooltip(ITooltipBuilder tooltip, CrystalFixerRecipe recipe, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
        tooltip.add(Component.translatable("emi.extendedae.text.success_chance", F.format(recipe.getChance())).withColor(0x7e7e7e));
        IRecipeCategory.super.getTooltip(tooltip, recipe, recipeSlotsView, mouseX, mouseY);
    }
}
