package com.stardustpvp.core;

/**
 * Keeps expensive client-side chunk maintenance from being requested more
 * often than necessary. It is deliberately conservative and never changes
 * server chunk state or player-visible render distance.
 */
public final class ChunkWorkPolicy {
    private long lastMaintenanceNs;
    private long intervalNs = 50_000_000L;

    public boolean allowMaintenance(long nowNs) {
        if (nowNs - lastMaintenanceNs < intervalNs) return false;
        lastMaintenanceNs = nowNs;
        return true;
    }

    public void setIntervalMs(long milliseconds) {
        intervalNs = Math.max(1L, milliseconds) * 1_000_000L;
    }
}
