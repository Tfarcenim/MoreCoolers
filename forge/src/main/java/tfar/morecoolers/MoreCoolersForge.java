package tfar.morecoolers;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.RegisterEvent;
import org.apache.commons.lang3.tuple.Pair;
import tfar.morecoolers.datagen.ModDatagen;
import tfar.morecoolers.platform.MLConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Mod(MoreCoolers.MOD_ID)
public class MoreCoolersForge {

    public MoreCoolersForge() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        // This method is invoked by the Forge mod loader when it is ready
        // to load your mod. You can access Forge and Common code in this
        // project.
        bus.addListener(this::register);
        bus.addListener(this::setup);
        bus.addListener(ModDatagen::gather);
        if (FMLEnvironment.dist.isClient()) {
            //  ModClientForge.init(bus);
        }
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


    public static class ClientConfig {
        public static ForgeConfigSpec.BooleanValue preview;
        public static ForgeConfigSpec.IntValue preview_x;
        public static ForgeConfigSpec.IntValue preview_y;

        public ClientConfig(ForgeConfigSpec.Builder builder) {
            builder.push("client");
            preview = builder
                    .comment("Whether to display the preview of the item in the dank, disable if you have optifine")
                    .define("preview", true);
            preview_x = builder
                    .comment("X position of preview")
                    .defineInRange("preview_x", -140, -10000, 10000);
            preview_y = builder
                    .comment("Y position of preview")
                    .defineInRange("preview_y", -25, -10000, 10000);
            builder.pop();
        }
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