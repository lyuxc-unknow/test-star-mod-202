package me.lyuxc.mind.block.renderer;

import me.lyuxc.mind.block.blockItem.CircleBlockItem;
import me.lyuxc.mind.block.model.CircleBlockItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class CircleBlockItemRenderer extends GeoItemRenderer<CircleBlockItem> {
    public CircleBlockItemRenderer() {
        super(new CircleBlockItemModel());
    }
}
