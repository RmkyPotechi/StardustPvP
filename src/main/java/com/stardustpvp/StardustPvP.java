package com.stardustpvp;

import com.stardustpvp.core.PerformanceMonitor;
import com.stardustpvp.core.PerformanceStats;
import com.stardustpvp.core.StardustConfig;
import com.stardustpvp.core.StardustHud;
import com.stardustpvp.hud.ArmorPotionRenderer;
import com.stardustpvp.hud.CpsInputHandler;
import com.stardustpvp.hud.CpsRenderer;
import com.stardustpvp.hud.CpsTracker;
import com.stardustpvp.hud.CrosshairRenderer;
import com.stardustpvp.hud.HudElementState;
import com.stardustpvp.hud.HudLayoutManager;
import com.stardustpvp.hud.HudPreset;
import com.stardustpvp.hud.HudPresetLayout;
import com.stardustpvp.hud.HudEditorController;
import com.stardustpvp.hud.HudWidgetCollector;
import com.stardustpvp.hud.HudWidgetRenderer;
import com.stardustpvp.hud.KeystrokesRenderer;
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
    public static final String VERSION = "0.3.1";

    private final PerformanceMonitor performanceMonitor = new PerformanceMonitor();
    private final PerformanceStats performanceStats = new PerformanceStats();
    private final HudLayoutManager hudLayout = new HudLayoutManager();
    private final CpsTracker cpsTracker = new CpsTracker();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        StardustConfig.load(event.getModConfigurationDirectory());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new StardustHud(performanceMonitor));
        registerHudElements();
        HudPresetLayout.apply(hudLayout, HudPreset.PVP);

        HudWidgetCollector collector = new HudWidgetCollector(cpsTracker);
        MinecraftForge.EVENT_BUS.register(new HudWidgetRenderer(collector, hudLayout));
        MinecraftForge.EVENT_BUS.register(new KeystrokesRenderer(hudLayout));
        MinecraftForge.EVENT_BUS.register(new CpsRenderer(cpsTracker, hudLayout));
        MinecraftForge.EVENT_BUS.register(new CpsInputHandler(cpsTracker));
        MinecraftForge.EVENT_BUS.register(new ArmorPotionRenderer(hudLayout));
        MinecraftForge.EVENT_BUS.register(new CrosshairRenderer(hudLayout));
        MinecraftForge.EVENT_BUS.register(new HudEditorController(net.minecraft.client.Minecraft.getMinecraft(), hudLayout));
    }

    private void registerHudElements() {
        hudLayout.add(new HudElementState("fps", "FPS", 8.0F, 8.0F));
        hudLayout.add(new HudElementState("ping", "Ping", 8.0F, 30.0F));
        hudLayout.add(new HudElementState("cps", "CPS", 8.0F, 52.0F));
        hudLayout.add(new HudElementState("keystrokes", "Keystrokes", 8.0F, 76.0F));
        hudLayout.add(new HudElementState("armor", "Armor", 8.0F, 140.0F));
        hudLayout.add(new HudElementState("potions", "Potions", 8.0F, 152.0F));
        hudLayout.add(new HudElementState("coordinates", "Coordinates", 8.0F, 164.0F));
        hudLayout.add(new HudElementState("crosshair", "Crosshair", 0.0F, 0.0F));
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

    public PerformanceStats getPerformanceStats() { return performanceStats; }
    public HudLayoutManager getHudLayout() { return hudLayout; }
}
