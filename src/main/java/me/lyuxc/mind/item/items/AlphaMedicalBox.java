package me.lyuxc.mind.item.items;

import me.lyuxc.mind.Variables;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class AlphaMedicalBox extends Item {
    public AlphaMedicalBox(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        if (pLivingEntity instanceof Player) {
            pLivingEntity.setHealth((float) Variables.MAX_HEALTH + pLivingEntity.getHealth());
        }
        return super.finishUsingItem(pStack, pLevel, pLivingEntity);
    }
}
