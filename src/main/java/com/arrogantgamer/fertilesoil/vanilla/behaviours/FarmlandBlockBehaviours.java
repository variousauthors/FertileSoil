package com.arrogantgamer.fertilesoil.vanilla.behaviours;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.entity.Entity;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class FarmlandBlockBehaviours {
    public static final IntegerProperty MOISTURE = BlockStateProperties.MOISTURE_0_7;

    public static void turnToDirt(BlockState state, World worldIn, BlockPos pos, Block dirt) {
	worldIn.setBlockState(pos, Block.nudgeEntitiesWithNewState(state, dirt.getDefaultState(), worldIn, pos));
    }

    public static void tick(FarmlandBlock self, BlockState state, World worldIn, BlockPos pos, Random random, Block dirt) {
	if (!state.isValidPosition(worldIn, pos)) {
	    turnToDirt(state, worldIn, pos, dirt);
	} else {
	    int i = state.get(MOISTURE);
	    if (!FarmlandBlockBehaviours.hasWater(worldIn, pos) && !worldIn.isRainingAt(pos.up())) {
		if (i > 0) {
		    worldIn.setBlockState(pos, state.with(MOISTURE, Integer.valueOf(i - 1)), 2);
		} else if (!hasCrops(self, worldIn, pos)) {
		    turnToDirt(state, worldIn, pos, dirt);
		}
	    } else if (i < 7) {
		worldIn.setBlockState(pos, state.with(MOISTURE, Integer.valueOf(7)), 2);
	    }

	}
    }

    public static void onFallenUpon(FarmlandBlock self, World worldIn, BlockPos pos, Entity entityIn, float fallDistance, Block dirt) {
	if (!worldIn.isRemote && net.minecraftforge.common.ForgeHooks.onFarmlandTrample(worldIn, pos, Blocks.DIRT.getDefaultState(), fallDistance, entityIn)) {
	    turnToDirt(worldIn.getBlockState(pos), worldIn, pos, dirt);
	}
    }

    public static boolean hasWater(IWorldReader worldIn, BlockPos pos) {
	for (BlockPos blockpos : BlockPos.getAllInBoxMutable(pos.add(-4, 0, -4), pos.add(4, 1, 4))) {
	    if (worldIn.getFluidState(blockpos).isTagged(FluidTags.WATER)) {
		return true;
	    }
	}

	return net.minecraftforge.common.FarmlandWaterManager.hasBlockWaterTicket(worldIn, pos);
    }

    private static boolean hasCrops(FarmlandBlock self, IBlockReader worldIn, BlockPos pos) {
	BlockState state = worldIn.getBlockState(pos.up());
	return state.getBlock() instanceof net.minecraftforge.common.IPlantable
		&& self.canSustainPlant(state, worldIn, pos, Direction.UP, (net.minecraftforge.common.IPlantable) state.getBlock());
    }
}
