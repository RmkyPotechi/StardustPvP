package com.stardustpvp.performance;

/**
 * Small allocation-free frame budget helper. It does not replace Minecraft/OptiFine
 * rendering; it only lets StardustPvP decide whether optional cosmetic work should run.
 */
public final class RenderBudget {
    private final long budgetNanos;
    private long frameStart;

    public RenderBudget(double targetFps) {
        double fps = Math.max(30.0, Math.min(1000.0, targetFps));
        this.budgetNanos = (long) (1_000_000_000.0 / fps);
    }

    public void beginFrame() {
        frameStart = System.nanoTime();
    }

    public boolean hasTimeForOptionalWork() {
        if (frameStart == 0L) return true;
        return System.nanoTime() - frameStart < budgetNanos;
    }
}
