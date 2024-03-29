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
 *    [o] integrate with JEI
 * [?] the farmland should give a bonus?
 * [] for some reason path and farmland show up in the /give command
 *    maybe because I have models.item entries for them? Not sure.
 * [o] add to vanilla creative tabs
 * [] add complete documentation of the mod directly to github and curseforge
 *    - with a gif for the recipe :D
 * [] how do you do config???
 *    [] add a config to control the richness of the grass color
 *       - some folks are like "ah, I should be able to tell the rich grass apart by looking at it"
 *    [] add a config for turning on/off a small fertility boost for rich farmland
 *       - just on/off for the small boost, not a configurable boost!
 * 
 * v0.1.0
 * [o] set the version number to 1.14.4-0.1.0
 * [o] test on the server
 * [o] test with a bunch of other mods in there
 * [o] all language stuff
 * 
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
 * [o] add models for farmland/path
 * 
 * [o] make shovel the effective tool
 * [o] path/farmland need to drop dirt (they currently drop nothing, which is weird)
 * [o] does snow gather on it correctly

 * [o] Farmland's turnToDirt method is static, so I can't override it
 *    so right now, FertileFarmland degenerates into dirt
 *    I need to copy/paste tick/onEntityFallenOn/etc... anything that uses Farmland.turnToDirt
 * [o] the dirt color should be richer, because otherwise the blocks 
 *    look identical to dirt in the inventory and that is terrible UX
 * [o] add blocks to enderman_holdable
 * [o] let dirt pull grass onto it while we wait for forge
 * 
 */
@Mod(FertileSoil.MODID)
public final class FertileSoil {

    public static final String MODID = "fertilesoil";

    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public FertileSoil() {}
}