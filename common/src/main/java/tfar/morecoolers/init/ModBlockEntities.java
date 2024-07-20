package tfar.morecoolers.init;

import tfar.morecoolers.CoolerBlockEntity;
import tfar.morecoolers.CoolerBlockEntityType;
import tfar.morecoolers.platform.Services;

import java.util.Set;

public class ModBlockEntities {
    public static final CoolerBlockEntityType<? extends CoolerBlockEntity> COPPER_COOLER = new CoolerBlockEntityType<>(Services.PLATFORM::copper, Set.of(ModBlocks.COPPER_COOLER),null, Services.PLATFORM.getConfig()::copper);
    public static final CoolerBlockEntityType<? extends CoolerBlockEntity> IRON_COOLER = new CoolerBlockEntityType<>(Services.PLATFORM::iron, Set.of(ModBlocks.IRON_COOLER),null, Services.PLATFORM.getConfig()::iron);
    public static final CoolerBlockEntityType<? extends CoolerBlockEntity> GOLD_COOLER = new CoolerBlockEntityType<>(Services.PLATFORM::gold, Set.of(ModBlocks.GOLD_COOLER),null, Services.PLATFORM.getConfig()::gold);
    public static final CoolerBlockEntityType<? extends CoolerBlockEntity> DIAMOND_COOLER = new CoolerBlockEntityType<>(Services.PLATFORM::diamond, Set.of(ModBlocks.DIAMOND_COOLER),null, Services.PLATFORM.getConfig()::diamond);

}
