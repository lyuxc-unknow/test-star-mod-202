package me.lyuxc.mind.item.tools;

import me.lyuxc.mind.Tiers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class Level1Sword extends SwordItem {
    public Level1Sword(Properties properties) {
        super(Tiers.LEVEL1, properties);
    }
    //是否可以攻击（破坏）方块
    @Override
    public boolean canAttackBlock(BlockState blockState, Level level, BlockPos blockPos, Player player) {
        return false;
    }
}
