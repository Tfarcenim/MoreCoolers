package tfar.morecoolers.datagen;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import tfar.morecoolers.MoreCoolers;
import tfar.morecoolers.init.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {


    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, MoreCoolers.MOD_ID, exFileHelper);
    }
    ModelFile.ExistingModelFile COOLER = models().getExistingFile(modLoc("block/cooler"));

    @Override
    protected void registerStatesAndModels() {
        cooler(ModBlocks.COPPER_COOLER);
        cooler(ModBlocks.IRON_COOLER);
        cooler(ModBlocks.GOLD_COOLER);
        cooler(ModBlocks.DIAMOND_COOLER);


    }

    protected void cooler(Block block) {
        String name = BuiltInRegistries.BLOCK.getKey(block).getPath();
        simpleBlock(block,models().getBuilder(name).parent(COOLER)
                .texture("1",modLoc("block/"+name+"_bottom"))
                .texture("3",modLoc("block/"+name+"_side"))
                .texture("4",modLoc("block/"+name+"_side2"))
                .texture("particle",modLoc("block/"+name+"_bottom"))
        );
    }

}
