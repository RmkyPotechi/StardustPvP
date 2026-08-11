package com.stardustpvp.performance;

/** Allocation-free limiter for optional client-side particle work. */
public final class ParticleBudget {
    private int spawned;
    private int cap = 160;

    public void beginTick() {
        spawned = 0;
    }

    public boolean allow() {
        if (spawned >= cap) return false;
        spawned++;
        return true;
    }

    public void setCap(int cap) {
        this.cap = Math.max(0, Math.min(2000, cap));
    }

    public int getCap() { return cap; }
}
