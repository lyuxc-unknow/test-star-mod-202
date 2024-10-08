package me.lyuxc.mind.block.renderer;

import me.lyuxc.mind.Star;
import me.lyuxc.mind.block.blockItem.CreativeGeneratorBlockItem;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class CreativeGeneratorBlockItemRenderer extends GeoItemRenderer<CreativeGeneratorBlockItem> {
    public CreativeGeneratorBlockItemRenderer() {
        super(new DefaultedItemGeoModel<>(Star.rl("creative_generator")));
    }
}
