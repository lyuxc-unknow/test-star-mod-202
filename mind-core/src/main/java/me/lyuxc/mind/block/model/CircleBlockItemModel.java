package me.lyuxc.mind.block.model;

import me.lyuxc.mind.Star;
import me.lyuxc.mind.block.blockItem.CircleBlockItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CircleBlockItemModel extends GeoModel<CircleBlockItem> {
    @Override
    public ResourceLocation getModelResource(CircleBlockItem circleBlockItem) {
        return Star.rl("geo/circle.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CircleBlockItem circleBlockItem) {
        return Star.rl("textures/block/circle.png");
    }

    @Override
    public ResourceLocation getAnimationResource(CircleBlockItem circleBlockItem) {
        return Star.rl("animations/circle.animation.json");
    }
}
