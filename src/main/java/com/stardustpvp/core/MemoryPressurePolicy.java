package com.stardustpvp.core;

/** Converts observed JVM memory pressure into conservative cosmetic hints. */
public final class MemoryPressurePolicy {
    public enum Level { NORMAL, ELEVATED, HIGH }

    public Level evaluate(double usedRatio) {
        if (usedRatio >= 0.90) return Level.HIGH;
        if (usedRatio >= 0.80) return Level.ELEVATED;
        return Level.NORMAL;
    }

    public boolean reduceCosmetics(Level level) {
        return level != Level.NORMAL;
    }
}
