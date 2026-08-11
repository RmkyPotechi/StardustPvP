package com.stardustpvp.core;

/**
 * Shared limiter for purely cosmetic work. Gameplay-critical rendering and
 * input must never use this class as a reason to skip authoritative logic.
 */
public final class CosmeticWorkLimiter {
    private final AdaptivePerformance adaptivePerformance;

    public CosmeticWorkLimiter(AdaptivePerformance adaptivePerformance) {
        this.adaptivePerformance = adaptivePerformance;
    }

    public boolean allow(int requiredQualityLevel) {
        return adaptivePerformance.allowCosmeticWork(requiredQualityLevel);
    }
}
