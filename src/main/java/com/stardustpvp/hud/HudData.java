package com.stardustpvp.hud;

/** Immutable render snapshot for lightweight HUD widgets. */
public final class HudData {
    public final int fps;
    public final int ping;
    public final int cps;

    public HudData(int fps, int ping, int cps) {
        this.fps = Math.max(0, fps);
        this.ping = Math.max(0, ping);
        this.cps = Math.max(0, cps);
    }
}
