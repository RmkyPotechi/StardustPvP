package com.stardustpvp.core;

/** Immutable performance profile. All values are client-side only. */
public final class PerformanceSettings {
    public final boolean reduceParticles;
    public final boolean reduceEntityShadows;
    public final boolean minimumViewBobbing;
    public final boolean rawMouseInput;

    public PerformanceSettings(boolean reduceParticles, boolean reduceEntityShadows,
                               boolean minimumViewBobbing, boolean rawMouseInput) {
        this.reduceParticles = reduceParticles;
        this.reduceEntityShadows = reduceEntityShadows;
        this.minimumViewBobbing = minimumViewBobbing;
        this.rawMouseInput = rawMouseInput;
    }

    public static PerformanceSettings competitiveDefaults() {
        return new PerformanceSettings(true, true, true, true);
    }
}
