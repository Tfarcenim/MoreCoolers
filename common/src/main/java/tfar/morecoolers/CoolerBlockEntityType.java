package tfar.morecoolers;

import com.mojang.datafixers.types.Type;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.Set;
import java.util.function.IntSupplier;

public class CoolerBlockEntityType<T extends CoolerBlockEntity> extends BlockEntityType<T> {
    private final IntSupplier rows;

    public CoolerBlockEntityType(BlockEntitySupplier<? extends T> $$0, Set<Block> $$1, Type<?> $$2, IntSupplier rows) {
        super($$0, $$1, $$2);
        this.rows = rows;
    }

    public int getRows() {
        return rows.getAsInt();
    }
}
