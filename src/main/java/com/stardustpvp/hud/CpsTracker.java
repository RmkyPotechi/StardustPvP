package com.stardustpvp.hud;

/** Lightweight client-side left/right CPS tracker; never generates clicks. */
public final class CpsTracker {
    private final long[] leftClicks = new long[32];
    private final long[] rightClicks = new long[32];
    private int leftSize, leftCursor;
    private int rightSize, rightCursor;

    public void recordLeftClick(long nowMs) {
        leftClicks[leftCursor] = nowMs;
        leftCursor = (leftCursor + 1) % leftClicks.length;
        if (leftSize < leftClicks.length) leftSize++;
    }

    public void recordRightClick(long nowMs) {
        rightClicks[rightCursor] = nowMs;
        rightCursor = (rightCursor + 1) % rightClicks.length;
        if (rightSize < rightClicks.length) rightSize++;
    }

    public int getLeftClicksLastSecond(long nowMs) { return countRecent(leftClicks, leftSize, nowMs); }
    public int getRightClicksLastSecond(long nowMs) { return countRecent(rightClicks, rightSize, nowMs); }

    /** Backwards-compatible total count for existing collectors. */
    public int getClicksLastSecond(long nowMs) {
        return getLeftClicksLastSecond(nowMs) + getRightClicksLastSecond(nowMs);
    }

    private int countRecent(long[] values, int size, long nowMs) {
        int count = 0;
        for (int i = 0; i < size; i++) if (nowMs - values[i] <= 1000L) count++;
        return count;
    }
}
