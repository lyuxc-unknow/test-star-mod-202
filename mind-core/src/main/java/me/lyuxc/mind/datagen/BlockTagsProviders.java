package me.lyuxc.mind.datagen;

import me.lyuxc.mind.block.BlockRegistry;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.CompletableFuture;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class BlockTagsProviders extends BlockTagsProvider {
    public BlockTagsProviders(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, String modId, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, modId, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(BlockRegistry.EXAMPLE_BLOCK.get())
                .add(BlockRegistry.STAR_BLOCK.get())
                .add(BlockRegistry.SUPER_GENERATOR.get())
                .add(BlockRegistry.CIRCLE_BLOCK.get())
                .add(BlockRegistry.FAN_BLOCK.get());
        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(BlockRegistry.CIRCLE_BLOCK.get())
                .add(BlockRegistry.STAR_BLOCK.get());
//        tag(Tags.Blocks.STORAGE_BLOCKS_NETHERITE).add(BlockRegistry.STAR_BLOCK.get());
    }
}
