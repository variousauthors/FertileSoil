package com.arrogantgamer.fertilesoil.block;

import com.arrogantgamer.fertilesoil.ModBlocks;
import com.arrogantgamer.fertilesoil.vanilla.behaviours.FarmlandBlockBehaviours;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FertileFarmlandBlock extends FarmlandBlock {
    public static Properties properties = Block.Properties.create(Material.EARTH).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.GROUND);

    public FertileFarmlandBlock(Block.Properties builder) {
	super(builder);
	this.setDefaultState(this.stateContainer.getBaseState().with(MOISTURE, Integer.valueOf(0)));
    }

    public static void turnToDirt(BlockState state, World worldIn, BlockPos pos) {
	FarmlandBlockBehaviours.turnToDirt(state, worldIn, pos, ModBlocks.FERTILE_DIRT_BLOCK);
    }
}
