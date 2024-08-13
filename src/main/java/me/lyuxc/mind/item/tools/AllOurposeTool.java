package me.lyuxc.mind.item.tools;

import me.lyuxc.mind.Star;
import me.lyuxc.mind.Tiers;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class AllOurposeTool extends DiggerItem {
    private final Tier TIER;
    public AllOurposeTool(Tier pTier) {
        super(pTier,
                TagKey.create(Registries.BLOCK, Star.rl("mineable/aotools")),
                new Properties().durability(pTier.getUses() * 5).attributes(SwordItem.createAttributes(pTier, (int) pTier.getAttackDamageBonus(), pTier.getSpeed())));
        this.TIER = pTier;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (!pLevel.isClientSide) {
            if(TIER == Tiers.LEVEL_INF) {
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
        }
        return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
    }

    public Tier getTier() {
        return TIER;
    }
}
