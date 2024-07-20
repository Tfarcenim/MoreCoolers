package tfar.morecoolers.platform;

import net.minecraft.core.BlockPos;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.commons.lang3.tuple.Pair;
import tfar.morecoolers.CoolerBlockEntity;
import tfar.morecoolers.CoolerBlockEntityForge;
import tfar.morecoolers.MoreCoolers;
import tfar.morecoolers.MoreCoolersForge;
import tfar.morecoolers.platform.services.IPlatformHelper;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Supplier;

public class ForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {

        return "Forge";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return !FMLLoader.isProduction();
    }

    @Override
    public <F> void registerAll(Class<?> clazz, Registry<F> registry, Class<F> filter) {
        List<Pair<ResourceLocation, Supplier<?>>> list = MoreCoolersForge.registerLater.computeIfAbsent(registry, k -> new ArrayList<>());
        for (Field field : clazz.getFields()) {
            MappedRegistry<?> forgeRegistry = (MappedRegistry<?>) registry;
            forgeRegistry.unfreeze();
            try {
                Object o = field.get(null);
                if (filter.isInstance(o)) {
                    list.add(Pair.of(MoreCoolers.id(field.getName().toLowerCase(Locale.ROOT)), () -> o));
                }
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        }
    }

    @Override
    public <C extends CoolerBlockEntity> C copper(BlockPos pos, BlockState state) {
        return (C) CoolerBlockEntityForge.copper(pos,state);
    }

    @Override
    public MLConfig getConfig() {
        return MoreCoolersForge.SERVER;
    }
}