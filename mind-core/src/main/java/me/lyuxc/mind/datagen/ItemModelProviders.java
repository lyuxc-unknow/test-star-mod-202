package me.lyuxc.mind.datagen;

import me.lyuxc.mind.Variables;
import me.lyuxc.mind.block.BlockRegistry;
import me.lyuxc.mind.item.ItemRegistry;
import me.lyuxc.mind.utils.Utils;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ItemModelProviders extends ItemModelProvider {

    public ItemModelProviders(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Variables.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        //方块模型继承方块状态
        withExistingParent(BlockRegistry.EXAMPLE_BLOCK.getId().getPath(), modLoc("block/example_block"));
        withExistingParent(BlockRegistry.STAR_BLOCK.getId().getPath(), modLoc("block/star_block"));
        withExistingParent(BlockRegistry.FAN_BLOCK.getId().getPath(), modLoc("block/fan_block"));
        //基础物品注册
        basicItem(ItemRegistry.LEVEL1SWORD.getId());
        basicItem(ItemRegistry.LEVEL2SWORD.getId());
        basicItem(ItemRegistry.LEVEL3SWORD.getId());
        basicItem(ItemRegistry.LEVEL4SWORD.getId());
        basicItem(ItemRegistry.LEVEL5SWORD.getId());
        basicItem(ItemRegistry.LEVEL6SWORD.getId());
        basicItem(ItemRegistry.LEVEL7SWORD.getId());
        basicItem(ItemRegistry.LEVEL8SWORD.getId());
        basicItem(ItemRegistry.LEVEL1ITEM.get());
        basicItem(ItemRegistry.LEVEL2ITEM.get());
        basicItem(ItemRegistry.LEVEL3ITEM.get());
        basicItem(ItemRegistry.LEVEL4ITEM.get());
        basicItem(ItemRegistry.LEVEL5ITEM.get());
        basicItem(ItemRegistry.LEVEL6ITEM.get());
        basicItem(ItemRegistry.LEVEL7ITEM.get());
        basicItem(ItemRegistry.LEVEL8ITEM.get());
        basicItem(ItemRegistry.MEDICAL_BOX.get());
        basicItem(ItemRegistry.EXAMPLE_ITEM.getId());
        basicItem(ItemRegistry.MY_SWORD.getId());
        basicItem(ItemRegistry.WATER_GETTER.getId());
        basicItem(ItemRegistry.END_ITEM.get());
        basicItem(ItemRegistry.GAZE_OF_CAPITAL.get());
        basicItem(ItemRegistry.SPIRITUAL_FOOD.get());
        basicItem(ItemRegistry.MOD_BLOCK_MEDIUM.get());
        basicItem(ItemRegistry.MOD_BLOCK_DATA.get());
        basicItem(ItemRegistry.MOD_BLOCK_DREAM.getId());
        basicItem(ItemRegistry.MOD_BLOCK_LIQUID.getId());
        basicItem(ItemRegistry.MOD_BLOCK_METAL.get());
        basicItem(ItemRegistry.TETANUS_BLADE.get());
        basicItem(ItemRegistry.MULTIPLAYER_TOOL.get());
        basicItem(ItemRegistry.WOOD_SHEARS.get());
        basicItem(ItemRegistry.WOOD_SAW_BLADE.get());
        basicItem(ItemRegistry.ALL_OURPOSE_TOOL_1.get());
        basicItem(ItemRegistry.ALL_OURPOSE_TOOL_2.get());
        basicItem(ItemRegistry.ALL_OURPOSE_TOOL_3.get());
        basicItem(ItemRegistry.ALL_OURPOSE_TOOL_4.get());
        basicItem(ItemRegistry.ALL_OURPOSE_TOOL_5.get());
        basicItem(ItemRegistry.ALL_OURPOSE_TOOL_6.get());
        basicItem(ItemRegistry.ALL_OURPOSE_TOOL_7.get());
        basicItem(ItemRegistry.ALL_OURPOSE_TOOL_8.get());
        basicItem(ItemRegistry.ALL_OURPOSE_TOOL_INF.get());
//        basicItem(ItemRegistry.SOLID_STATE_ENERGY.get());
//        basicItem(ItemRegistry.SOLID_STATE_ENERGY_02X.get());
//        basicItem(ItemRegistry.SOLID_STATE_ENERGY_100X.get());
//        basicItem(ItemRegistry.SOLID_STATE_ENERGY_MAX.get());
        for(var i=0;i<20;i++) {
            basicItem(Utils.getItem("test_star_core:package_" + i));
        }
    }
}
