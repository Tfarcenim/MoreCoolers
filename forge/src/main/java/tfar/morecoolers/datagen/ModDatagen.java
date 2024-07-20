package tfar.morecoolers.datagen;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.packs.VanillaBlockLoot;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import tfar.morecoolers.MoreCoolers;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class ModDatagen {

    public static void gather(GatherDataEvent event) {
        boolean client = event.includeClient();
        DataGenerator dataGenerator = event.getGenerator();
        PackOutput packOutput = dataGenerator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        dataGenerator.addProvider(client,new ModLangProvider(packOutput));
        dataGenerator.addProvider(client,new ModItemModelProvider(packOutput,existingFileHelper));
        dataGenerator.addProvider(client,new ModBlockStateProvider(packOutput,existingFileHelper));

        dataGenerator.addProvider(event.includeServer(),new ModRecipeProvider(packOutput));
        dataGenerator.addProvider(event.includeServer(),new ModLootTableProvider(packOutput, Set.of(), List.of(new LootTableProvider.SubProviderEntry(ModBlockLoot::new, LootContextParamSets.BLOCK))));
    }

    public static Stream<Block> getKnownBlocks() {
        return getKnown(BuiltInRegistries.BLOCK);
    }
    public static Stream<Item> getKnownItems() {
        return getKnown(BuiltInRegistries.ITEM);
    }

    public static <V> Stream<V> getKnown(Registry<V> registry) {
        return registry.stream().filter(o -> registry.getKey(o).getNamespace().equals(MoreCoolers.MOD_ID));
    }
}
