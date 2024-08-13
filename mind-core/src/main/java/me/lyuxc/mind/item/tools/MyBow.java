package me.lyuxc.mind.item.tools;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class MyBow extends BowItem {
    public MyBow(Properties properties) {
        super(properties);
    }

    @Override
    public float getDestroySpeed(ItemStack p_41425_, BlockState p_41426_) {
        return -1;
    }

    @Override
    public boolean hurtEnemy(ItemStack p_41395_, LivingEntity pAtt, LivingEntity player) {
        pAtt.hurt(new DamageSource(player.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.PLAYER_ATTACK)), 9999F);
        return super.hurtEnemy(p_41395_, pAtt, player);
    }

    @Override
    public int getUseDuration(ItemStack pStack, LivingEntity pTarget) {
        return -75000;
    }
}
