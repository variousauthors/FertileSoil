package com.arrogantgamer.fertilesoil.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

/**
 * the idea is that fertile soil looks like grass, but it can sustain crops
 * - it does need to be hydrated
 * - trampling does not damage it
 * - it is a GrassBlock, and if it decays into dirt for any reason it loses
 * its special properties and becomes just DIRT
 * - it does not spread
 * - bonemealing it can be cool...
 * <p>
 * So I'm thinking, since FarmlandBlock is relatively simple while GrassBlock
 * is very complex, I might go ahead and extend GrassBlock and then just
 * paste in the functionality from Farmland that I need
 * <p>
 * I might need to override canSustainPlant since Farmland's canSustainPlain behaviour
 * is built in to Block. Probably like super.canSustainPlant || type === crop
 * so that you can still put flowers and reeds on it, but also crops
 */
public class FertileDirtBlock extends Block {
  public static Block.Properties properties = Block.Properties
    .create(Material.EARTH, MaterialColor.DIRT)
    .hardnessAndResistance(0.5F)
    .sound(SoundType.GROUND);

  public FertileDirtBlock(Properties properties) {
    super(properties);
  }
}