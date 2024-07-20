package tfar.morecoolers;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.jetbrains.annotations.Nullable;
import tfar.morecoolers.init.ModBlockEntities;

import javax.annotation.Nonnull;

public class CoolerBlockEntityForge extends CoolerBlockEntity {
    public CoolerBlockEntityForge(CoolerBlockEntityType<?> $$0, BlockPos $$1, BlockState $$2) {
        super($$0, $$1, $$2);
    }


    public static CoolerBlockEntityForge copper(BlockPos pos,BlockState state) {
        return new CoolerBlockEntityForge(ModBlockEntities.COPPER_COOLER,pos,state);
    }

    protected LazyOptional<IItemHandler> optional;

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap == ForgeCapabilities.ITEM_HANDLER ? optional.cast() : super.getCapability(cap, side);
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        optional.invalidate();
    }

    @Override
    public void reviveCaps() {
        super.reviveCaps();
        optional = LazyOptional.of(() -> new InvWrapper(container));
    }


}