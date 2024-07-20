package tfar.morecoolers.datagen;

import net.minecraft.data.loot.packs.VanillaBlockLoot;
import net.minecraft.world.level.block.Block;
import tfar.morecoolers.init.ModBlocks;

public class ModBlockLoot extends VanillaBlockLoot {

    @Override
    protected void generate() {
        dropSelf(ModBlocks.COPPER_COOLER);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModDatagen.getKnownBlocks().toList();
    }
}
