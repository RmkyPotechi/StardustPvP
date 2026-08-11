package com.stardustpvp.hud;

/** Visual snapshot of armor durability; never changes inventory state. */
public final class ArmorStatus {
    public final int helmet, chestplate, leggings, boots;
    public ArmorStatus(int helmet, int chestplate, int leggings, int boots) {
        this.helmet=clamp(helmet); this.chestplate=clamp(chestplate); this.leggings=clamp(leggings); this.boots=clamp(boots);
    }
    private static int clamp(int value) { return Math.max(0, Math.min(100, value)); }
}
