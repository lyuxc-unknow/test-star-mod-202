package me.lyuxc.mind.utils;

import me.lyuxc.mind.Variables;
import me.lyuxc.mind.recipes.*;
import net.minecraft.SharedConstants;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

import javax.annotation.ParametersAreNonnullByDefault;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@ParametersAreNonnullByDefault
public class Utils {
    public static int getTime(int time) {
        return time * 20;
    }

    /**
     * 执行指令
     * @param level 使用指令的世界
     * @param command 待执行的命令
     */
    @SuppressWarnings("unused")
    public static void executeCommand(ServerLevel level, String command) {
        executeCommand(level,command,0,0,0);
    }

    /**
     * 执行指令
     * @param level 使用指令的世界
     * @param command 待执行的命令
     * @param player 在玩家脚底下执行命令
     */
    @SuppressWarnings("unused")
    public static void executeCommand(ServerLevel level, String command, Player player) {
        executeCommand(level,command,player.getX(),player.getY(),player.getZ());
    }

    /**
     * 执行指令
     * @param level 使用指令的世界
     * @param command 待执行的命令
     * @param x X坐标
     * @param y Y坐标
     * @param z Z坐标
     */
    @SuppressWarnings("unused")
    private static void executeCommand(ServerLevel level, String command, double x, double y, double z) {
        MinecraftServer server = level.getServer();
        Vec3 vec3 = new Vec3(x,y,z);
        CommandSourceStack sourceStack = new CommandSourceStack(CommandSource.NULL, vec3, Vec2.ZERO, level, 4, "", Component.literal(""), server, null)
                .withSuppressedOutput();
        server.getCommands().performPrefixedCommand(sourceStack, command);
    }

    /**
     * 禁用飞行
     * @param player 禁止飞行的玩家
     */
    @SuppressWarnings("unused")
    public static void disableFly(Player player) {
        player.getAbilities().flying = false;
        player.getAbilities().setFlyingSpeed(0);
    }

    /**
     * 启用飞行
     * @param player 允许飞行的玩家
     */
    @SuppressWarnings("unused")
    public static void enableFly(Player player) {
        player.getAbilities().flying = true;
        player.getAbilities().setFlyingSpeed(0.015F);
    }

    /**
     * 获取物品
     * @param itemId 通过字符串ID获取物品
     * @return 获取到的物品，如果未获取到则返回空气
     */
    public static Item getItem(String itemId) {
        String[] ids = itemId.split(":");
        return BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(ids[0], ids[1]));
    }

    /**
     * 获取ItemStack
     * @param itemId 通过字符串ID获取ItemStack
     * @return 获取到的ItemStack，如果未获取到则返回空气
     */
    public static ItemStack getItemStack(String itemId) {
        return getItem(itemId).getDefaultInstance();
    }

    /**
     * 随机获取一个物品
     * @return 随机获取到的一个物品
     */
    public static Item getRandomItemStack() {
        List<Item> item = new ArrayList<>();
        for(ResourceLocation rl : BuiltInRegistries.ITEM.keySet()) {
            item.add(BuiltInRegistries.ITEM.get(rl));
        }
        return item.get(Variables.random.nextInt(item.size()));
    }

    /**
     * 加载资源
     * @param access 注册访问器
     * @param manager 配方管理器
     */
    public static void loadModResource(RegistryAccess access,RecipeManager manager) {
        try {
            //读取前清空
            DropCraftingRecipes.RECIPES.clear();
            ExplosionCraftingRecipes.RECIPES.clear();
            ExplosionMultiItemRecipes.RECIPES.clear();
            DeputyCraftingRecipes.RECIPES.clear();
            LightningCraftingRecipes.RECIPES.clear();
            //读取文件存入合成表列表
            Variables.IDs = FileUtils.readFromFile("banBlock.recipes", false).split(System.lineSeparator());
            addRecipesFromFile("dropCrafting.recipes", DropCraftingRecipes::addPlayerPickupRecipes);
            addRecipesFromFile("multiExplosion.recipes", ExplosionMultiItemRecipes::addExplosionMultiRecipes);
            addRecipesFromFile("explosion.recipes", ExplosionCraftingRecipes::addExplosionRecipes);
            addRecipesFromFile("lightning.recipes", LightningCraftingRecipes::addLightningCraftingRecipes);
            for (String recipe : FileUtils.readFromFile("deputy.recipes", false).split(System.lineSeparator())) {
                if (!recipe.isEmpty()) {
                    DeputyCraftingRecipes.addDeputyCraftingRecipes(recipe,access,manager);
                }
            }
            runningInIDE(access,manager);
        } catch (FileNotFoundException e) {
            // 如果没找到就创建
            createRecipeFiles();
        }
    }

    /**
     * 获取配方
     * @param itemStack 物品阀
     * @param access 注册访问器
     * @param recipeManager 配方管理器
     * @return 获取到的配方
     */
    public static List<NonNullList<Ingredient>> getRecipe(ItemStack itemStack, RegistryAccess access, RecipeManager recipeManager) {
        List<NonNullList<Ingredient>> recipes = new ArrayList<>();
        for(RecipeHolder<?> recipeHolder : recipeManager.getRecipes()) {
            if(recipeHolder.value().getType() == RecipeType.CRAFTING) {
                if (itemStack.is(recipeHolder.value().getResultItem(access).getItem())) {
                    recipes.add(recipeHolder.value().getIngredients());
                }
            }
        }
        return recipes;
    }

    /**
     * 只在调试环境运行的代码
     * @param access 注册访问器
     * @param recipeManager 配方管理器
     */
    private static void runningInIDE(RegistryAccess access, RecipeManager recipeManager) {
        if(SharedConstants.IS_RUNNING_IN_IDE) {
            DeputyCraftingRecipes.addDeputyCraftingRecipes(Items.DIRT,1,Items.DIAMOND,1,Items.END_CRYSTAL,access,recipeManager);
            DropCraftingRecipes.addPlayerPickupRecipes(Items.DIRT,Items.DIAMOND,1,Items.DIAMOND_BLOCK,1);
            ExplosionCraftingRecipes.addExplosionRecipes(Items.DIRT,1,Items.DIAMOND,1);
            ExplosionMultiItemRecipes.addExplosionMultiRecipes(List.of(Items.OBSIDIAN.getDefaultInstance(),Items.DIAMOND.getDefaultInstance()),1,Items.DIRT,1);
            LightningCraftingRecipes.addLightningCraftingRecipes(Items.DIRT,Items.DIAMOND);
        }
    }

    /**
     * 从文件添加私有配方方法
     * @param fileName 文件名
     * @param recipeConsumer 配方添加方法
     * @throws FileNotFoundException 文件未找到报错
     */
    private static void addRecipesFromFile(String fileName, Consumer<String> recipeConsumer) throws FileNotFoundException {
        String[] recipes = FileUtils.readFromFile(fileName, false).split(System.lineSeparator());
        for (String recipe : recipes) {
            if (!recipe.isEmpty()) {
                recipeConsumer.accept(recipe);
            }
        }
    }

    /**
     * 创建空文件
     */
    private static void createRecipeFiles() {
        FileUtils.createFiles();
        FileUtils.writeToNewFile("banBlock.recipes", "", false);
        FileUtils.writeToNewFile("dropCrafting.recipes", "", false);
        FileUtils.writeToNewFile("multiExplosion.recipes", "", false);
        FileUtils.writeToNewFile("explosion.recipes", "", false);
        FileUtils.writeToNewFile("deputy.recipes", "", false);
        FileUtils.writeToNewFile("lightning.recipes", "", false);
    }
}
