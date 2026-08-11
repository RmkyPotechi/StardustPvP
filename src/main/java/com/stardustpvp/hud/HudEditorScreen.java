package com.stardustpvp.hud;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;

/** Interactive client-side HUD editor for Minecraft 1.8.9. */
public final class HudEditorScreen extends GuiScreen {
    private final HudLayoutManager layout;
    private final HudConfigStore config = new HudConfigStore();
    private String selectedId;
    private boolean dragging;
    private float dragOffsetX, dragOffsetY;

    public HudEditorScreen(HudLayoutManager layout) { this.layout = layout; }

    @Override public void initGui() {
        buttonList.clear();
        buttonList.add(new GuiButton(0, width - 105, height - 28, 100, 20, "Done"));
        buttonList.add(new GuiButton(1, 5, height - 28, 70, 20, "Scale +"));
        buttonList.add(new GuiButton(2, 80, height - 28, 70, 20, "Scale -"));
        buttonList.add(new GuiButton(3, 155, height - 28, 85, 20, "Toggle"));
        buttonList.add(new GuiButton(10, width - 370, height - 28, 75, 20, "Minimal"));
        buttonList.add(new GuiButton(11, width - 290, height - 28, 55, 20, "PvP"));
        buttonList.add(new GuiButton(12, width - 230, height - 28, 75, 20, "BedWars"));
        buttonList.add(new GuiButton(13, width - 150, height - 28, 55, 20, "Save"));
        buttonList.add(new GuiButton(14, width - 205, height - 28, 50, 20, "Load"));
    }

    @Override protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 0) { config.save(layout); mc.displayGuiScreen(null); return; }
        if (button.id == 1) scaleSelected(0.1F);
        else if (button.id == 2) scaleSelected(-0.1F);
        else if (button.id == 3 && selectedId != null) layout.toggle(selectedId);
        else if (button.id == 10) HudPresetLayout.apply(layout, HudPreset.MINIMAL);
        else if (button.id == 11) HudPresetLayout.apply(layout, HudPreset.PVP);
        else if (button.id == 12) HudPresetLayout.apply(layout, HudPreset.BEDWARS);
        else if (button.id == 13) config.save(layout);
        else if (button.id == 14) config.load(layout);
    }

    private void scaleSelected(float delta) {
        if (selectedId == null) return;
        HudElement element = find(selectedId);
        if (element != null) element.setScale(element.getScale() + delta);
    }

    @Override public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        drawCenteredString(fontRendererObj, "Stardust HUD Editor - " + layout.getPreset().name(), width / 2, 12, 0xFFFFFF);
        for (HudElement element : layout.getElements()) {
            if (!element.isEnabled()) continue;
            int x = Math.round(element.getX()), y = Math.round(element.getY());
            int w = Math.round(90 * element.getScale()), h = Math.round(22 * element.getScale());
            boolean selected = element.getId().equals(selectedId);
            drawRect(x - 2, y - 2, x + w + 2, y + h + 2, selected ? 0xAA55AAFF : 0x66333333);
            drawString(fontRendererObj, element.getDisplayName(), x + 4, y + 6, 0xFFFFFF);
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (mouseButton != 0) return;
        HudElement hit = hitTest(mouseX, mouseY);
        if (hit != null) { selectedId = hit.getId(); dragging = true; dragOffsetX = mouseX - hit.getX(); dragOffsetY = mouseY - hit.getY(); }
    }

    @Override protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        if (!dragging || selectedId == null) return;
        HudElement element = find(selectedId);
        if (element != null) element.setPosition(mouseX - dragOffsetX, mouseY - dragOffsetY);
    }

    @Override protected void mouseReleased(int mouseX, int mouseY, int state) { dragging = false; super.mouseReleased(mouseX, mouseY, state); }

    private HudElement find(String id) { for (HudElement e : layout.getElements()) if (e.getId().equals(id)) return e; return null; }
    private HudElement hitTest(int mouseX, int mouseY) {
        for (HudElement e : layout.getElements()) {
            if (!e.isEnabled()) continue;
            float x=e.getX(), y=e.getY(), w=90*e.getScale(), h=22*e.getScale();
            if (mouseX>=x && mouseX<=x+w && mouseY>=y && mouseY<=y+h) return e;
        }
        return null;
    }
}