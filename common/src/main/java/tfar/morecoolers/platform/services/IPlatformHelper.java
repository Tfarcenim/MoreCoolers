package tfar.morecoolers.platform.services;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.state.BlockState;
import tfar.morecoolers.CoolerBlockEntity;
import tfar.morecoolers.platform.MLConfig;

public interface IPlatformHelper {

    /**
     * Gets the name of the current platform
     *
     * @return The name of the current platform.
     */
    String getPlatformName();

    /**
     * Checks if a mod with the given id is loaded.
     *
     * @param modId The mod to check if it is loaded.
     * @return True if the mod is loaded, false otherwise.
     */
    boolean isModLoaded(String modId);

    /**
     * Check if the game is currently in a development environment.
     *
     * @return True if in a development environment, false otherwise.
     */
    boolean isDevelopmentEnvironment();

    /**
     * Gets the name of the environment type as a string.
     *
     * @return The name of the environment type.
     */
    default String getEnvironmentName() {

        return isDevelopmentEnvironment() ? "development" : "production";
    }

    <F> void registerAll(Class<?> clazz, Registry<F> registry, Class<F> filter);


    <C extends CoolerBlockEntity> C copper(BlockPos pos, BlockState state);
    MLConfig getConfig();

}