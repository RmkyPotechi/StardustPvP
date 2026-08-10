package com.stardustpvp.config;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

/** Central client-only settings. No gameplay automation or packet manipulation lives here. */
public final class StardustConfig {
    private StardustConfig() {}

    public static boolean rawMouseInput = true;
    public static boolean minimumViewBobbing = true;
    public static boolean performanceHud = false;

    public static void load(File configDir) {
        File file = new File(configDir, "stardustpvp.cfg");
        Configuration cfg = new Configuration(file);
        cfg.load();

        rawMouseInput = cfg.getBoolean("rawMouseInput", "input", true,
                "Use Minecraft/LWJGL low-level mouse deltas for consistent camera input.");
        minimumViewBobbing = cfg.getBoolean("minimumViewBobbing", "input", true,
                "Reduce camera bobbing while preserving normal player movement.");
        performanceHud = cfg.getBoolean("performanceHud", "performance", false,
                "Show lightweight FPS/frame-time diagnostics.");

        if (cfg.hasChanged()) cfg.save();
    }
}
