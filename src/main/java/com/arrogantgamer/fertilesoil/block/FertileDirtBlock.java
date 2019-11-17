package com.arrogantgamer.fertilesoil.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

/** Fertile Dirt just turns into fertile grass. That's it's whole deal. */
public class FertileDirtBlock extends Block {
  public static Block.Properties properties = Block.Properties
    .create(Material.EARTH, MaterialColor.DIRT)
    .hardnessAndResistance(0.5F)
    .sound(SoundType.GROUND);

  public FertileDirtBlock(Properties properties) {
    super(properties);
  }
}