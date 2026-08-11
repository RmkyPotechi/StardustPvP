package com.stardustpvp.hud;

/** Base contract for lightweight, client-side HUD elements. */
public interface HudElement {
    String getId();
    String getDisplayName();
    float getX();
    float getY();
    float getScale();
    boolean isEnabled();
    void setPosition(float x, float y);
    void setScale(float scale);
    void setEnabled(boolean enabled);
}
