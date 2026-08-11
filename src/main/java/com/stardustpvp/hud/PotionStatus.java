package com.stardustpvp.hud;

/** Lightweight immutable potion-effect display entry. */
public final class PotionStatus {
    public final String name;
    public final int amplifier;
    public final int durationTicks;
    public PotionStatus(String name, int amplifier, int durationTicks) {
        this.name = name == null ? "" : name;
        this.amplifier = Math.max(0, amplifier);
        this.durationTicks = Math.max(0, durationTicks);
    }
}
