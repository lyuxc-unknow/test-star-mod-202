package me.lyuxc.mind.compat.theoneprobe;

import mcjty.theoneprobe.apiimpl.client.ElementTextRender;
import mcjty.theoneprobe.apiimpl.styles.ProgressStyle;
import net.minecraft.network.chat.Component;

public class TextFixProgressStyle extends ProgressStyle implements ITextFixProgressStyle{
    public TextFixProgressStyle() {
        super();
    }

    @Override
    public TextFixProgressStyle fixProgress(long current) {
        int textWidth = ElementTextRender.getWidth(Component.literal(getPrefix()+current+getSuffix())) ;
        width(Math.max(textWidth+8, getWidth()));
        return this;
    }
}
