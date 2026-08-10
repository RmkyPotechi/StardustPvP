package com.stardustpvp.core;

/** Lightweight frame-time monitor. Uses a fixed ring buffer to avoid per-frame allocations. */
public final class PerformanceMonitor {
    private static final int SIZE = 120;
    private final long[] samples = new long[SIZE];
    private int index;
    private int count;
    private long frameStart;
    private double fps;
    private double averageFrameMs;
    private double low1FrameMs;

    public void beginFrame() {
        frameStart = System.nanoTime();
    }

    public void endFrame() {
        if (frameStart == 0L) return;
        long elapsed = Math.max(1L, System.nanoTime() - frameStart);
        samples[index] = elapsed;
        index = (index + 1) % SIZE;
        if (count < SIZE) count++;
        recompute();
    }

    private void recompute() {
        long total = 0L;
        long worstFastestThreshold = Long.MIN_VALUE;
        for (int i = 0; i < count; i++) total += samples[i];
        long[] copy = new long[count];
        System.arraycopy(samples, 0, copy, 0, count);
        java.util.Arrays.sort(copy);
        int lowCount = Math.max(1, (int) Math.ceil(count * 0.01));
        for (int i = count - lowCount; i < count; i++) worstFastestThreshold += copy[i];
        averageFrameMs = (total / (double) count) / 1_000_000.0;
        low1FrameMs = ((total - worstFastestThreshold) / (double) Math.max(1, count - lowCount)) / 1_000_000.0;
        fps = averageFrameMs <= 0.0 ? 0.0 : 1000.0 / averageFrameMs;
    }

    public double getFps() { return fps; }
    public double getAverageFrameMs() { return averageFrameMs; }
    public double getLow1FrameMs() { return low1FrameMs; }
}
