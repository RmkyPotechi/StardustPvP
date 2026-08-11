package com.stardustpvp.core;

/**
 * Keeps optional cosmetic work from being scheduled ahead of input handling.
 * This is a policy helper only; actual Minecraft input processing remains
 * authoritative and untouched.
 */
public final class InputRenderPriority {
    private static final long INPUT_PRIORITY_WINDOW_NS = 8_000_000L;

    public boolean deferOptionalWork(long nowNs, long lastInputNs) {
        if (lastInputNs == 0L) return false;
        return nowNs - lastInputNs < INPUT_PRIORITY_WINDOW_NS;
    }
}
