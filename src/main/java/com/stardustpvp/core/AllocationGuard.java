package com.stardustpvp.core;

/**
 * Tracks allocation-sensitive paths without attempting unsafe JVM/GC tricks.
 * Intended for diagnostics and future render modules rather than replacing
 * Minecraft's memory management.
 */
public final class AllocationGuard {
    private long cosmeticObjects;
    private long skippedCosmeticObjects;

    public void recordCosmeticObject() { cosmeticObjects++; }
    public void recordSkippedCosmeticObject() { skippedCosmeticObjects++; }

    public long getCosmeticObjects() { return cosmeticObjects; }
    public long getSkippedCosmeticObjects() { return skippedCosmeticObjects; }
}
