package com.stardustpvp.hud;

/** Simple layout operations used by a future in-game HUD editor. */
public final class HudLayout {
    public void move(HudElement element, float x, float y) {
        if (element != null) element.setPosition(x, y);
    }

    public void scale(HudElement element, float scale) {
        if (element != null) element.setScale(scale);
    }

    public void toggle(HudElement element) {
        if (element != null) element.setEnabled(!element.isEnabled());
    }
}
