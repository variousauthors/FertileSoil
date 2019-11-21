package com.arrogantgamer.fertilesoil.vanilla.behaviours;

import java.util.Random;

import com.arrogantgamer.fertilesoil.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SnowBlock;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.lighting.LightEngine;

public class SpreadableSnowyDirtBlockBehaviours {
    public static final BooleanProperty SNOWY = BlockStateProperties.SNOWY;
    
    private static boolean func_220257_b(BlockState p_220257_0_, IWorldReader p_220257_1_, BlockPos p_220257_2_) {
        BlockPos blockpos = p_220257_2_.up();
        BlockState blockstate = p_220257_1_.getBlockState(blockpos);
        if (blockstate.getBlock() == Blocks.SNOW && blockstate.get(SnowBlock.LAYERS) == 1) {
           return true;
        } else {
           int i = LightEngine.func_215613_a(p_220257_1_, p_220257_0_, p_220257_2_, blockstate, blockpos, Direction.UP, blockstate.getOpacity(p_220257_1_, blockpos));
           return i < p_220257_1_.getMaxLightLevel();
        }
     }
    
    private static boolean func_220256_c(BlockState p_220256_0_, IWorldReader p_220256_1_, BlockPos p_220256_2_) {
        BlockPos blockpos = p_220256_2_.up();
        return func_220257_b(p_220256_0_, p_220256_1_, p_220256_2_) && !p_220256_1_.getFluidState(blockpos).isTagged(FluidTags.WATER);
     }
    
    public static void tick(BlockState state, World worldIn, BlockPos pos, Random random, Block degeneratesInto, Block spreadsAs) {
        if (!worldIn.isRemote) {
           if (!worldIn.isAreaLoaded(pos, 3)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
           if (!func_220257_b(state, worldIn, pos)) {
              worldIn.setBlockState(pos, degeneratesInto.getDefaultState());
           } else {
              if (worldIn.getLight(pos.up()) >= 9) {
                 BlockState blockstate = spreadsAs.getDefaultState();

                 for(int i = 0; i < 4; ++i) {
                    BlockPos blockpos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                    BlockState target = worldIn.getBlockState(blockpos);
                    
                    if (target.getBlock() == Blocks.DIRT && func_220256_c(blockstate, worldIn, blockpos)) {
                       // if we are spreading to dirt, spread grass
                       worldIn.setBlockState(blockpos, Blocks.GRASS_BLOCK.getDefaultState().with(SNOWY, Boolean.valueOf(worldIn.getBlockState(blockpos.up()).getBlock() == Blocks.SNOW)));
                    } else if (target.getBlock() == ModBlocks.FERTILE_DIRT_BLOCK && func_220256_c(blockstate, worldIn, blockpos)) {
                  	 // if we are spreading to fertile dirt, spread fertile grass
                       worldIn.setBlockState(blockpos, blockstate.with(SNOWY, Boolean.valueOf(worldIn.getBlockState(blockpos.up()).getBlock() == Blocks.SNOW)));
                    }
                 }
              }
           }
        }
     }
}
