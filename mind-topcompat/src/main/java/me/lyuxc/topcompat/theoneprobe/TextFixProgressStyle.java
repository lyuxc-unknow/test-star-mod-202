package me.lyuxc.topcompat.theoneprobe;

import mcjty.theoneprobe.apiimpl.styles.ProgressStyle;
import me.lyuxc.topcompat.util.TOPUtils;

public class TextFixProgressStyle extends ProgressStyle implements ITextFixProgressStyle{
    public TextFixProgressStyle() {
        super();
    }

    @Override
    public TextFixProgressStyle fixProgress(long current) {
        int textWidth = TOPUtils.calculateCustomLength(getPrefix()+current+getSuffix());
        width(Math.max(textWidth+8, getWidth()));
        return this;
    }
}
