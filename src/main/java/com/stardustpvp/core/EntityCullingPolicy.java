package com.stardustpvp.core;

/**
 * Conservative entity-culling policy. This layer makes decisions only from
 * information already available to the client; it never changes server state,
 * reach, hit detection, or packets.
 */
public final class EntityCullingPolicy {
    private boolean enabled = true;
    private double maxDistanceSq = 256.0 * 256.0;

    public boolean shouldConsider(double distanceSq) {
        return !enabled || distanceSq <= maxDistanceSq;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public double getMaxDistanceSq() {
        return maxDistanceSq;
    }

    public void setMaxDistanceBlocks(double blocks) {
        if (blocks < 1.0) blocks = 1.0;
        maxDistanceSq = blocks * blocks;
    }
}
