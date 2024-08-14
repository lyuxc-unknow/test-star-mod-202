package me.lyuxc.topcompat.theoneprobe.TestStar;

import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ProbeMode;
import me.lyuxc.mind.block.blockEntity.SuperGeneratorEntity;
import me.lyuxc.topcompat.TestStarTopCompat;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SuperGeneratorProvider implements IProbeInfoProvider {
//    int scrollOffset = 0;
    @Override
    public ResourceLocation getID() {
        return TestStarTopCompat.rl("super_generator");
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, Player player, Level level, BlockState blockState, IProbeHitData iProbeHitData) {
        BlockEntity blockEntity = level.getBlockEntity(iProbeHitData.getPos());
        if (blockEntity instanceof SuperGeneratorEntity entity) {
            iProbeInfo.horizontal()
                    .text("剩余燃烧时间",iProbeInfo.defaultTextStyle()
                            .padding(2))
                    .progress(entity.getBurnTime()/20,entity.getItemBurnTime()/20,iProbeInfo.defaultProgressStyle()
                            .width(40))
            ;
//            String displayText = getScrollingText("测试测试测试测试测试测试测试测试测试", 10, scrollOffset);
//            iProbeInfo.text(displayText);
//            iProbeInfo.progress(50,100,new ProgressStyle()
//                    .showText(false)
//                    .prefix(displayText)
//            );
//            scrollOffset++;
        }
    }
}
