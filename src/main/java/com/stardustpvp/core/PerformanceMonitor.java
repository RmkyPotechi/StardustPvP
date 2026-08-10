package com.stardustpvp.core;

/** Lightweight frame-time monitor. Hot-path updates perform no heap allocation. */
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
        long worst = 0L;
        for (int i = 0; i < count; i++) {
            long sample = samples[i];
            total += sample;
            if (sample > worst) worst = sample;
        }
        averageFrameMs = (total / (double) count) / 1_000_000.0;
        // A conservative allocation-free proxy for the 1% low: worst observed frame
        // in the rolling window. The benchmark harness will calculate exact percentiles.
        low1FrameMs = worst / 1_000_000.0;
        fps = averageFrameMs <= 0.0 ? 0.0 : 1000.0 / averageFrameMs;
    }

    public double getFps() { return fps; }
    public double getAverageFrameMs() { return averageFrameMs; }
    public double getLow1FrameMs() { return low1FrameMs; }
}
