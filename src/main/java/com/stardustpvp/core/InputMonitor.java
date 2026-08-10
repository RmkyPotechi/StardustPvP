package com.stardustpvp.core;

import org.lwjgl.input.Mouse;

/**
 * Raw LWJGL mouse-delta sampler for 1.8.9. It observes input without consuming
 * the event stream, so Minecraft's normal controls remain authoritative.
 */
public final class InputMonitor {
    private int dx;
    private int dy;

    public void sample() {
        if (!StardustConfig.rawMouseInput) return;
        dx = Mouse.getDX();
        dy = Mouse.getDY();
    }

    public int getDx() { return dx; }
    public int getDy() { return dy; }
}
