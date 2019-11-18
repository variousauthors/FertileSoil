package com.arrogantgamer.fertilesoil.block;

import com.arrogantgamer.fertilesoil.FertileSoil;
import com.arrogantgamer.fertilesoil.ModBlocks;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IEnviromentBlockReader;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FertileSoil.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlockColors implements IBlockColor
{
    public static final IBlockColor INSTANCE = new ModBlockColors();
    
	@Override
	public int getColor(BlockState state, IEnviromentBlockReader blockReader, BlockPos pos, int tintIndex) {
		return blockReader.getBiome(pos).getGrassColor(pos);
	}
    
    /**
     * Register block colors.
     */
	@SubscribeEvent
    public static void registerBlockColorHandler(final ColorHandlerEvent.Block event)
    {
		FertileSoil.LOGGER.debug("Registering ColorHandlers");

		final BlockColors blockColors = event.getBlockColors();

		blockColors.register(INSTANCE, ModBlocks.FERTILE_GRASS_BLOCK);
    }
}
