package com.stardustpvp.client;

/** Allocation-free rolling frame-time/FPS monitor for the client HUD. */
public final class PerformanceMonitor {
    private static final int SAMPLE_COUNT = 120;
    private final long[] samples = new long[SAMPLE_COUNT];
    private int cursor;
    private int count;
    private long lastFrame = System.nanoTime();

    public void frame() {
        long now = System.nanoTime();
        long delta = now - lastFrame;
        lastFrame = now;
        if (delta <= 0L || delta > 1_000_000_000L) return;
        samples[cursor] = delta;
        cursor = (cursor + 1) % SAMPLE_COUNT;
        if (count < SAMPLE_COUNT) count++;
    }

    public double getAverageFrameTimeMs() {
        if (count == 0) return 0.0;
        long total = 0L;
        for (int i = 0; i < count; i++) total += samples[i];
        return total / (double) count / 1_000_000.0;
    }

    public double getFps() {
        double ms = getAverageFrameTimeMs();
        return ms <= 0.0 ? 0.0 : 1000.0 / ms;
    }
}
