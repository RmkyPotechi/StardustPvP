package com.stardustpvp.hud;

/** Applies conservative built-in layouts without touching gameplay state. */
public final class HudPresetLayout {
    private HudPresetLayout() {}

    public static void apply(HudLayoutManager layout, HudPreset preset) {
        if (layout == null || preset == null) return;
        layout.setPreset(preset);
        switch (preset) {
            case MINIMAL:
                move(layout, "fps", 8, 8);
                move(layout, "ping", 8, 30);
                break;
            case PVP:
                move(layout, "fps", 8, 8);
                move(layout, "ping", 8, 30);
                move(layout, "cps", 8, 52);
                move(layout, "keystrokes", 8, 76);
                break;
            case BEDWARS:
                move(layout, "fps", 8, 8);
                move(layout, "ping", 8, 30);
                move(layout, "keystrokes", 8, 54);
                move(layout, "cps", 8, 132);
                break;
            case CUSTOM:
                break;
        }
    }

    private static void move(HudLayoutManager layout, String id, float x, float y) {
        if (layout.get(id) != null) layout.move(id, x, y);
    }
}
