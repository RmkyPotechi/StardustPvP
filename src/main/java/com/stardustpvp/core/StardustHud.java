package com.stardustpvp.core;

import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

/** Minimal diagnostics HUD; disabled by default. */
public final class StardustHud {
    private final PerformanceMonitor monitor;

    public StardustHud(PerformanceMonitor monitor) {
        this.monitor = monitor;
    }

    @SubscribeEvent
    public void render(RenderGameOverlayEvent.Text event) {
        if (!StardustConfig.performanceHud) return;
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.fontRendererObj == null || mc.theWorld == null) return;
        FontRenderer font = mc.fontRendererObj;
        int ping = NetworkStats.getPing();
        String text = String.format("Stardust  %.0f FPS  %.2f ms  worst %.2f ms  %d ms ping",
                monitor.getFps(), monitor.getAverageFrameMs(), monitor.getLow1FrameMs(), ping);
        font.drawStringWithShadow(text, 4, 4, 0xFFFFFF);
    }
}
