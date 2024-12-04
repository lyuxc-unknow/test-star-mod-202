package me.lyuxc.mind.block.renderer;

import me.lyuxc.mind.block.blockItem.CreativeGeneratorBlockItem;
import me.lyuxc.mind.block.model.CreativeGeneratorBlockItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class CreativeGeneratorBlockItemRenderer extends GeoItemRenderer<CreativeGeneratorBlockItem> {
    public CreativeGeneratorBlockItemRenderer() {
        super(new CreativeGeneratorBlockItemModel());
    }
}
