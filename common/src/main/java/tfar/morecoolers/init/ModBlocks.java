package tfar.morecoolers.init;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import tfar.morecoolers.CoolerBlock;
import tfar.morecoolers.platform.Services;

public class ModBlocks {
    public static final Block COPPER_COOLER = new CoolerBlock(BlockBehaviour.Properties.of(), Services.PLATFORM::copper);
}
