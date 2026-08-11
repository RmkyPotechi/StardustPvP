package com.stardustpvp.hud;

/** Validated client-side HUD settings. */
public final class HudSettings {
    private float scale = 1.0F;
    private boolean enabled = true;

    public float getScale() { return scale; }
    public boolean isEnabled() { return enabled; }

    public void setScale(float scale) {
        this.scale = Math.max(0.5F, Math.min(3.0F, scale));
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
