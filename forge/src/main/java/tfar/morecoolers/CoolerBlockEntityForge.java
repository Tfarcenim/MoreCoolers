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

    public static CoolerBlockEntityForge iron(BlockPos pos,BlockState state) {
        return new CoolerBlockEntityForge(ModBlockEntities.IRON_COOLER,pos,state);
    }

    public static CoolerBlockEntityForge gold(BlockPos pos,BlockState state) {
        return new CoolerBlockEntityForge(ModBlockEntities.GOLD_COOLER,pos,state);
    }


    public static CoolerBlockEntityForge diamond(BlockPos pos,BlockState state) {
        return new CoolerBlockEntityForge(ModBlockEntities.DIAMOND_COOLER,pos,state);
    }


    protected LazyOptional<IItemHandler> optional = LazyOptional.of(() -> new InvWrapper(container));

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
