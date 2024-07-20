package tfar.morecoolers.datagen;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import tfar.morecoolers.MoreCoolers;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MoreCoolers.MOD_ID, existingFileHelper);
    }

    ModelFile.ExistingModelFile GENERATED = getExistingFile(mcLoc("item/generated"));
    ModelFile.ExistingModelFile COOLER = getExistingFile(modLoc("block/cooler"));

    @Override
    protected void registerModels() {

    }

    protected void makeOneLayerItem(Item item, ResourceLocation texture) {
        String path = BuiltInRegistries.ITEM.getKey(item).getPath();
        if (existingFileHelper.exists(texture , PackType.CLIENT_RESOURCES, ".png", "textures")) {
            getBuilder(path).parent(GENERATED).texture("layer0", texture);
        } else {
            System.out.println("no texture for " + texture + " found, skipping");
        }
    }

    protected void makeOneLayerItem(Item item) {
        ResourceLocation texture = BuiltInRegistries.ITEM.getKey(item);
        makeOneLayerItem(item, new ResourceLocation(texture.getNamespace(), "item/" + texture.getPath()));
    }

}
