package com.stardustpvp.hud;

/** Lightweight client-side CPS tracker; it never generates or modifies clicks. */
public final class CpsTracker {
    private final long[] clicks = new long[32];
    private int size;
    private int cursor;

    public void recordClick(long nowMs) {
        clicks[cursor] = nowMs;
        cursor = (cursor + 1) % clicks.length;
        if (size < clicks.length) size++;
    }

    public int getClicksLastSecond(long nowMs) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            long t = clicks[i];
            if (nowMs - t <= 1000L) count++;
        }
        return count;
    }
}
