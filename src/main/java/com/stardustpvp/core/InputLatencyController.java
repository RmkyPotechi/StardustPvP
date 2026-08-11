package com.stardustpvp.core;

/**
 * Lightweight client-side input timing helper. It never synthesizes clicks,
 * changes CPS, or modifies packets; it only records input timestamps so the
 * client can avoid adding unnecessary work between input and rendering.
 */
public final class InputLatencyController {
    private long lastMouseInputNs;
    private long lastKeyboardInputNs;

    public void recordMouseInput(long nowNs) {
        lastMouseInputNs = nowNs;
    }

    public void recordKeyboardInput(long nowNs) {
        lastKeyboardInputNs = nowNs;
    }

    public long mouseAgeNs(long nowNs) {
        return lastMouseInputNs == 0L ? Long.MAX_VALUE : Math.max(0L, nowNs - lastMouseInputNs);
    }

    public long keyboardAgeNs(long nowNs) {
        return lastKeyboardInputNs == 0L ? Long.MAX_VALUE : Math.max(0L, nowNs - lastKeyboardInputNs);
    }
}
