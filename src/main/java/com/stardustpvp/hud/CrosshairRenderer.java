package com.stardustpvp.hud;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

/** Simple configurable client-side crosshair overlay. */
public final class CrosshairRenderer {
    private final Minecraft mc = Minecraft.getMinecraft();
    private final HudLayoutManager layout;

    public CrosshairRenderer(HudLayoutManager layout) { this.layout = layout; }

    @SubscribeEvent
    public void render(RenderGameOverlayEvent.Post event) {
        if (event.type != RenderGameOverlayEvent.ElementType.ALL) return;
        HudElementState e = layout.get(HudWidgetIds.CROSSHAIR);
        if (e == null || !e.isEnabled() || mc.thePlayer == null) return;
        int cx = mc.displayWidth / 2;
        int cy = mc.displayHeight / 2;
        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GL11.glLineWidth(1.0F);
        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex2f(cx - 5, cy); GL11.glVertex2f(cx + 5, cy);
        GL11.glVertex2f(cx, cy - 5); GL11.glVertex2f(cx, cy + 5);
        GL11.glEnd();
        GlStateManager.enableTexture2D();
        GlStateManager.popMatrix();
    }
}
