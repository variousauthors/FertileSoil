package com.arrogantgamer.fertilesoil.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.GrassPathBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FertileGrassPathBlock extends GrassPathBlock {
	public static Properties properties = Block.Properties.create(Material.EARTH).hardnessAndResistance(0.65F).sound(SoundType.PLANT);
	
	public FertileGrassPathBlock(Block.Properties builder) {
		super(builder);
	}

	public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
		FertileFarmlandBlock.turnToDirt(state, worldIn, pos);
	}
}
