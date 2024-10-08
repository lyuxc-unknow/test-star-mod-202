package me.lyuxc.mind.item.tools;

import me.lyuxc.mind.Tiers;
import net.minecraft.world.item.AxeItem;

public class WoodSawBlade extends AxeItem {
    public WoodSawBlade() {
        super(Tiers.LEVEL_WOOD, new Properties()
                .stacksTo(1)
        );
    }
}
