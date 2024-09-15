package me.lyuxc.mind.datagen;

import me.lyuxc.mind.Variables;
import me.lyuxc.mind.dimension.MindMineWorld;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModWorldGen extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.LEVEL_STEM, MindMineWorld::bootstrapStem)
            .add(Registries.DIMENSION_TYPE,MindMineWorld::bootstrapType)
            ;

    public ModWorldGen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(Variables.MOD_ID));
    }
}
