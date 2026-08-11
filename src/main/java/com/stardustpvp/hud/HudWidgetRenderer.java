package com.stardustpvp.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/** Renders live HUD widgets using the same layout state edited by HUD Editor. */
public final class HudWidgetRenderer {
    private final Minecraft mc = Minecraft.getMinecraft();
    private final HudWidgetCollector collector;
    private final HudLayoutManager layout;

    public HudWidgetRenderer(HudWidgetCollector collector, HudLayoutManager layout) {
        this.collector = collector;
        this.layout = layout;
    }

    @SubscribeEvent
    public void onOverlay(RenderGameOverlayEvent.Post event) {
        if (event.type != RenderGameOverlayEvent.ElementType.ALL || mc.thePlayer == null) return;
        HudWidgetState s = collector.collect(System.currentTimeMillis());
        FontRenderer font = mc.fontRendererObj;
        drawElement(font, HudWidgetIds.FPS, "FPS: " + s.fps);
        drawElement(font, HudWidgetIds.PING, "Ping: " + (s.ping < 0 ? "-" : s.ping + "ms"));
        drawElement(font, HudWidgetIds.CPS, "CPS: " + s.cps);
        drawElement(font, HudWidgetIds.COORDINATES, "XYZ: " + (int)s.x + " " + (int)s.y + " " + (int)s.z);
        drawElement(font, HudWidgetIds.ARMOR, "Armor: " + s.armorCount);
        drawElement(font, HudWidgetIds.POTIONS, "Potions: " + s.activePotionCount);
    }

    private void drawElement(FontRenderer font, String id, String text) {
        HudElementState element = layout.get(id);
        if (element == null || !element.isEnabled()) return;
        float scale = element.getScale();
        int x = Math.round(element.getX());
        int y = Math.round(element.getY());
        if (scale == 1.0F) {
            font.drawStringWithShadow(text, x, y, 0xFFFFFF);
            return;
        }
        net.minecraft.client.renderer.GlStateManager.pushMatrix();
        net.minecraft.client.renderer.GlStateManager.translate(x, y, 0.0F);
        net.minecraft.client.renderer.GlStateManager.scale(scale, scale, 1.0F);
        font.drawStringWithShadow(text, 0, 0, 0xFFFFFF);
        net.minecraft.client.renderer.GlStateManager.popMatrix();
    }
}
