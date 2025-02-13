package me.lyuxc.mind.block.blocks;

import me.lyuxc.mind.Variables;
import me.lyuxc.mind.utils.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class FanBlock extends Block {
    public FanBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if (pEntity instanceof Player player) {
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, Utils.getTime(3), 10, false, false));
        } else {
            pEntity.moveTo(pEntity.getX(), pEntity.getY() + Variables.random.nextInt(50), pEntity.getZ());
        }
        super.stepOn(pLevel, pPos, pState, pEntity);
    }
}
