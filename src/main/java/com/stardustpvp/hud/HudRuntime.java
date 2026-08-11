package com.stardustpvp.hud;

/** Central runtime state for HUD features. */
public final class HudRuntime {
    private boolean enabled = true;
    private boolean editorOpen;

    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
    public boolean isEditorOpen() { return editorOpen; }
    public void setEditorOpen(boolean editorOpen) { this.editorOpen = editorOpen; }

    public FeatureStatus getStatus(HudLayoutManager layout) {
        boolean keys = layout.get(HudWidgetIds.KEYSTROKES) != null
                && layout.get(HudWidgetIds.KEYSTROKES).isEnabled();
        boolean crosshair = layout.get(HudWidgetIds.CROSSHAIR) != null
                && layout.get(HudWidgetIds.CROSSHAIR).isEnabled();
        return new FeatureStatus(enabled, keys, crosshair);
    }
}
