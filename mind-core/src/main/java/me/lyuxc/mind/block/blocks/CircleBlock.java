package me.lyuxc.mind.block.blocks;

import me.lyuxc.mind.block.blockEntity.CircleBlockEntity;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class CircleBlock extends Block implements EntityBlock {
    public CircleBlock() {
        super(Properties.of()
                .strength(5.0f)
                .noOcclusion()
        );
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new CircleBlockEntity(blockPos, blockState);
    }

    @SuppressWarnings("all")
    @Override
    protected RenderShape getRenderShape(BlockState pState) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (!level.isClientSide) {
            return (level1, pos, state1, be) -> {
                if (be instanceof CircleBlockEntity block) {
                    block.tickServer(level1, pos);
                }
            };
        }
        return null;
    }

    @Override
    protected VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.1875, 0.0625, 0.6875, 0.3125, 0.4375, 0.8125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.6875, 0.0625, 0.6875, 0.8125, 0.4375, 0.8125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.1875, 0.0625, 0.1875, 0.3125, 0.4375, 0.3125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.6875, 0.0625, 0.1875, 0.8125, 0.4375, 0.3125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.0625, -0.375, 0.8125, 0.1875, -0.0625, 0.9375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.8125, -0.375, 0.8125, 0.9375, -0.0625, 0.9375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.0625, -0.375, 0.0625, 0.1875, -0.0625, 0.1875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.8125, -0.375, 0.0625, 0.9375, -0.0625, 0.1875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.375, 0.6875, 0.375, 0.625, 0.75, 0.625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.4375, 0.75, 0.4375, 0.5625, 1.5, 0.5625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.75, -0.0625, 0.125, 0.875, 0.0625, 0.25), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.75, -0.0625, 0.75, 0.875, 0.0625, 0.875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.125, -0.0625, 0.125, 0.25, 0.0625, 0.25), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.125, -0.0625, 0.75, 0.25, 0.0625, 0.875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.375, 1.5, 0.375, 0.625, 1.5625, 0.625), BooleanOp.OR);

        return shape;
    }
}
