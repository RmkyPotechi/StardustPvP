package com.stardustpvp.core;

/**
 * Converts recent frame-time pressure into conservative quality levels.
 * Level 0 is full quality; higher levels may be used by cosmetic render
 * modules to reduce optional work. Gameplay-critical rendering is unaffected.
 */
public final class AdaptivePerformance {
    private int level;
    private int pressureFrames;

    public void sample(double frameMs) {
        if (frameMs > 33.0) {
            pressureFrames = Math.min(pressureFrames + 2, 120);
        } else if (frameMs > 20.0) {
            pressureFrames = Math.min(pressureFrames + 1, 120);
        } else {
            pressureFrames = Math.max(0, pressureFrames - 1);
        }

        if (pressureFrames >= 45) level = Math.min(3, level + 1);
        else if (pressureFrames <= 5) level = Math.max(0, level - 1);
    }

    public int getLevel() {
        return level;
    }

    public boolean allowCosmeticWork(int minimumLevel) {
        return level < minimumLevel;
    }
}
