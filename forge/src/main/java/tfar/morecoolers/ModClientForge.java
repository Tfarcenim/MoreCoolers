package tfar.morecoolers;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import tfar.morecoolers.init.ModBlocks;

public class ModClientForge {
    public static void init(IEventBus bus) {
        bus.addListener(ModClientForge::setup);
    }

    static void setup(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.COPPER_COOLER, RenderType.translucent());
    }

}
