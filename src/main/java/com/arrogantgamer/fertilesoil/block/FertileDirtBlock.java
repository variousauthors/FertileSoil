package com.arrogantgamer.fertilesoil.block;

import java.util.Random;

import com.arrogantgamer.fertilesoil.FertileSoil;
import com.arrogantgamer.fertilesoil.ModBlocks;
import com.arrogantgamer.fertilesoil.vanilla.behaviours.DirtBlockBehaviours;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SnowBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.lighting.LightEngine;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.common.ToolType;

/** Fertile Dirt just turns into fertile grass. That's it's whole deal. */
public class FertileDirtBlock extends Block {
    public static Block.Properties properties = Block.Properties.create(Material.EARTH, MaterialColor.DIRT).hardnessAndResistance(0.5F).sound(SoundType.GROUND).tickRandomly();

    public FertileDirtBlock(Properties properties) {
	super(properties);
    }

    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, net.minecraftforge.common.IPlantable plantable) {
	PlantType type = plantable.getPlantType(world, pos.offset(facing));
	// TODO change this to test each tag (flowers, mushrooms, etc...) as they become
	// available
	ResourceLocation tagId = new ResourceLocation(FertileSoil.MODID, "fertile_grass_plants");

	boolean isPlantableOnGrass = BlockTags.getCollection().getOrCreate(tagId).contains((Block) plantable);

	return isPlantableOnGrass || type == PlantType.Crop || super.canSustainPlant(state, world, pos, facing, plantable);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
	return super.onBlockActivated(state, worldIn, pos, player, handIn, hit) || DirtBlockBehaviours.onItemUseHoe(state, worldIn, pos, player, handIn, hit);
    }

    @Override
    public boolean isToolEffective(BlockState state, ToolType tool) {
	return tool == ToolType.SHOVEL;
    }

    /*
     * TODO this is just temporary: the dirt pulls nearby vanilla grass onto it I have a PR
     * open into forge to add grass growing hooks, but this code will work until
     * that is merged in
     * 
     * @see https://github.com/MinecraftForge/MinecraftForge/pull/6320
     */
    @Override
    public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
	// check a random block in the 3x5x3 area above this block
	// check that this block is OK for grass
	// set this block to be grass

	if (!worldIn.isRemote) {
	    for (int i = 0; i < 4; ++i) {
		// find a grass block: flipped the y so that my dirt is search up for grass
		// (grass searches down)
		BlockPos grassPos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 1, random.nextInt(3) - 1);
		BlockState grassBlockState = worldIn.getBlockState(grassPos).getBlock().getDefaultState();

		// make sure that this is grass and that it is in bright light
		if (grassBlockState.getBlock() == Blocks.GRASS_BLOCK && worldIn.getLight(grassPos.up()) >= 9) {

		    // no need to check whether this block is dirt, because it is
		    // just check whether the conditions are correct for spread
		    if (func_220256_c(grassBlockState, worldIn, pos)) {
			// when fertile dirt pulls vanilla grass it becomes fertile grass
			worldIn.setBlockState(pos, ModBlocks.FERTILE_GRASS_BLOCK.getDefaultState().with(SNOWY, Boolean.valueOf(worldIn.getBlockState(pos.up()).getBlock() == Blocks.SNOW)));
		    }
		}
	    }
	}
    }

    /*
     * TODO technology stolen from SpreadableSnowyDirtBlock to enable the grass spreading
     * while we wait for forge to implement the hooks
     */
    private static final BooleanProperty SNOWY = BlockStateProperties.SNOWY;

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

}