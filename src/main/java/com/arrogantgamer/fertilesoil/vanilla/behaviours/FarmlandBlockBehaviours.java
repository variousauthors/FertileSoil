package com.arrogantgamer.fertilesoil.vanilla.behaviours;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FarmlandBlockBehaviours {
    public static void turnToDirt(BlockState state, World worldIn, BlockPos pos, Block dirt) {
	worldIn.setBlockState(pos, Block.nudgeEntitiesWithNewState(state, dirt.getDefaultState(), worldIn, pos));
    }
}
