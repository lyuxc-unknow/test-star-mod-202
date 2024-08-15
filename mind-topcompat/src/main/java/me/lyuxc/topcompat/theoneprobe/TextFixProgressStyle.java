package me.lyuxc.topcompat.theoneprobe;

import mcjty.theoneprobe.apiimpl.styles.ProgressStyle;
import me.lyuxc.topcompat.util.TOPUtils;

public class TextFixProgressStyle extends ProgressStyle implements ITextFixProgressStyle{
    public TextFixProgressStyle() {
        super();
    }

    @Override
    public TextFixProgressStyle fixProgress(long current) {
        int textWidth = TOPUtils.calculateCustomLength(this.getPrefix()+current+this.getSuffix());
        this.width(Math.max(textWidth+8, this.getWidth()));
        return this;
    }

    @Override
    public TextFixProgressStyle fixProgress(String text) {
        this.prefix(text);
        int textWidth = TOPUtils.calculateCustomLength(text);
        this.width(Math.max(textWidth+8, this.getWidth()));
        return this;
    }
}
