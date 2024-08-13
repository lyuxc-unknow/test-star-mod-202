package me.lyuxc.mind.block.model;

import me.lyuxc.mind.Star;
import me.lyuxc.mind.block.blockItem.CreativeGeneratorBlockItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CreativeGeneratorBlockItemModel extends GeoModel<CreativeGeneratorBlockItem> {
    @Override
    public ResourceLocation getModelResource(CreativeGeneratorBlockItem creativeGeneratorBlockItem) {
        return Star.rl( "geo/creative_generator.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CreativeGeneratorBlockItem creativeGeneratorBlockItem) {
        return Star.rl("textures/block/creative_generator.png");
    }

    @Override
    public ResourceLocation getAnimationResource(CreativeGeneratorBlockItem creativeGeneratorBlockItem) {
        return Star.rl("animations/creative_generator.animation.json");
    }
}
