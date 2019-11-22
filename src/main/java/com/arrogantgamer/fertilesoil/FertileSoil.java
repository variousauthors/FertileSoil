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
 * 
 * [x] sheep should be able to eat the grass (and it gives them a buff! <3)
 *    - this seems to be not possible at the moment
 * [o] hoe and shovel interaction
 *    [o] implement the same for hoe on Dirt/Grass
 *    [o] need to make FertileFarmlandBlock and FertilePathBlock
 *    [o] make an event handler to do the switch
 *    [o] should affect fertile dirt the same way
 *    [o] make sure crops can be planted on FertileFarmland
 * 
 * 
 * [o]- do not give fertile farmland a bonus
 * 
 * [o] add recipe GRASS_BLOCK -> FERTILE_GRASS_BLOCK
 * [] add models for farmland/path
 * [] the dirt color should be richer // custom texture?
 * 
 * [] make shovel the effective tool
 * [] Farmland's turnToDirt method is static, so I can't override it
 *    so right now, FertileFarmland degenerates into dirt
 *    I need to copy/paste tick/onEntityFallenOn/etc... anything that uses Farmland.turnToDirt

 */
@Mod(FertileSoil.MODID)
public final class FertileSoil {

    public static final String MODID = "fertilesoil";

    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public FertileSoil() {
        LOGGER.debug("HELLO HELLO");
    }
}