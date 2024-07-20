package tfar.morecoolers;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import tfar.morecoolers.init.ModBlockEntities;
import tfar.morecoolers.init.ModBlocks;
import tfar.morecoolers.init.ModItems;
import tfar.morecoolers.platform.Services;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// This class is part of the common project meaning it is shared between all supported loaders. Code written here can only
// import and access the vanilla codebase, libraries used by vanilla, and optionally third party libraries that provide
// common compatible binaries. This means common code can not directly use loader specific concepts such as Forge events
// however it will be compatible with all supported mod loaders.
public class MoreCoolers {

    public static final String MOD_ID = "morecoolers";
    public static final String MOD_NAME = "MoreCoolers";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    // The loader specific projects are able to import and use any code from the common project. This allows you to
    // write the majority of your code here and load it from your loader specific projects. This example has some
    // code that gets invoked by the entry point of the loader specific projects.
    public static void init() {
        // It is common for all supported loaders to provide a similar feature that can not be used directly in the
        // common code. A popular way to get around this is using Java's built-in service loader feature to create
        // your own abstraction layer. You can learn more about this in our provided services class. In this example
        // we have an interface in the common code and use a loader specific implementation to delegate our call to
        // the platform specific approach.
        Services.PLATFORM.registerAll(ModBlocks.class,BuiltInRegistries.BLOCK, Block.class);
        Services.PLATFORM.registerAll(ModItems.class,BuiltInRegistries.ITEM, Item.class);
        Class<BlockEntityType<?>> typeClass = (Class<BlockEntityType<?>>)(Object) BlockEntityType.class;
        Services.PLATFORM.registerAll(ModBlockEntities.class,BuiltInRegistries.BLOCK_ENTITY_TYPE, typeClass);
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID,path);
    }

}