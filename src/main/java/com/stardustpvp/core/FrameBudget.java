package com.stardustpvp.core;

/**
 * Small frame-budget controller used to keep optional cosmetic work from
 * becoming a source of frame-time spikes. It only provides a budget decision;
 * render hooks decide whether a cosmetic task can be skipped safely.
 */
public final class FrameBudget {
    private static final long DEFAULT_BUDGET_NS = 16_666_667L;
    private long budgetNs = DEFAULT_BUDGET_NS;
    private long frameStartNs;

    public void beginFrame(long nowNs) {
        frameStartNs = nowNs;
    }

    public boolean hasTimeForOptionalWork(long nowNs, long estimatedCostNs) {
        if (frameStartNs == 0L) return true;
        long elapsed = nowNs - frameStartNs;
        return elapsed + Math.max(0L, estimatedCostNs) <= budgetNs;
    }

    public long getBudgetNs() {
        return budgetNs;
    }

    public void setTargetFps(int fps) {
        if (fps <= 0) {
            budgetNs = DEFAULT_BUDGET_NS;
        } else {
            budgetNs = 1_000_000_000L / Math.min(fps, 1000);
        }
    }
}
