package com.stardustpvp.core;

/** Keeps the render loop from adding optional work immediately after input. */
public final class InputFramePolicy {
    private static final long PRIORITY_NS = 8_000_000L;

    public boolean prioritizeInput(long nowNs, long lastInputNs) {
        return lastInputNs != 0L && nowNs - lastInputNs < PRIORITY_NS;
    }
}
