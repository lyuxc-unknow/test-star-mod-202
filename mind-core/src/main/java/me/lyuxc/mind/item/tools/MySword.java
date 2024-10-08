package me.lyuxc.mind.item.tools;

import me.lyuxc.mind.Tiers;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.component.Unbreakable;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class MySword extends SwordItem {
    //品质,伤害,攻速,属性
    public MySword() {
        super(Tiers.LEVEL_INF, new Item.Properties()
                .component(DataComponents.UNBREAKABLE,new Unbreakable(true))
                .attributes(SwordItem.createAttributes(Tiers.LEVEL_INF, (int) Tiers.LEVEL_INF.getAttackDamageBonus(), Tiers.LEVEL_INF.getSpeed()))
        );
    }

    //是否可以攻击（破坏）方块
    @Override
    public boolean canAttackBlock(BlockState blockState, Level level, BlockPos blockPos, Player player) {
        return !player.isCreative();
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pTarget.hurt(pAttacker.damageSources().source(DamageTypes.FELL_OUT_OF_WORLD), Integer.MAX_VALUE);
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (!pLevel.isClientSide()) {
            AABB aabb = pPlayer.getBoundingBox().deflate(48);
            List<Entity> entityList = pLevel.getEntities(pPlayer, aabb);
            DamageSource damageSource = pPlayer.damageSources().source(DamageTypes.EXPLOSION);
            if(entityList.isEmpty()) return InteractionResultHolder.fail(pPlayer.getItemInHand(pUsedHand));
            if(!pPlayer.isCreative()) pPlayer.getCooldowns().addCooldown(pPlayer.getItemInHand(pUsedHand).getItem(), 100);
            for(Entity entity :entityList) {
                if ((pPlayer.getInventory().getFreeSlot() != -1 && entity instanceof ItemEntity)||entity instanceof ExperienceOrb) {
                    entity.absMoveTo(pPlayer.getX(),pPlayer.getY(),pPlayer.getZ());
                }else if(entity instanceof EnderDragon enderDragon) {
                    enderDragon.hurt(enderDragon.head,damageSource,Integer.MAX_VALUE);
                } else /*if(entity instanceof LivingEntity)*/ {
                    Entity light = new LightningBolt(EntityType.LIGHTNING_BOLT, pLevel);
                    light.moveTo(entity.getX(), entity.getY() + 4, entity.getZ());
                    pLevel.addFreshEntity(light);
                    entity.hurt(damageSource, Integer.MAX_VALUE);
                }
            }
        }
        return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
    }
}
