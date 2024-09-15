package me.lyuxc.mind.dimension;

import com.mojang.datafixers.util.Pair;
import me.lyuxc.mind.Star;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;

import java.util.List;
import java.util.OptionalLong;

public class MindMineWorld {
    public static final ResourceKey<LevelStem> MINE_MINE_STEM_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            Star.rl("mind_mine_dim"));
//    public static final ResourceKey<Level> MINE_MINE_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
//            Star.rl("mind_mine_dim"));
    public static final ResourceKey<DimensionType> MINE_MINE_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            Star.rl("mind_mine_dim_type"));

    // 生成DimensionType 对应的json
    public static void bootstrapType(BootstrapContext<DimensionType> context) {
        // 详细的参数含义看wiki
        context.register(MINE_MINE_DIM_TYPE, new DimensionType(
                OptionalLong.of(12000), // 是否是固定时间,即固定在白天或者夜晚的某一个时间
                false, // 天空光照
                false, // 是否类似地狱有顶层
                false, // ultraWarm
                false, // natural
                2.0, // 与主世界坐标比例
                true, // 是否可以使用床
                false, // 是否可以使用重生锚
                -64, // 最低Y轴
                384, // 世界高度
                75, // 逻辑高度
                BlockTags.INFINIBURN_OVERWORLD, // infiniburn
                BuiltinDimensionTypes.OVERWORLD_EFFECTS, // effectsLocation
                1.0f, // 环境光
                new DimensionType.MonsterSettings(false, false, ConstantInt.of(0), 0)));//怪物生成
    }

    // 生成dimension的json文件
    public static void bootstrapStem(BootstrapContext<LevelStem> context) {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);
        HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<NoiseGeneratorSettings> noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);

        // 这里的代码都说过了。
        NoiseBasedChunkGenerator noiseBasedChunkGenerator = new NoiseBasedChunkGenerator(
                MultiNoiseBiomeSource.createFromList(
                        new Climate.ParameterList<>(List.of(
                                Pair.of(
                                        Climate.parameters(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F), biomeRegistry.getOrThrow(Biomes.PLAINS))
                        ))),
                noiseGenSettings.getOrThrow(NoiseGeneratorSettings.OVERWORLD));
        // 一个stem 给context
        LevelStem stem = new LevelStem(dimTypes.getOrThrow(MindMineWorld.MINE_MINE_DIM_TYPE), noiseBasedChunkGenerator);

        context.register(MindMineWorld.MINE_MINE_STEM_KEY, stem);
    }
}
