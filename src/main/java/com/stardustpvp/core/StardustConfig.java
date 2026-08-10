package com.stardustpvp.core;

import java.io.File;
import net.minecraftforge.common.config.Configuration;

/** Central client-only settings. No gameplay automation is exposed here. */
public final class StardustConfig {
    private StardustConfig() {}

    public static Configuration CONFIG;
    public static boolean rawMouseInput = true;
    public static boolean minimumViewBobbing = true;
    public static boolean performanceHud = false;
    public static boolean compactParticles = true;

    public static void load(File configDir) {
        CONFIG = new Configuration(new File(configDir, "stardustpvp.cfg"));
        try {
            CONFIG.load();
            rawMouseInput = CONFIG.getBoolean("rawMouseInput", "input", true,
                    "Use the low-level LWJGL mouse delta path for diagnostics/input consistency.");
            minimumViewBobbing = CONFIG.getBoolean("minimumViewBobbing", "visual", true,
                    "Minimize view bobbing while preserving normal player movement.");
            performanceHud = CONFIG.getBoolean("performanceHud", "hud", false,
                    "Show FPS/frame-time diagnostics.");
            compactParticles = CONFIG.getBoolean("compactParticles", "performance", true,
                    "Prefer reduced particle work where Forge exposes a safe client-side hook.");
        } finally {
            if (CONFIG.hasChanged()) CONFIG.save();
        }
    }
}
