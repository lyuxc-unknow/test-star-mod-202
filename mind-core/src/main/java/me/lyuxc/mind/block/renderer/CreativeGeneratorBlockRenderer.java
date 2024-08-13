package me.lyuxc.mind.block.renderer;

import me.lyuxc.mind.block.blockEntity.CreativeGeneratorBlockEntity;
import me.lyuxc.mind.block.model.CreativeGeneratorBlockModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class CreativeGeneratorBlockRenderer extends GeoBlockRenderer<CreativeGeneratorBlockEntity> {
    public CreativeGeneratorBlockRenderer(BlockEntityRendererProvider.Context context) {
        super(new CreativeGeneratorBlockModel());
    }
}
