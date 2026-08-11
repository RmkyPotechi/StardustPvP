package com.stardustpvp.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/** Visual-only CPS widget. */
public final class CpsRenderer {
    private final Minecraft mc = Minecraft.getMinecraft();
    private final CpsTracker tracker;
    private final HudLayoutManager layout;

    public CpsRenderer(CpsTracker tracker, HudLayoutManager layout) {
        this.tracker = tracker;
        this.layout = layout;
    }

    @SubscribeEvent
    public void render(RenderGameOverlayEvent.Post event) {
        if (event.type != RenderGameOverlayEvent.ElementType.ALL || mc.thePlayer == null) return;
        HudElementState e = layout.get(HudWidgetIds.CPS);
        if (e == null || !e.isEnabled()) return;
        FontRenderer f = mc.fontRendererObj;
        f.drawStringWithShadow("CPS: " + tracker.getClicksLastSecond(System.currentTimeMillis()),
                Math.round(e.getX()), Math.round(e.getY()), 0xFFFFFF);
    }
}
