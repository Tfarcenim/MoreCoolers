package tfar.morecoolers.datagen;

import net.minecraft.data.PackOutput;
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
        simpleBlock(ModBlocks.COPPER_COOLER,models().getBuilder("copper_cooler").parent(COOLER));
    }
}
