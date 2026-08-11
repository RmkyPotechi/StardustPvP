package com.stardustpvp.hud;

/** Client-side state shared by the visual HUD widgets. */
public final class HudWidgetState {
    public int fps;
    public int ping = -1;
    public int cps;
    public int armorCount;
    public int activePotionCount;
    public double x;
    public double y;
    public double z;
    public boolean crosshairEnabled = true;

    public void resetTransient() {
        cps = 0;
        ping = -1;
    }
}
