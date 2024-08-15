package me.lyuxc.topcompat.util;

import aztech.modern_industrialization.pipes.impl.PipeBlockEntity;
import aztech.modern_industrialization.pipes.impl.PipeVoxelShape;
import mcjty.theoneprobe.api.IProbeHitData;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.apache.commons.lang3.StringUtils;

public class TOPUtils {
    public static PipeVoxelShape getPipeVoxelShape(PipeBlockEntity pipeBlock, IProbeHitData iProbeHitData) {
        Vec3 hitPos = iProbeHitData.getHitVec();
        BlockPos blockPos = iProbeHitData.getPos();
        if (pipeBlock != null) {
            for (PipeVoxelShape partShape : pipeBlock.getPartShapes()) {
                Vec3 posInBlock = hitPos.subtract(blockPos.getX(), blockPos.getY(), blockPos.getZ());
                for (AABB box : partShape.shape.toAabbs()) {
                    Vec3 dir = box.getCenter().subtract(posInBlock).normalize().scale(1e-4);
                    if (box.contains(posInBlock.add(dir))) {
                        return partShape;
                    }
                }
            }
        }
        return null;
    }
    /**
     * 返回指定偏移量和最大宽度下的滚动文本
     * @param text 要显示的原始文本
     * @param maxDisplayLength 最大显示字符数
     * @param scrollOffset 滚动偏移量
     * @return 处理后的滚动文本
     */
    public static String getScrollingText(String text, int maxDisplayLength, int scrollOffset) {
        int textLength = text.length();

        if (textLength <= maxDisplayLength) {
            return text;  // 如果文本长度小于最大显示长度，则直接返回原文本
        }

        // 扩展文本，使其循环滚动时能完整展示
        String extendedText = text + StringUtils.repeat(" ", maxDisplayLength) + text;

        // 确定当前显示的文本部分
        int startIndex = scrollOffset % (textLength + maxDisplayLength);
        return extendedText.substring(startIndex, startIndex + maxDisplayLength);
    }
    /**
     * 计算字符串的自定义长度
     * @param text 要计算的字符串
     * @return 计算后的长度
     */
    public static int calculateCustomLength(String text) {
        int totalLength = 0;
        for (char ch : text.toCharArray()) {
            if (isAlphanumeric(ch)) {
                totalLength += 4;  // 如果是字母或数字，加8
            } else {
                totalLength += 10; // 其他字符，加16
            }
        }
        return totalLength;
    }

    /**
     * 判断字符是否是字母或数字
     * @param ch 要判断的字符
     * @return 是字母或数字则返回true，否则返回false
     */
    private static boolean isAlphanumeric(char ch) {
        return Character.isLetterOrDigit(ch);
    }
}
