package com.stardustpvp.performance;

/** Immutable PvP performance profile. OptiFine remains the renderer owner. */
public final class PerformanceProfile {
    public final boolean reduceParticles;
    public final boolean hideEntityShadows;
    public final boolean reduceWeather;
    public final boolean animatedTextures;
    public final boolean fastMath;

    private PerformanceProfile(boolean reduceParticles, boolean hideEntityShadows,
                               boolean reduceWeather, boolean animatedTextures, boolean fastMath) {
        this.reduceParticles = reduceParticles;
        this.hideEntityShadows = hideEntityShadows;
        this.reduceWeather = reduceWeather;
        this.animatedTextures = animatedTextures;
        this.fastMath = fastMath;
    }

    public static PerformanceProfile competitive() {
        return new PerformanceProfile(true, true, true, false, true);
    }

    public static PerformanceProfile balanced() {
        return new PerformanceProfile(true, true, false, true, true);
    }
}
