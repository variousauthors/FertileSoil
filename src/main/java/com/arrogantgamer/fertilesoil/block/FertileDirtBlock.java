package com.arrogantgamer.fertilesoil.block;

import com.arrogantgamer.fertilesoil.vanilla.behaviours.DirtBlockBehaviours;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;


/** Fertile Dirt just turns into fertile grass. That's it's whole deal. */
public class FertileDirtBlock extends Block {
    public static Block.Properties properties = Block.Properties.create(Material.EARTH, MaterialColor.DIRT)
	    .hardnessAndResistance(0.5F).sound(SoundType.GROUND);

    public FertileDirtBlock(Properties properties) {
	super(properties);
    }
    
    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
  	return super.onBlockActivated(state, worldIn, pos, player, handIn, hit)
  		|| DirtBlockBehaviours.onItemUseHoe(state, worldIn, pos, player, handIn, hit);
    }    
}