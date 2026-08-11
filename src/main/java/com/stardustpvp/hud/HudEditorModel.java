package com.stardustpvp.hud;

/** Interaction state for a future Minecraft GUI screen. */
public final class HudEditorModel {
    private final HudLayoutManager layout;
    private String selectedId;
    private boolean editing;

    public HudEditorModel(HudLayoutManager layout) {
        this.layout = layout;
    }

    public void begin(String id) {
        if (layout.get(id) == null) return;
        selectedId = id;
        editing = true;
    }

    public void moveSelected(float x, float y) {
        if (editing) layout.move(selectedId, x, y);
    }

    public void scaleSelected(float scale) {
        if (editing) layout.scale(selectedId, scale);
    }

    public void end() {
        selectedId = null;
        editing = false;
    }

    public String getSelectedId() { return selectedId; }
    public boolean isEditing() { return editing; }
}
