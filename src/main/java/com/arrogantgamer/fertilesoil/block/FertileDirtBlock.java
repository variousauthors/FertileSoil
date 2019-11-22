package com.arrogantgamer.fertilesoil.block;

import com.arrogantgamer.fertilesoil.FertileSoil;
import com.arrogantgamer.fertilesoil.vanilla.behaviours.DirtBlockBehaviours;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.common.ToolType;

/** Fertile Dirt just turns into fertile grass. That's it's whole deal. */
public class FertileDirtBlock extends Block {
    public static Block.Properties properties = Block.Properties.create(Material.EARTH, MaterialColor.DIRT).hardnessAndResistance(0.5F).sound(SoundType.GROUND);

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
}