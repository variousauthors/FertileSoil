package com.arrogantgamer.fertilesoil.vanilla.behaviours;

import net.minecraft.block.GrassPathBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ShovelItemBehaviours {

    /* grabbed from ShovelItem 2019-11-19 1.14.4-28.1.0 */
    public static void turnBlockToPath(ItemUseContext context, GrassPathBlock path) {
	World world = context.getWorld();
	BlockPos blockpos = context.getPos();

	if (context.getFace() != Direction.DOWN && world.getBlockState(blockpos.up()).isAir(world, blockpos.up())) {
	    PlayerEntity player = context.getPlayer();

	    world.playSound(player, blockpos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
	    if (!world.isRemote) {
		world.setBlockState(blockpos, path.getDefaultState(), 11);
		if (player != null) {
		    context.getItem().damageItem(1, player, (p_220041_1_) -> {
			p_220041_1_.sendBreakAnimation(context.getHand());
		    });
		}
	    }
	}
    }
}
