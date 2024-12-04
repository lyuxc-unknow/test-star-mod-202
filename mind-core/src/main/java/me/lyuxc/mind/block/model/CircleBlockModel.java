package me.lyuxc.mind.block.model;

import me.lyuxc.mind.Star;
import me.lyuxc.mind.block.blockEntity.CircleBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CircleBlockModel extends GeoModel<CircleBlockEntity> {
    @Override
    public ResourceLocation getModelResource(CircleBlockEntity block) {
        return Star.rl("geo/circle.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CircleBlockEntity block) {
        return Star.rl("textures/block/circle.png");
    }

    @Override
    public ResourceLocation getAnimationResource(CircleBlockEntity block) {
        return Star.rl("animations/circle.animation.json");
    }
}
