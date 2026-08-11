package com.stardustpvp.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/** Visual-only Keystrokes widget. */
public final class KeystrokesRenderer {
    private final Minecraft mc = Minecraft.getMinecraft();
    private final HudLayoutManager layout;

    public KeystrokesRenderer(HudLayoutManager layout) { this.layout = layout; }

    @SubscribeEvent
    public void render(RenderGameOverlayEvent.Post event) {
        if (event.type != RenderGameOverlayEvent.ElementType.ALL) return;
        HudElementState e = layout.get(HudWidgetIds.KEYSTROKES);
        if (e == null || !e.isEnabled() || mc.thePlayer == null) return;
        FontRenderer f = mc.fontRendererObj;
        int x = Math.round(e.getX()), y = Math.round(e.getY());
        drawKey(f, "W", x + 22, y, mc.gameSettings.keyBindForward.isKeyDown());
        drawKey(f, "A", x, y + 22, mc.gameSettings.keyBindLeft.isKeyDown());
        drawKey(f, "S", x + 22, y + 22, mc.gameSettings.keyBindBack.isKeyDown());
        drawKey(f, "D", x + 44, y + 22, mc.gameSettings.keyBindRight.isKeyDown());
        drawKey(f, "SP", x + 10, y + 44, mc.gameSettings.keyBindJump.isKeyDown());
    }

    private void drawKey(FontRenderer f, String label, int x, int y, boolean pressed) {
        int bg = pressed ? 0xAAFFFFFF : 0x66333333;
        mc.ingameGUI.drawRect(x, y, x + 20, y + 18, bg);
        f.drawString(label, x + 7 - (label.length() - 1) * 2, y + 5, pressed ? 0x000000 : 0xFFFFFF);
    }
}
