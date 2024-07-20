package tfar.morecoolers.init;

import tfar.morecoolers.CoolerBlockEntity;
import tfar.morecoolers.CoolerBlockEntityType;
import tfar.morecoolers.platform.Services;

import java.util.Set;

public class ModBlockEntities {
    public static final CoolerBlockEntityType<? extends CoolerBlockEntity> COPPER_COOLER = new CoolerBlockEntityType<>(Services.PLATFORM::copper, Set.of(ModBlocks.COPPER_COOLER),null, Services.PLATFORM.getConfig()::copper);
}
