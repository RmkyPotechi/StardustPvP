package com.stardustpvp.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/** Minimal 1.8.9 HUD renderer. Rendering is intentionally cheap and data-only. */
public final class HudRenderer {
    private final Minecraft minecraft = Minecraft.getMinecraft();
    private final HudRegistry registry;

    public HudRenderer(HudRegistry registry) {
        this.registry = registry;
    }

    @SubscribeEvent
    public void onOverlay(RenderGameOverlayEvent.Post event) {
        if (event.type != RenderGameOverlayEvent.ElementType.ALL) return;
        ScaledResolution resolution = new ScaledResolution(minecraft);
        for (HudElement element : registry.getElements()) {
            if (!element.isEnabled()) continue;
            renderPlaceholder(element, resolution);
        }
    }

    private void renderPlaceholder(HudElement element, ScaledResolution resolution) {
        // The first pass deliberately renders only the element name. Individual
        // modules will supply their own compact renderers in later commits.
        int x = Math.max(0, Math.min(resolution.getScaledWidth() - 1, Math.round(element.getX())));
        int y = Math.max(0, Math.min(resolution.getScaledHeight() - 10, Math.round(element.getY())));
        minecraft.fontRendererObj.drawStringWithShadow(element.getDisplayName(), x, y, 0xFFFFFF);
    }
}
