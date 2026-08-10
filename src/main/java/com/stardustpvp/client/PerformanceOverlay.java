package com.stardustpvp.client;

import com.stardustpvp.config.StardustConfig;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.opengl.GL11;

/** Optional, very cheap performance diagnostics. */
public final class PerformanceOverlay {
    private final PerformanceMonitor monitor = new PerformanceMonitor();

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) monitor.frame();
    }

    @SubscribeEvent
    public void onOverlay(RenderGameOverlayEvent.Text event) {
        if (!StardustConfig.performanceHud) return;
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.fontRendererObj == null) return;

        GL11.glPushMatrix();
        try {
            String text = String.format("Stardust  FPS %.0f  Frame %.2f ms", monitor.getFps(), monitor.getAverageFrameTimeMs());
            mc.fontRendererObj.drawStringWithShadow(text, 4, 4, 0xFFFFFF);
        } finally {
            GL11.glPopMatrix();
        }
    }
}
