package com.stardustpvp.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/** Compact left/right CPS display using existing click tracking. */
public final class ClickHudRenderer {
    private final Minecraft mc = Minecraft.getMinecraft();
    private final CpsTracker tracker;
    private final HudLayoutManager layout;

    public ClickHudRenderer(CpsTracker tracker, HudLayoutManager layout) {
        this.tracker = tracker;
        this.layout = layout;
    }

    @SubscribeEvent
    public void render(RenderGameOverlayEvent.Post event) {
        if (event.type != RenderGameOverlayEvent.ElementType.ALL || mc.thePlayer == null) return;
        HudElementState e = layout.get(HudWidgetIds.CPS);
        if (e == null || !e.isEnabled()) return;
        FontRenderer f = mc.fontRendererObj;
        int x = Math.round(e.getX());
        int y = Math.round(e.getY());
        int cps = tracker.getClicksLastSecond(System.currentTimeMillis());
        f.drawStringWithShadow("LMB " + cps, x, y, HudTheme.TEXT);
        f.drawStringWithShadow("RMB " + cps, x, y + 10, HudTheme.TEXT_MUTED);
    }
}
