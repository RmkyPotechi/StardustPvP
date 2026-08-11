package com.stardustpvp.core;

/** Safe performance profile: only cosmetic workloads may be reduced. */
public final class OptimizationProfile {
    public enum Level { DEFAULT, BALANCED, COMPETITIVE }

    private Level level = Level.BALANCED;

    public Level getLevel() { return level; }
    public void setLevel(Level level) { this.level = level == null ? Level.BALANCED : level; }

    public int particleLimit() {
        switch (level) {
            case COMPETITIVE: return 120;
            case BALANCED: return 250;
            default: return 500;
        }
    }

    public int cosmeticLimit() {
        switch (level) {
            case COMPETITIVE: return 250;
            case BALANCED: return 500;
            default: return 1000;
        }
    }
}
