package tfar.morecoolers;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.LockCode;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class CoolerBlockEntity extends BlockEntity implements MenuProvider {

    protected final int rows;
    protected final Container container;
    @Nullable
    private Component name;
    public CoolerBlockEntity(CoolerBlockEntityType<?> type, BlockPos $$1, BlockState $$2) {
        super(type, $$1, $$2);
        rows = type.getRows();
        container = new SimpleContainer(rows*9);
    }

    public int calculateRedstone() {
        return AbstractContainerMenu.getRedstoneSignalFromContainer(container);
    }

    public void setCustomName(Component pName) {
        this.name = pName;
    }

    public Component getName() {
        return this.name != null ? this.name : this.getDefaultName();
    }

    @Override
    public Component getDisplayName() {
        return this.getName();
    }

    @Nullable
    public Component getCustomName() {
        return this.name;
    }

    protected Component getDefaultName() {
        return getBlockState().getBlock().getName();
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        switch (rows) {
            case 1 -> {
                return new ChestMenu(MenuType.GENERIC_9x1,i,inventory,container,rows * 9);
            }
            case 2 -> {
                return new ChestMenu(MenuType.GENERIC_9x2,i,inventory,container,rows * 9);
            }
            case 3 -> {
                return new ChestMenu(MenuType.GENERIC_9x3,i,inventory,container,rows * 9);
            }
            case 4 -> {
                return new ChestMenu(MenuType.GENERIC_9x4,i,inventory,container,rows * 9);
            }
            case 5 -> {
                return new ChestMenu(MenuType.GENERIC_9x5,i,inventory,container,rows * 9);
            }
            case 6 -> {
                return new ChestMenu(MenuType.GENERIC_9x6,i,inventory,container,rows * 9);
            }
        }
        return null;
    }


    public void load(CompoundTag pTag) {
        super.load(pTag);
        if (pTag.contains("CustomName", Tag.TAG_STRING)) {
            this.name = Component.Serializer.fromJson(pTag.getString("CustomName"));
        }

    }

    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        if (this.name != null) {
            pTag.putString("CustomName", Component.Serializer.toJson(this.name));
        }

    }


}
