package com.stardustpvp;

import com.stardustpvp.core.PerformanceMonitor;
import com.stardustpvp.core.PerformanceStats;
import com.stardustpvp.core.StardustConfig;
import com.stardustpvp.core.StardustHud;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;

@Mod(modid = StardustPvP.MOD_ID, name = StardustPvP.NAME, version = StardustPvP.VERSION,
        clientSideOnly = true, acceptedMinecraftVersions = "[1.8.9]")
public final class StardustPvP {
    public static final String MOD_ID = "stardustpvp";
    public static final String NAME = "StardustPvP";
    public static final String VERSION = "0.3.0";

    private final PerformanceMonitor performanceMonitor = new PerformanceMonitor();
    private final PerformanceStats performanceStats = new PerformanceStats();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        StardustConfig.load(event.getModConfigurationDirectory());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new StardustHud(performanceMonitor));
    }

    @SubscribeEvent
    public void onRenderTick(RenderTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            performanceMonitor.beginFrame();
            performanceStats.beginFrame();
        } else if (event.phase == TickEvent.Phase.END) {
            performanceMonitor.endFrame();
            performanceStats.endFrame();
            performanceStats.invalidatePercentiles();
        }
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        // Reserved for low-frequency performance scheduling. Keep the tick hook cheap.
    }

    public PerformanceStats getPerformanceStats() {
        return performanceStats;
    }
}
