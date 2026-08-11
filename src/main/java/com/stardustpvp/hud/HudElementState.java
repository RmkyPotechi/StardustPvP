package com.stardustpvp.hud;

/** Mutable, validated state shared by the HUD editor and renderers. */
public final class HudElementState implements HudElement {
    private final String id;
    private final String displayName;
    private float x;
    private float y;
    private float scale = 1.0F;
    private boolean enabled = true;

    public HudElementState(String id, String displayName, float x, float y) {
        this.id = id;
        this.displayName = displayName;
        this.x = x;
        this.y = y;
    }

    public String getId() { return id; }
    public String getDisplayName() { return displayName; }
    public float getX() { return x; }
    public float getY() { return y; }
    public float getScale() { return scale; }
    public boolean isEnabled() { return enabled; }
    public void setPosition(float x, float y) { this.x = x; this.y = y; }
    public void setScale(float scale) { this.scale = Math.max(0.5F, Math.min(3.0F, scale)); }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
}
