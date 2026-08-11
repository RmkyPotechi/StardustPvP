package com.stardustpvp.core;

/**
 * Low-overhead JVM memory telemetry. It never forces a GC; callers can use the
 * data to identify pressure without introducing a collection spike themselves.
 */
public final class MemoryMonitor {
    private long lastSampleNs;
    private long usedBytes;
    private long committedBytes;
    private long maxBytes;

    public boolean sample(long nowNs) {
        if (nowNs - lastSampleNs < 250_000_000L) return false;
        lastSampleNs = nowNs;
        Runtime runtime = Runtime.getRuntime();
        long total = runtime.totalMemory();
        long free = runtime.freeMemory();
        usedBytes = total - free;
        committedBytes = total;
        maxBytes = runtime.maxMemory();
        return true;
    }

    public long getUsedBytes() { return usedBytes; }
    public long getCommittedBytes() { return committedBytes; }
    public long getMaxBytes() { return maxBytes; }

    public double getUsedRatio() {
        return maxBytes <= 0L ? 0.0 : usedBytes / (double) maxBytes;
    }
}
