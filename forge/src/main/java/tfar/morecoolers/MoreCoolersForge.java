package tfar.morecoolers;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.RegisterEvent;
import org.apache.commons.lang3.tuple.Pair;
import tfar.morecoolers.datagen.ModDatagen;
import tfar.morecoolers.init.ModBlocks;
import tfar.morecoolers.platform.MLConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Mod(MoreCoolers.MOD_ID)
public class MoreCoolersForge {

    public MoreCoolersForge() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER,SERVER_SPEC);
        // This method is invoked by the Forge mod loader when it is ready
        // to load your mod. You can access Forge and Common code in this
        // project.
        bus.addListener(this::register);
        bus.addListener(this::setup);
        bus.addListener(ModDatagen::gather);
        if (FMLEnvironment.dist.isClient()) {
              ModClientForge.init(bus);
        }
        bus.addListener(this::creativeTab);
        // Use Forge to bootstrap the Common mod.
        MoreCoolers.init();
    }

    //copper,iron.gold,diamond

    public static Map<Registry<?>, List<Pair<ResourceLocation, Supplier<?>>>> registerLater = new HashMap<>();

    private void register(RegisterEvent e) {
        for (Map.Entry<Registry<?>, List<Pair<ResourceLocation, Supplier<?>>>> entry : registerLater.entrySet()) {
            Registry<?> registry = entry.getKey();
            List<Pair<ResourceLocation, Supplier<?>>> toRegister = entry.getValue();
            for (Pair<ResourceLocation, Supplier<?>> pair : toRegister) {
                e.register((ResourceKey<? extends Registry<Object>>) registry.key(), pair.getLeft(), (Supplier<Object>) pair.getValue());
            }
        }
    }

    private void creativeTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(() -> ModBlocks.COPPER_COOLER);
            event.accept(() -> ModBlocks.IRON_COOLER);
            event.accept(() -> ModBlocks.GOLD_COOLER);
            event.accept(() -> ModBlocks.DIAMOND_COOLER);

        }
    }


    private void setup(FMLCommonSetupEvent event) {
        registerLater.clear();
    }

    public static final ServerConfig SERVER;
    public static final ForgeConfigSpec SERVER_SPEC;

    static {
        final Pair<ServerConfig, ForgeConfigSpec> specPair2 = new ForgeConfigSpec.Builder().configure(ServerConfig::new);
        SERVER_SPEC = specPair2.getRight();
        SERVER = specPair2.getLeft();
    }

    public static class ServerConfig implements MLConfig {
        public static ForgeConfigSpec.IntValue copperRows;
        public static ForgeConfigSpec.IntValue ironRows;
        public static ForgeConfigSpec.IntValue goldRows;
        public static ForgeConfigSpec.IntValue diamondRows;

        public ServerConfig(ForgeConfigSpec.Builder builder) {
            builder.push("server");
            copperRows = builder.
                    comment("Rows of copper cooler")
                    .defineInRange("copper_rows", 1, 1, 6);
            ironRows = builder.
                    comment("Rows of iron cooler")
                    .defineInRange("iron_rows", 2, 1, 6);
            goldRows = builder.
                    comment("Rows of gold cooler")
                    .defineInRange("gold_rows", 3, 1, 6);
            diamondRows = builder.
                    comment("Rows of diamond cooler")
                    .defineInRange("diamond_rows", 4, 1, 6);
            builder.pop();
        }

        @Override
        public int copper() {
            return copperRows.get();
        }

        @Override
        public int iron() {
            return ironRows.get();
        }

        @Override
        public int gold() {
            return goldRows.get();
        }

        @Override
        public int diamond() {
            return diamondRows.get();
        }
    }
}