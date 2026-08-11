package com.stardustpvp.hud;

/** Immutable snapshot used by the client HUD to expose feature state safely. */
public final class FeatureStatus {
    public final boolean hudEnabled;
    public final boolean keystrokesEnabled;
    public final boolean crosshairEnabled;

    public FeatureStatus(boolean hudEnabled, boolean keystrokesEnabled, boolean crosshairEnabled) {
        this.hudEnabled = hudEnabled;
        this.keystrokesEnabled = keystrokesEnabled;
        this.crosshairEnabled = crosshairEnabled;
    }
}
