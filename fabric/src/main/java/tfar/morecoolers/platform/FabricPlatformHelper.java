package tfar.morecoolers.platform;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.state.BlockState;
import tfar.morecoolers.CoolerBlockEntity;
import tfar.morecoolers.platform.services.IPlatformHelper;
import net.fabricmc.loader.api.FabricLoader;

public class FabricPlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public <F> void registerAll(Class<?> clazz, Registry<F> registry, Class<F> filter) {

    }

    @Override
    public <C extends CoolerBlockEntity> C copper(BlockPos pos, BlockState state) {
        return null;
    }

    @Override
    public <C extends CoolerBlockEntity> C iron(BlockPos pos, BlockState state) {
        return null;
    }

    @Override
    public <C extends CoolerBlockEntity> C gold(BlockPos pos, BlockState state) {
        return null;
    }

    @Override
    public <C extends CoolerBlockEntity> C diamond(BlockPos pos, BlockState state) {
        return null;
    }

    @Override
    public MLConfig getConfig() {
        return null;
    }
}
