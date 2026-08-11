package com.stardustpvp.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

/** Visual-only configurable crosshair; never changes aim or input. */
public final class CrosshairRenderer {
    private final Minecraft mc = Minecraft.getMinecraft();
    private final HudLayoutManager layout;
    private final CrosshairSettings settings = new CrosshairSettings();

    public CrosshairRenderer(HudLayoutManager layout) { this.layout = layout; }

    @SubscribeEvent
    public void render(RenderGameOverlayEvent.Post event) {
        if (event.type != RenderGameOverlayEvent.ElementType.ALL || mc.thePlayer == null) return;
        HudElementState e = layout.get(HudWidgetIds.CROSSHAIR);
        if (e == null || !e.isEnabled()) return;
        ScaledResolution sr = new ScaledResolution(mc);
        float cx = sr.getScaledWidth() / 2.0F;
        float cy = sr.getScaledHeight() / 2.0F;
        float gap = settings.getGap();
        float size = settings.getSize();
        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GL11.glLineWidth(settings.getThickness());
        GL11.glColor4f(((settings.getColor() >> 16) & 255) / 255.0F, ((settings.getColor() >> 8) & 255) / 255.0F, (settings.getColor() & 255) / 255.0F, 1.0F);
        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex2f(cx - gap - size, cy); GL11.glVertex2f(cx - gap, cy);
        GL11.glVertex2f(cx + gap, cy); GL11.glVertex2f(cx + gap + size, cy);
        GL11.glVertex2f(cx, cy - gap - size); GL11.glVertex2f(cx, cy - gap);
        GL11.glVertex2f(cx, cy + gap); GL11.glVertex2f(cx, cy + gap + size);
        GL11.glEnd();
        if (settings.isDot()) {
            GL11.glBegin(GL11.GL_QUADS);
            GL11.glVertex2f(cx - 1, cy - 1); GL11.glVertex2f(cx + 1, cy - 1);
            GL11.glVertex2f(cx + 1, cy + 1); GL11.glVertex2f(cx - 1, cy + 1);
            GL11.glEnd();
        }
        GlStateManager.enableTexture2D();
        GlStateManager.popMatrix();
    }

    public CrosshairSettings getSettings() { return settings; }
}
