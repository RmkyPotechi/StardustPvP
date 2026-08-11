package com.stardustpvp.core;

/**
 * Allocation-free rolling frame statistics for the 1.8.9 client hot path.
 * Stores frame times in a fixed ring and computes exact percentile values
 * only when the HUD asks for them, keeping the render hook cheap.
 */
public final class PerformanceStats {
    private static final int WINDOW = 256;
    private final long[] samples = new long[WINDOW];
    private final long[] scratch = new long[WINDOW];
    private int index;
    private int count;
    private long frameStart;
    private double fps;
    private double averageMs;
    private double p99Ms;
    private double p999Ms;
    private long spikeCount;

    public void beginFrame() {
        frameStart = System.nanoTime();
    }

    public void endFrame() {
        if (frameStart == 0L) return;
        final long elapsed = Math.max(1L, System.nanoTime() - frameStart);
        frameStart = 0L;
        samples[index] = elapsed;
        index = (index + 1) % WINDOW;
        if (count < WINDOW) count++;

        long total = 0L;
        for (int i = 0; i < count; i++) total += samples[i];
        averageMs = total / (double) count / 1_000_000.0;
        fps = averageMs > 0.0 ? 1000.0 / averageMs : 0.0;
        if (elapsed > 33_333_333L) spikeCount++;
    }

    /** Exact percentile over the current fixed-size window, without allocation. */
    private double percentile(double percentile) {
        if (count == 0) return 0.0;
        System.arraycopy(samples, 0, scratch, 0, count);
        for (int i = 1; i < count; i++) {
            long value = scratch[i];
            int j = i - 1;
            while (j >= 0 && scratch[j] > value) {
                scratch[j + 1] = scratch[j--];
            }
            scratch[j + 1] = value;
        }
        int position = (int) Math.ceil(percentile * count) - 1;
        if (position < 0) position = 0;
        if (position >= count) position = count - 1;
        return scratch[position] / 1_000_000.0;
    }

    public double getFps() { return fps; }
    public double getAverageMs() { return averageMs; }
    public double getP99Ms() { return p99Ms == 0.0 && count > 0 ? (p99Ms = percentile(0.99)) : p99Ms; }
    public double getP999Ms() { return p999Ms == 0.0 && count > 0 ? (p999Ms = percentile(0.999)) : p999Ms; }
    public long getSpikeCount() { return spikeCount; }

    public void invalidatePercentiles() {
        p99Ms = 0.0;
        p999Ms = 0.0;
    }
}
