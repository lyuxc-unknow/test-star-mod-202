package me.lyuxc.mind.datagen;

import me.lyuxc.mind.Variables;
import me.lyuxc.mind.block.BlockRegistry;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;


public class BlockStateProviders extends BlockStateProvider {
    public BlockStateProviders(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Variables.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(BlockRegistry.EXAMPLE_BLOCK.get());
        simpleBlock(BlockRegistry.STAR_BLOCK.get());
        simpleBlock(BlockRegistry.FAN_BLOCK.get());
    }
}
