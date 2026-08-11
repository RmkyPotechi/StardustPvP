package com.stardustpvp.core;

/**
 * Schedules expensive diagnostics away from the render hot path.
 * One maintenance slice is allowed every 250 ms.
 */
public final class PerformanceScheduler {
    private static final long MAINTENANCE_INTERVAL_NS = 250_000_000L;
    private long nextMaintenanceNs;

    public boolean shouldRunMaintenance(long nowNs) {
        if (nowNs < nextMaintenanceNs) return false;
        nextMaintenanceNs = nowNs + MAINTENANCE_INTERVAL_NS;
        return true;
    }
}
