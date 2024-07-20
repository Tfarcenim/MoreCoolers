package tfar.morecoolers;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BarrelBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class CoolerBlock extends Block implements EntityBlock {
    private final BlockEntityType.BlockEntitySupplier<BlockEntity> supplier;

    public CoolerBlock(Properties $$0, BlockEntityType.BlockEntitySupplier<BlockEntity> supplier) {
        super($$0);
        this.supplier = supplier;
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState $$0) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState $$0, Level $$1, BlockPos $$2) {
        BlockEntity blockEntity = $$1.getBlockEntity($$2);
        return blockEntity instanceof CoolerBlockEntity coolerBlockEntity ? coolerBlockEntity.calculateRedstone() : 0;
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pLevel.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            BlockEntity blockentity = pLevel.getBlockEntity(pPos);
            if (blockentity instanceof CoolerBlockEntity) {
                pPlayer.openMenu((CoolerBlockEntity)blockentity);
                PiglinAi.angerNearbyPiglins(pPlayer, true);
            }

            return InteractionResult.CONSUME;
        }
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return supplier.create(blockPos,blockState);
    }

    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @javax.annotation.Nullable LivingEntity pPlacer, ItemStack pStack) {
        if (pStack.hasCustomHoverName()) {
            BlockEntity blockentity = pLevel.getBlockEntity(pPos);
            if (blockentity instanceof CoolerBlockEntity coolerBlockEntity) {
                coolerBlockEntity.setCustomName(pStack.getHoverName());
            }
        }
    }
}
