package com.arrogantgamer.fertilesoil;

import com.arrogantgamer.fertilesoil.block.FertileGrassBlock;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.BlockItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FertileSoil.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModBlockColors {
    @SubscribeEvent
    public static void registerBlockColorHandler(final ColorHandlerEvent.Block event) {
	final BlockColors blockColors = event.getBlockColors();

	blockColors.register(new FertileGrassBlock.ColorHandler(), ModBlocks.FERTILE_GRASS_BLOCK);
    }

    @SubscribeEvent
    public static void registerItemColorHandler(final ColorHandlerEvent.Item event) {
	BlockColors blockColors = event.getBlockColors();
	ItemColors itemColors = event.getItemColors();

	final IItemColor itemBlockColourHandler = (stack, tintIndex) -> {
	    final BlockState state = ((BlockItem) stack.getItem()).getBlock().getDefaultState();
	    return blockColors.getColor(state, null, null, tintIndex);
	};

	itemColors.register(itemBlockColourHandler, ModBlocks.FERTILE_GRASS_BLOCK);
    }

}
