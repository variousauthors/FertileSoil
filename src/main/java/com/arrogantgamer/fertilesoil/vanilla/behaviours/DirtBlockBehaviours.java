package com.arrogantgamer.fertilesoil.vanilla.behaviours;

import com.arrogantgamer.fertilesoil.ModBlocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class DirtBlockBehaviours {
    
    public static boolean onItemUseHoe(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
	if (worldIn.isRemote) {
	    return true;
	} else {
	    ItemStack itemStack = player.getHeldItem(handIn);

	    if (itemStack.getItem() instanceof HoeItem) {
		// simulate item use
		ItemUseContext context = new ItemUseContext(player, handIn, hit);
		HoeItemBehaviours.turnBlockToFarmland(context, (FarmlandBlock)ModBlocks.FERTILE_FARMLAND_BLOCK);
	    }

	    return false;
	}
    }
}
