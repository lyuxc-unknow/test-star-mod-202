package me.lyuxc.mind.item.tools;

import me.lyuxc.mind.Tiers;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class Level8Sword extends SwordItem {
    public Level8Sword(Properties properties) {
        super(Tiers.LEVEL8, properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pTarget.setHealth(pTarget.getHealth() - 50);
        if (pAttacker instanceof Player) {
            pAttacker.setHealth(pAttacker.getHealth() - 10);
        }
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }
}
