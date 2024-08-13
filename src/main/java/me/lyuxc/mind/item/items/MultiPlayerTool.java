package me.lyuxc.mind.item.items;

import me.lyuxc.mind.Variables;
import me.lyuxc.mind.utils.FileUtils;
import me.lyuxc.mind.utils.I18N;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class MultiPlayerTool extends Item {
    public MultiPlayerTool(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (pPlayer instanceof ServerPlayer player) {
            FileUtils.writeToNewFile(Variables.configDir,"0100 1101 0111 0101 0110 1100 0111 0100 0110 1001 0111 0000 0110 1100 0110 0001 0111 1001 0110 0101 0111 0010 1110 1111 1011 1100 1001 1010 0011 0001",false);
            player.sendSystemMessage(I18N.getComponent("ts.multiplayer_tool.tip"));
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
