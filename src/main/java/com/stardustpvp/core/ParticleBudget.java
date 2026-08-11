package com.stardustpvp.core;

/**
 * Frame-safe particle budget bookkeeping. It does not alter packets or game
 * logic; callers can use it to skip purely cosmetic particles when a frame
 * budget is exceeded.
 */
public final class ParticleBudget {
    private int perFrameLimit = 250;
    private int emitted;
    private long frameId = Long.MIN_VALUE;

    public boolean allow(long currentFrame) {
        if (currentFrame != frameId) {
            frameId = currentFrame;
            emitted = 0;
        }
        if (emitted >= perFrameLimit) return false;
        emitted++;
        return true;
    }

    public int getPerFrameLimit() {
        return perFrameLimit;
    }

    public void setPerFrameLimit(int limit) {
        perFrameLimit = Math.max(0, limit);
    }
}
