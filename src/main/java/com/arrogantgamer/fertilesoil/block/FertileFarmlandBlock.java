package com.arrogantgamer.fertilesoil.block;

import java.util.Random;

import com.arrogantgamer.fertilesoil.ModBlocks;
import com.arrogantgamer.fertilesoil.vanilla.behaviours.FarmlandBlockBehaviours;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.common.ToolType;

public class FertileFarmlandBlock extends FarmlandBlock {
    public static Properties properties = Block.Properties.create(Material.EARTH).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.GROUND);

    public FertileFarmlandBlock(Block.Properties builder) {
	super(builder);
	this.setDefaultState(this.stateContainer.getBaseState().with(MOISTURE, Integer.valueOf(0)));
    }

    @Override
    public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
	FarmlandBlockBehaviours.tick(this, state, worldIn, pos, random, ModBlocks.FERTILE_DIRT_BLOCK);
    }

    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
	FarmlandBlockBehaviours.onFallenUpon(this, worldIn, pos, entityIn, fallDistance, ModBlocks.FERTILE_DIRT_BLOCK);
    }

    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, net.minecraftforge.common.IPlantable plantable) {
	PlantType type = plantable.getPlantType(world, pos.offset(facing));

	return type == PlantType.Crop || super.canSustainPlant(state, world, pos, facing, plantable);
    }

    @Override
    public boolean isToolEffective(BlockState state, ToolType tool) {
	return tool == ToolType.SHOVEL;
    }
}
