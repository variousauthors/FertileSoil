package com.arrogantgamer.fertilesoil.vanilla.behaviours;

import com.arrogantgamer.fertilesoil.ModBlocks;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class ShovelItemBehaviours {

    public static boolean behaveLikeGrassBlock(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
	    Hand handIn, BlockRayTraceResult hit) {
	if (worldIn.isRemote) {
	    return true;
	} else {
	    ItemStack itemStack = player.getHeldItem(handIn);

	    if (itemStack.getToolTypes().contains(ToolType.SHOVEL)) {
		// simulate item use
		ItemUseContext context = new ItemUseContext(player, handIn, hit);
		ShovelItemBehaviours.turnGrassToPath(context, ModBlocks.FERTILE_GRASS_PATH_BLOCK.getDefaultState());
	    }

	    return false;
	}
    }

    /* grabbed from ShovelItem 2019-11-19 1.14.4-28.1.0 */
    public static void turnGrassToPath(ItemUseContext context, BlockState path) {
	World world = context.getWorld();
	BlockPos blockpos = context.getPos();

	if (context.getFace() != Direction.DOWN && world.getBlockState(blockpos.up()).isAir(world, blockpos.up())) {
	    PlayerEntity player = context.getPlayer();

	    world.playSound(player, blockpos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
	    if (!world.isRemote) {
		world.setBlockState(blockpos, path, 11);
		if (player != null) {
		    context.getItem().damageItem(1, player, (p_220041_1_) -> {
			p_220041_1_.sendBreakAnimation(context.getHand());
		    });
		}
	    }
	}
    }
}
