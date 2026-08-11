package com.stardustpvp.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/** Lightweight visual renderer for the built-in HUD widgets. */
public final class HudWidgetRenderer {
    private final Minecraft mc = Minecraft.getMinecraft();
    private final HudWidgetCollector collector;

    public HudWidgetRenderer(HudWidgetCollector collector) {
        this.collector = collector;
    }

    @SubscribeEvent
    public void onOverlay(RenderGameOverlayEvent.Post event) {
        if (event.type != RenderGameOverlayEvent.ElementType.ALL || mc.thePlayer == null) return;
        HudWidgetState s = collector.collect(System.currentTimeMillis());
        FontRenderer font = mc.fontRendererObj;
        int x = 8;
        int y = 8;
        draw(font, "FPS: " + s.fps, x, y); y += 10;
        draw(font, "Ping: " + (s.ping < 0 ? "-" : s.ping + "ms"), x, y); y += 10;
        draw(font, "CPS: " + s.cps, x, y); y += 12;
        draw(font, "XYZ: " + (int)s.x + " " + (int)s.y + " " + (int)s.z, x, y); y += 12;
        draw(font, "Armor: " + s.armorCount, x, y); y += 10;
        draw(font, "Potions: " + s.activePotionCount, x, y);
    }

    private void draw(FontRenderer font, String text, int x, int y) {
        font.drawStringWithShadow(text, x, y, 0xFFFFFF);
    }
}
