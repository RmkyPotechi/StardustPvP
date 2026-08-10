package com.stardustpvp;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = StardustPvP.MOD_ID, name = StardustPvP.NAME, version = StardustPvP.VERSION, clientSideOnly = true, acceptedMinecraftVersions = "[1.8.9]")
public final class StardustPvP {
    public static final String MOD_ID = "stardustpvp";
    public static final String NAME = "StardustPvP";
    public static final String VERSION = "0.1.0";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        // Core initialization will be added incrementally.
    }
}
