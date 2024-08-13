package me.lyuxc.multiblock.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.Map;

public class Utils {
    /**
     * @param level 需要获取方块的世界
     * @param pos 需要获取方块的位置
     * */
    public static Block getBlock(Level level, BlockPos pos) {
        return level.getBlockState(pos).getBlock();
    }
    /**
     * @param level 需要设置方块的世界
     * @param pos 需要设置方块的位置
     * */
    public static void setBlock(Level level, BlockPos pos,Block block) {
        level.setBlock(pos,block.defaultBlockState(),2);
    }
    /**
     * @param level 需要判断结构的世界
     * @param pos 需要判断结构的位置
     * @param matrix 需要判断结构的矩阵图
     * @param blockMap 方块表(包含需要判断的所有方块)
     * @param yAxis 触发判断的位置的Y轴偏移量
     * @param size 矩阵大小(matrix的长宽，这样写matrix只能是正方形结构)
     * @param offset 二次偏移量(X轴或者Z轴距离触发点的距离)
     * */
    public static boolean isValid(Level level, BlockPos pos, String[] matrix, Map<Character,Block> blockMap, int yAxis, int size, int offset) {
        boolean valid = false;
        for(int i = 0;i<size;i++) {
            for(int j = 0;j<size;j++) {
                char key = matrix[i].charAt(j);
                if(key == 'X') continue;
                valid = checkBlock(key,level,pos,blockMap,i,yAxis,j,offset);
                if(!valid) break;
            }
            if(!valid) break;
        }
        return valid;
    }
    /**
     * @param blockId 矩阵中的方块代称
     * @param level 需要判断结构的世界
     * @param pos 需要判断结构的位置
     * @param blockMap 方块表(包含需要判断的所有方块)
     * @param x X轴偏移量
     * @param y Y轴偏移量
     * @param z Z轴偏移量
     * @param offset 二次偏移量(X轴或者Z轴距离触发点的距离)
     * */
    public static boolean checkBlock(char blockId,Level level,BlockPos pos,Map<Character,Block> blockMap,int x,int y,int z,int offset) {
        return getBlock(level, pos.offset(x - offset, y, z - offset)) == blockMap.get(blockId);
    }
}
