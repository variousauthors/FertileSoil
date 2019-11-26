package com.arrogantgamer.fertilesoil;

import com.arrogantgamer.fertilesoil.block.FertileDirtBlock;
import com.arrogantgamer.fertilesoil.block.FertileFarmlandBlock;
import com.arrogantgamer.fertilesoil.block.FertileGrassBlock;
import com.arrogantgamer.fertilesoil.block.FertileGrassPathBlock;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.BlockItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistryEntry;

@Mod.EventBusSubscriber(modid = FertileSoil.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber {
  @SubscribeEvent
  public static void onRegisterBlocks(RegistryEvent.Register<Block> event) {
    event.getRegistry().registerAll(
      setup(new FertileDirtBlock(FertileDirtBlock.properties), "fertile_dirt_block"),
      setup(new FertileGrassBlock(FertileGrassBlock.properties), "fertile_grass_block"),
      setup(new FertileGrassPathBlock(FertileGrassPathBlock.properties), "fertile_grass_path_block"),
      setup(new FertileFarmlandBlock(FertileFarmlandBlock.properties), "fertile_farmland_block")      
    );
  }

  @SubscribeEvent
  public static void onRegisterItems(RegistryEvent.Register<Item> event) {
    event.getRegistry().registerAll(
      setup(new BlockItem(ModBlocks.FERTILE_DIRT_BLOCK, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)), ModBlocks.FERTILE_DIRT_BLOCK.getRegistryName()),
      setup(new BlockItem(ModBlocks.FERTILE_GRASS_BLOCK, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)), ModBlocks.FERTILE_GRASS_BLOCK.getRegistryName()),
      setup(new BlockItem(ModBlocks.FERTILE_GRASS_PATH_BLOCK, new Item.Properties()), ModBlocks.FERTILE_GRASS_PATH_BLOCK.getRegistryName()),
      setup(new BlockItem(ModBlocks.FERTILE_FARMLAND_BLOCK, new Item.Properties()), ModBlocks.FERTILE_FARMLAND_BLOCK.getRegistryName())      
    );
  }

  public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final String name) {
    return setup(entry, new ResourceLocation(FertileSoil.MODID, name));
  }

  public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final ResourceLocation registryName) {
    entry.setRegistryName(registryName);
    return entry;
  }
}
