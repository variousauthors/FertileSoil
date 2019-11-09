package com.arrogantgamer.fertilesoil;

import com.arrogantgamer.fertilesoil.block.FertileDirtBlock;
import com.arrogantgamer.fertilesoil.block.FertileGrassBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
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
    System.out.println("=========> onRegisterBlocks");
    event.getRegistry().registerAll(
      setup(new FertileDirtBlock(FertileDirtBlock.properties), "fertile_dirt_block"),
      setup(new FertileGrassBlock(FertileDirtBlock.properties), "fertile_grass_block")
    );
  }

  @SubscribeEvent
  public static void onRegisterItems(RegistryEvent.Register<Item> event) {
    System.out.println("=========> onRegisterBlocks");
    event.getRegistry().registerAll(
      setup(new Item(new Item.Properties()), "example_item"),
      setup(new BlockItem(ModBlocks.FERTILE_DIRT_BLOCK, new Item.Properties()), ModBlocks.FERTILE_DIRT_BLOCK.getRegistryName()),
      setup(new BlockItem(ModBlocks.FERTILE_GRASS_BLOCK, new Item.Properties()), ModBlocks.FERTILE_GRASS_BLOCK.getRegistryName())
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
