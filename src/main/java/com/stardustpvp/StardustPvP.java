package com.stardustpvp;

import com.stardustpvp.client.MinimumViewBobbing;
import com.stardustpvp.client.PerformanceOverlay;
import com.stardustpvp.config.StardustConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = StardustPvP.MOD_ID, name = StardustPvP.NAME, version = StardustPvP.VERSION, clientSideOnly = true, acceptedMinecraftVersions = "[1.8.9]")
public final class StardustPvP {
    public static final String MOD_ID = "stardustpvp";
    public static final String NAME = "StardustPvP";
    public static final String VERSION = "0.1.0";

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        StardustConfig.load(event.getModConfigurationDirectory());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new MinimumViewBobbing());
        MinecraftForge.EVENT_BUS.register(new PerformanceOverlay());
    }
}
