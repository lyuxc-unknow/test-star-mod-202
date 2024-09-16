package me.lyuxc.mind.compat.JEI;

import com.glodblock.github.extendedae.common.EAESingletons;
import com.glodblock.github.extendedae.recipe.CircuitCutterRecipe;
import com.glodblock.github.extendedae.recipe.CrystalAssemblerRecipe;
import com.glodblock.github.extendedae.recipe.CrystalFixerRecipe;
import me.lyuxc.mind.Star;
import me.lyuxc.mind.Variables;
import me.lyuxc.mind.compat.JEI.category.*;
import me.lyuxc.mind.recipes.*;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeManager;
import net.pedroksl.advanced_ae.common.definitions.AAEBlocks;
import net.pedroksl.advanced_ae.recipes.ReactionChamberRecipe;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@JeiPlugin
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class TestStarJEIPlugin implements IModPlugin {
    protected static IJeiRuntime iJeiRuntime;
    public static final RecipeType<DropCraftingRecipes> CATEGORY_DROP = RecipeType.create(Variables.MOD_ID,"drop_crafting", DropCraftingRecipes.class);
    public static final RecipeType<ExplosionCraftingRecipes> CATEGORY_EXPLOSION = RecipeType.create(Variables.MOD_ID,"explosion_crafting", ExplosionCraftingRecipes.class);
    public static final RecipeType<ExplosionMultiItemRecipes> CATEGORY_MULTI_EXPLOSION = RecipeType.create(Variables.MOD_ID,"multi_explosion_crafting", ExplosionMultiItemRecipes.class);
    public static final RecipeType<DeputyCraftingRecipes> CATEGORY_DEPUTY = RecipeType.create(Variables.MOD_ID,"deputy_crafting", DeputyCraftingRecipes.class);
    public static final RecipeType<LightningCraftingRecipes> CATEGORY_LIGHTNING = RecipeType.create(Variables.MOD_ID,"lightning_crafting", LightningCraftingRecipes.class);
    @Override
    public ResourceLocation getPluginUid() {
        return Star.rl("test_star_jei");
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(Items.TNT.getDefaultInstance(),CATEGORY_EXPLOSION);
        registration.addRecipeCatalyst(Items.TNT.getDefaultInstance(),CATEGORY_MULTI_EXPLOSION);
        registration.addRecipeCatalyst(Items.CRAFTING_TABLE.getDefaultInstance(),CATEGORY_DEPUTY);
        registration.addRecipeCatalyst(AAEBlocks.REACTION_CHAMBER.stack(),AdvencedAECategory.TYPE);
        registration.addRecipeCatalyst(EAESingletons.CIRCUIT_CUTTER.asItem().getDefaultInstance(),CircuitCutterRecipeCategory.TYPE);
        registration.addRecipeCatalyst(EAESingletons.CRYSTAL_ASSEMBLER.asItem().getDefaultInstance(),CrystalAssemblerRecipeCategory.TYPE);
        registration.addRecipeCatalyst(EAESingletons.CRYSTAL_FIXER.asItem().getDefaultInstance(),CrystalFixerRecipeCategory.TYPE);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(
                new DeputyCraftingRecipeCategory(registration.getJeiHelpers().getGuiHelper()),
                new DropRecipeCategory(registration.getJeiHelpers().getGuiHelper()),
                new ExplosionRecipeCategory(registration.getJeiHelpers().getGuiHelper()),
                new LightningCraftingCategory(registration.getJeiHelpers().getGuiHelper()),
                new MultiExplosionRecipeCategory(registration.getJeiHelpers().getGuiHelper()),
                new AdvencedAECategory(registration.getJeiHelpers().getGuiHelper()),
                new CircuitCutterRecipeCategory(registration.getJeiHelpers().getGuiHelper()),
                new CrystalAssemblerRecipeCategory(registration.getJeiHelpers().getGuiHelper()),
                new CrystalFixerRecipeCategory(registration.getJeiHelpers().getGuiHelper())
        );
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        ClientLevel level = Minecraft.getInstance().level;
        assert level != null;
        RecipeManager recipeManager = level.getRecipeManager();

        registration.addRecipes(CATEGORY_DROP, DropCraftingRecipes.RECIPES.stream().map(dropRecipes -> {
            ItemStack input = dropRecipes.input();
            ItemStack offhand = dropRecipes.offhandItems();
            ItemStack output = dropRecipes.output();
            offhand.setCount(dropRecipes.quantityConsumed() == 0 ? 1 : dropRecipes.quantityConsumed());
            offhand.set(DataComponents.CUSTOM_NAME, Component.translatable("ts.tips.jei.offhandTip", offhand.getDisplayName()));
            output.setCount(dropRecipes.outputCount());
            return new DropCraftingRecipes(input, offhand, dropRecipes.quantityConsumed(), output, dropRecipes.outputCount());
        }).toList());
        registration.addRecipes(CATEGORY_EXPLOSION, ExplosionCraftingRecipes.RECIPES.stream().map(explosionRecipes -> {
            ItemStack input = explosionRecipes.input();
            ItemStack output = explosionRecipes.output();
            input.setCount(explosionRecipes.inputCount());
            return new ExplosionCraftingRecipes(input, explosionRecipes.inputCount(), output, explosionRecipes.change());
        }).toList());
        registration.addRecipes(CATEGORY_MULTI_EXPLOSION, ExplosionMultiItemRecipes.RECIPES.stream().toList());
        registration.addRecipes(CATEGORY_DEPUTY, DeputyCraftingRecipes.RECIPES.stream().map(recipes -> {
            ItemStack inputItem = recipes.inputItem();
            ItemStack outputItem = recipes.outputItem();
            ItemStack craftingItem = recipes.craftingOutputItem();
            inputItem.setCount(recipes.inputCount());
            inputItem.set(DataComponents.CUSTOM_NAME, Component.translatable("ts.tips.jei.offhandTip", inputItem.getDisplayName()));
            outputItem.setCount(recipes.outputCount());
            return new DeputyCraftingRecipes(inputItem, recipes.inputCount(), outputItem, recipes.outputCount(), craftingItem, recipes.recipe());
        }).toList());
        registration.addRecipes(CATEGORY_LIGHTNING, LightningCraftingRecipes.RECIPES.stream().toList());
        recipeManager.getAllRecipesFor(ReactionChamberRecipe.TYPE).forEach(holder ->
                registration.addRecipes(AdvencedAECategory.TYPE, List.of(holder.value())));
        recipeManager.getAllRecipesFor(CircuitCutterRecipe.TYPE).forEach(holder ->
                registration.addRecipes(CircuitCutterRecipeCategory.TYPE, List.of(holder.value())));
        recipeManager.getAllRecipesFor(CrystalAssemblerRecipe.TYPE).forEach(holder ->
                registration.addRecipes(CrystalAssemblerRecipeCategory.TYPE, List.of(holder.value())));
        recipeManager.getAllRecipesFor(CrystalFixerRecipe.TYPE).forEach(holder ->
                registration.addRecipes(CrystalFixerRecipeCategory.TYPE, List.of(holder.value())));
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
        iJeiRuntime = jeiRuntime;
    }

    public static void displayRecipes(ItemStack stack) {
        if (!stack.isEmpty()) {
            iJeiRuntime.getRecipesGui().show(iJeiRuntime.getJeiHelpers().getFocusFactory().createFocus(RecipeIngredientRole.OUTPUT, VanillaTypes.ITEM_STACK, stack));
        }
    }

    public static void displayUses(ItemStack stack) {
        if (!stack.isEmpty()) {
            iJeiRuntime.getRecipesGui().show(iJeiRuntime.getJeiHelpers().getFocusFactory().createFocus(RecipeIngredientRole.INPUT, VanillaTypes.ITEM_STACK, stack));
        }
    }
}
