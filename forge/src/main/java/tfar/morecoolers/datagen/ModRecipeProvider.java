package tfar.morecoolers.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import tfar.morecoolers.init.ModItems;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        cooler(ModItems.COPPER_COOLER,Tags.Items.STORAGE_BLOCKS_COPPER,pWriter);
        cooler(ModItems.IRON_COOLER,Tags.Items.STORAGE_BLOCKS_IRON,pWriter);
        cooler(ModItems.GOLD_COOLER,Tags.Items.STORAGE_BLOCKS_GOLD,pWriter);
        cooler(ModItems.DIAMOND_COOLER,Tags.Items.STORAGE_BLOCKS_DIAMOND,pWriter);
    }

    protected void cooler(Item result, TagKey<Item> material,Consumer<FinishedRecipe> pWriter) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,result)
                .define('c', material)
                .define('g', Tags.Items.GLASS_PANES)
                .define('b', Items.POWDER_SNOW_BUCKET)
                .pattern("cgc")
                .pattern("cbc")
                .pattern("ccc")
                .unlockedBy(getHasName(Items.POWDER_SNOW_BUCKET),has(Items.POWDER_SNOW_BUCKET))
                .save(pWriter);
    }

}
