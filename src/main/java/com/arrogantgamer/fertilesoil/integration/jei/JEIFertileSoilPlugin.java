package com.arrogantgamer.fertilesoil.integration.jei;

import com.arrogantgamer.fertilesoil.FertileSoil;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import net.minecraft.util.ResourceLocation;

@JeiPlugin
public class JEIFertileSoilPlugin implements IModPlugin {
    private static ResourceLocation ID = new ResourceLocation(FertileSoil.MODID, "jei_plugin");

    @Override
    public ResourceLocation getPluginUid() {
	return ID;
    }

}
