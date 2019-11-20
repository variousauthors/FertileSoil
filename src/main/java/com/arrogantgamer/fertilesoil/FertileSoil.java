package com.arrogantgamer.fertilesoil;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
 * ROADMAP 
 * 
 * v1.0.0
 * [] use the forge spreadable spread hooks
 * 
 * v0.2.0
 * [] integrate with all the things (JEI, WAILA, The One Probe, etc...)
 * 
 * v0.1.0
 * [o] hydration (growth speed should be not as good as farmland)
 * [o] trampling (no trampling)
 * [o] tag for grass bushlikes
 *    - for mushrooms, try planting on lower light block
 *    - for the rest, gradually add the tags back in and test
 * [o] can we have it spread grass to nearby dirt?
 * 
 * [o] the grass color should match biomes // handle in code
 * [] the dirt color should be richer // custom texture?
 * 
 * [x] sheep should be able to eat the grass (and it gives them a buff! <3)
 *    - this seems to be not possible at the moment
 * [] hoe and shovel interaction
 *    [] implement the same for hoe on Dirt/Grass
 *    [] add models for farmland/path
 *    [o] need to make FertileFarmlandBlock and FertilePathBlock
 *    [o] make an event handler to do the switch
 *    [o] should affect fertile dirt the same way
 * 
 * [o]- do not give fertile farmland a bonus
 * 
 * [] add recipe GRASS_BLOCK -> FERTILE_GRASS_BLOCK
 * [] make shovel the effective tool
 * 
 */
@Mod(FertileSoil.MODID)
public final class FertileSoil {

    public static final String MODID = "fertilesoil";

    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public FertileSoil() {
        LOGGER.debug("HELLO HELLO");
    }
}