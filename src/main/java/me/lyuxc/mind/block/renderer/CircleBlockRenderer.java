package me.lyuxc.mind.block.renderer;

import me.lyuxc.mind.block.blockEntity.CircleBlockEntity;
import me.lyuxc.mind.block.model.CircleBlockModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class CircleBlockRenderer extends GeoBlockRenderer<CircleBlockEntity> {
    public CircleBlockRenderer(BlockEntityRendererProvider.Context context) {
        super(new CircleBlockModel());
    }
}
