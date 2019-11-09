package com.arrogantgamer.fertilesoil;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(FertileSoil.MODID)
public final class FertileSoil {

    public static final String MODID = "fertilesoil";

    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public FertileSoil() {
        LOGGER.debug("HELLO HELLO");
    }
}