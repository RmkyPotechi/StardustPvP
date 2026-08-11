package com.stardustpvp.hud;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;

/**
 * Minimal 1.8.9 Forge HUD editor screen. Elements can be selected, moved and
 * scaled with the mouse. It only edits client-side HUD layout state.
 */
public final class HudEditorScreen extends GuiScreen {
    private final HudLayoutManager layout;
    private String selectedId;
    private boolean dragging;
    private float dragOffsetX;
    private float dragOffsetY;

    public HudEditorScreen(HudLayoutManager layout) {
        this.layout = layout;
    }

    @Override
    public void initGui() {
        buttonList.clear();
        buttonList.add(new GuiButton(0, width - 105, height - 28, 100, 20, "Done"));
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 0) mc.displayGuiScreen(null);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        drawCenteredString(fontRendererObj, "Stardust HUD Editor", width / 2, 12, 0xFFFFFF);

        for (HudElement element : layoutElements()) {
            if (!element.isEnabled()) continue;
            int x = Math.round(element.getX());
            int y = Math.round(element.getY());
            int w = 90;
            int h = 22;
            boolean selected = element.getId().equals(selectedId);
            drawRect(x - 2, y - 2, x + w + 2, y + h + 2, selected ? 0xAA55AAFF : 0x66333333);
            drawString(fontRendererObj, element.getDisplayName(), x + 4, y + 6, 0xFFFFFF);
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    private Iterable<HudElement> layoutElements() {
        return new Iterable<HudElement>() {
            public java.util.Iterator<HudElement> iterator() {
                return layout.getElements().iterator();
            }
        };
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (mouseButton != 0) return;
        HudElement hit = hitTest(mouseX, mouseY);
        if (hit != null) {
            selectedId = hit.getId();
            dragging = true;
            dragOffsetX = mouseX - hit.getX();
            dragOffsetY = mouseY - hit.getY();
        }
    }

    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        if (!dragging || selectedId == null) return;
        HudElement element = find(selectedId);
        if (element != null) {
            element.setPosition(mouseX - dragOffsetX, mouseY - dragOffsetY);
        }
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        dragging = false;
        super.mouseReleased(mouseX, mouseY, state);
    }

    private HudElement find(String id) {
        for (HudElement element : layout.getElements()) {
            if (element.getId().equals(id)) return element;
        }
        return null;
    }

    private HudElement hitTest(int mouseX, int mouseY) {
        for (HudElement element : layout.getElements()) {
            if (!element.isEnabled()) continue;
            float x = element.getX();
            float y = element.getY();
            if (mouseX >= x && mouseX <= x + 90 && mouseY >= y && mouseY <= y + 22) return element;
        }
        return null;
    }
}
