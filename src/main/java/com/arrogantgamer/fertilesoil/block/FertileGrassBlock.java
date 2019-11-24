package com.arrogantgamer.fertilesoil.block;

import java.util.Random;

import javax.annotation.Nullable;

import com.arrogantgamer.fertilesoil.FertileSoil;
import com.arrogantgamer.fertilesoil.ModBlocks;
import com.arrogantgamer.fertilesoil.vanilla.behaviours.GrassBlockBehaviours;
import com.arrogantgamer.fertilesoil.vanilla.behaviours.SpreadableSnowyDirtBlockBehaviours;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.GrassColors;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IEnviromentBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.common.ToolType;

public class FertileGrassBlock extends GrassBlock {
    public static Block.Properties properties = Block.Properties.create(Material.ORGANIC).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.PLANT);

    public FertileGrassBlock(Properties properties) {
	super(properties);
    }

    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, net.minecraftforge.common.IPlantable plantable) {
	PlantType type = plantable.getPlantType(world, pos.offset(facing));
	// TODO change this to test each tag (flowers, mushrooms, etc...) as they become available
	ResourceLocation tagId = new ResourceLocation(FertileSoil.MODID, "fertile_grass_plants");

	boolean isPlantableOnGrass = BlockTags.getCollection().getOrCreate(tagId).contains((Block) plantable);

	return isPlantableOnGrass || type == PlantType.Crop || super.canSustainPlant(state, world, pos, facing, plantable);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
	return super.onBlockActivated(state, worldIn, pos, player, handIn, hit) || GrassBlockBehaviours.onItemUseShovel(state, worldIn, pos, player, handIn, hit)
		|| GrassBlockBehaviours.onItemUseHoe(state, worldIn, pos, player, handIn, hit);
    }

    @Override
    public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
	SpreadableSnowyDirtBlockBehaviours.tick(state, worldIn, pos, random, ModBlocks.FERTILE_DIRT_BLOCK, ModBlocks.FERTILE_GRASS_BLOCK);
    }

    @Override
    public boolean isToolEffective(BlockState state, ToolType tool) {
	return tool == ToolType.SHOVEL;
    }

    public static class ColorHandler implements IBlockColor {
	public int getColor(BlockState state, @Nullable IEnviromentBlockReader blockReader, @Nullable BlockPos pos, int tintIndex) {
	    if (blockReader != null && pos != null) {
		return blockReader.getBiome(pos).getGrassColor(pos);
	    }

	    return GrassColors.get(0.5D, 1.0D);
	}
    }
}
