package com.arrogantgamer.fertilesoil.block;

import net.minecraft.block.Block;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class FertileGrassBlock extends GrassBlock {
  public static Block.Properties properties = Block.Properties
    .create(Material.ORGANIC)
    .tickRandomly()
    .hardnessAndResistance(0.6F)
    .sound(SoundType.PLANT);

  public FertileGrassBlock(Properties properties) {
    super(properties);
  }
}
