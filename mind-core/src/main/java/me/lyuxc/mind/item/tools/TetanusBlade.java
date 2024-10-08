package me.lyuxc.mind.item.tools;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.TooltipFlag;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
public class TetanusBlade extends SwordItem {
    public TetanusBlade(Properties pProperties) {
        super(Tiers.IRON, pProperties);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        float attackerHealth = pAttacker.getHealth();
        if (attackerHealth > 1) {
            attackerHealth /= 2;
            pTarget.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.01);
            pTarget.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(0);
            pTarget.getAttribute(Attributes.ATTACK_KNOCKBACK).setBaseValue(0);
            pTarget.getAttribute(Attributes.LUCK).setBaseValue(-1024);
            pAttacker.setHealth(attackerHealth);
        }
        return true;
    }
    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext tooltipContext, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
         super.appendHoverText(pStack, tooltipContext, pTooltipComponents, pIsAdvanced);
    }
}
