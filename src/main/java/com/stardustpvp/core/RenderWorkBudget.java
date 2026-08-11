package com.stardustpvp.core;

/**
 * Per-frame counters for optional render work. The class itself does not
 * cancel Minecraft rendering; individual cosmetic modules can consult it
 * before submitting non-essential work.
 */
public final class RenderWorkBudget {
    private int particleLimit = 250;
    private int cosmeticLimit = 500;
    private int particles;
    private int cosmetics;
    private long frameId = Long.MIN_VALUE;

    public void beginFrame(long id) {
        if (id == frameId) return;
        frameId = id;
        particles = 0;
        cosmetics = 0;
    }

    public boolean allowParticle() {
        if (particles >= particleLimit) return false;
        particles++;
        return true;
    }

    public boolean allowCosmetic() {
        if (cosmetics >= cosmeticLimit) return false;
        cosmetics++;
        return true;
    }

    public void setParticleLimit(int limit) { particleLimit = Math.max(0, limit); }
    public void setCosmeticLimit(int limit) { cosmeticLimit = Math.max(0, limit); }
}
