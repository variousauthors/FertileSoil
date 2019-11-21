package com.arrogantgamer.fertilesoil.vanilla.behaviours;

import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HoeItemBehaviours {

    /* grabbed from HoeItem 1.14.4-28.1.0 (2019-11-19 ) */
    public static void turnBlockToFarmland(ItemUseContext context, FarmlandBlock path) {
	World world = context.getWorld();
	BlockPos blockpos = context.getPos();
	if (context.getFace() != Direction.DOWN && world.getBlockState(blockpos.up()).isAir()) {
	    BlockState blockstate = path.getDefaultState();
	    
	    if (blockstate != null) {
		PlayerEntity playerentity = context.getPlayer();
		world.playSound(playerentity, blockpos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
		if (!world.isRemote) {
		    world.setBlockState(blockpos, blockstate, 11);
		    if (playerentity != null) {
			context.getItem().damageItem(1, playerentity, (p_220043_1_) -> {
			    p_220043_1_.sendBreakAnimation(context.getHand());
			});
		    }
		}
	    }
	}
    }
}
