package com.arrogantgamer.fertilesoil.block;

import java.util.Random;

import javax.annotation.Nullable;

import com.arrogantgamer.fertilesoil.FertileSoil;
import com.arrogantgamer.fertilesoil.ModBlocks;
import com.arrogantgamer.fertilesoil.vanilla.behaviours.ShovelItemBehaviours;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.SnowBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.item.ShovelItem;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.GrassColors;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IEnviromentBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.lighting.LightEngine;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.common.ToolType;

public class FertileGrassBlock extends GrassBlock implements IBlockColor {
  public static Block.Properties properties = Block.Properties
    .create(Material.ORGANIC)
    .tickRandomly()
    .hardnessAndResistance(0.6F)
    .sound(SoundType.PLANT);

  public FertileGrassBlock(Properties properties) {
    super(properties);
  }
  
  @Override
  public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, net.minecraftforge.common.IPlantable plantable) {
      PlantType type = plantable.getPlantType(world, pos.offset(facing));
      ResourceLocation myTagId = new ResourceLocation(FertileSoil.MODID, "fertile_grass_plants");

      boolean isPlantableOnGrass = BlockTags.getCollection().getOrCreate(myTagId).contains((Block)plantable);

      return isPlantableOnGrass || type == PlantType.Crop || super.canSustainPlant(state, world, pos, facing, plantable);
  }
  
  public int getColor(BlockState state, @Nullable IEnviromentBlockReader blockReader, @Nullable BlockPos pos, int tintIndex) {
	  if (blockReader != null && pos != null) {
		  return blockReader.getBiome(pos).getGrassColor(pos);		  
	  }

	  return GrassColors.get(0.5D, 1.0D);
  }
  
  @Override
  public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
	return super.onBlockActivated(state, worldIn, pos, player, handIn, hit)
		|| ShovelItemBehaviours.behaveLikeGrassBlock(state, worldIn, pos, player, handIn, hit);
  }
  
  /* Overrides of the grass spreading logic 
   * modified so that this spreads normal grass
   * but degenerates into fertile dirt 
   * TODO once my PR is in we can do this with an event handler since it is basically the 
   * same as making mycelium spread wool 
   * =================================== */
  
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
  
  @Override
  public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
      if (!worldIn.isRemote) {
         if (!worldIn.isAreaLoaded(pos, 3)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
         if (!func_220257_b(state, worldIn, pos)) {
            worldIn.setBlockState(pos, ModBlocks.FERTILE_DIRT_BLOCK.getDefaultState());
         } else {
            if (worldIn.getLight(pos.up()) >= 9) {
               BlockState blockstate = this.getDefaultState();

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
