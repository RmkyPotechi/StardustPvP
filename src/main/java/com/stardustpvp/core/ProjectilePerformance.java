package com.stardustpvp.core;

/**
 * Client-only projectile performance policy for fast PvP projectiles such as
 * fireballs. It never changes projectile physics, collision, reach, packets,
 * or server state. It only describes which cosmetic work may be reduced.
 */
public final class ProjectilePerformance {
    private boolean optimizeCosmetics = true;
    private int maxTrackedCosmeticProjectiles = 48;

    public boolean shouldRenderCosmetics(int trackedProjectiles, int adaptiveLevel) {
        if (!optimizeCosmetics) return true;
        if (trackedProjectiles <= maxTrackedCosmeticProjectiles) return true;
        return adaptiveLevel < 2;
    }

    public boolean shouldRenderTrail(int adaptiveLevel) {
        return !optimizeCosmetics || adaptiveLevel < 2;
    }

    public boolean isOptimizeCosmetics() {
        return optimizeCosmetics;
    }

    public void setOptimizeCosmetics(boolean optimizeCosmetics) {
        this.optimizeCosmetics = optimizeCosmetics;
    }

    public int getMaxTrackedCosmeticProjectiles() {
        return maxTrackedCosmeticProjectiles;
    }

    public void setMaxTrackedCosmeticProjectiles(int value) {
        maxTrackedCosmeticProjectiles = Math.max(1, value);
    }
}
