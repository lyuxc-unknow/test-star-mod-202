package me.lyuxc.mind.item.items;

import me.lyuxc.mind.AttachmentTypes;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class SpiritualFood extends Item {
    public SpiritualFood(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        if (pLivingEntity instanceof Player player) {
            player.addEffect(new MobEffectInstance(MobEffects.SATURATION, 99999 * 20, 255));
            player.setData(AttachmentTypes.INF_ATTACHMENT.get(),player.getData(AttachmentTypes.INF_ATTACHMENT.get()) + 10);
        }
        return super.finishUsingItem(pStack, pLevel, pLivingEntity);
    }
}
