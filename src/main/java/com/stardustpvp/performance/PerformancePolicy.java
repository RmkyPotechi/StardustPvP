package com.stardustpvp.performance;

/**
 * Central policy for client-side performance work.
 *
 * The important rule for OptiFine compatibility is that StardustPvP does not
 * replace or duplicate OptiFine's renderer. It only decides whether optional
 * client-side work should run and exposes conservative budgets.
 */
public final class PerformancePolicy {
    private boolean enabled = true;
    private boolean particleBudget = true;
    private boolean entityDistanceBudget = true;
    private boolean animationBudget = true;

    private int maxParticlesPerTick = 120;
    private int optionalEntityDistanceSq = 64 * 64;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isParticleBudgetEnabled() {
        return enabled && particleBudget;
    }

    public boolean isEntityDistanceBudgetEnabled() {
        return enabled && entityDistanceBudget;
    }

    public boolean isAnimationBudgetEnabled() {
        return enabled && animationBudget;
    }

    public int getMaxParticlesPerTick() {
        return maxParticlesPerTick;
    }

    public void setMaxParticlesPerTick(int value) {
        maxParticlesPerTick = clamp(value, 0, 2000);
    }

    public int getOptionalEntityDistanceSq() {
        return optionalEntityDistanceSq;
    }

    public void setOptionalEntityDistanceSq(int value) {
        optionalEntityDistanceSq = clamp(value, 16 * 16, 256 * 256);
    }

    private static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }
}
