package com.stardustpvp.core;

/**
 * Client-only projectile rendering policy. It smooths visual interpolation
 * without changing authoritative projectile physics, hit detection, or packets.
 */
public final class ProjectileRenderPolicy {
    private boolean smoothing = true;
    private float maximumInterpolation = 1.0F;

    public float interpolate(float previous, float current, float partialTicks) {
        if (!smoothing) return current;
        float t = Math.max(0.0F, Math.min(maximumInterpolation, partialTicks));
        return previous + (current - previous) * t;
    }

    public boolean isSmoothingEnabled() {
        return smoothing;
    }

    public void setSmoothingEnabled(boolean enabled) {
        smoothing = enabled;
    }
}
