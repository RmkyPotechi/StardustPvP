package com.stardustpvp.hud;

import net.minecraft.client.Minecraft;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/** Small, dependency-free persistent HUD configuration for 1.8.9. */
public final class HudConfigStore {
    private final File file;

    public HudConfigStore() {
        file = new File(Minecraft.getMinecraft().mcDataDir, "config/stardust-hud.properties");
    }

    public void save(HudLayoutManager layout) throws IOException {
        File parent = file.getParentFile();
        if (!parent.exists() && !parent.mkdirs()) throw new IOException("Could not create HUD config directory");
        Properties p = new Properties();
        p.setProperty("preset", layout.getPreset().name());
        for (HudElement element : layout.getElements()) {
            String key = "element." + element.getId();
            p.setProperty(key + ".x", Float.toString(element.getX()));
            p.setProperty(key + ".y", Float.toString(element.getY()));
            p.setProperty(key + ".scale", Float.toString(element.getScale()));
            p.setProperty(key + ".enabled", Boolean.toString(element.isEnabled()));
        }
        FileOutputStream out = new FileOutputStream(file);
        try { p.store(out, "StardustPvP HUD settings"); } finally { out.close(); }
    }

    public void load(HudLayoutManager layout) throws IOException {
        if (!file.isFile()) return;
        Properties p = new Properties();
        FileInputStream in = new FileInputStream(file);
        try { p.load(in); } finally { in.close(); }
        try { layout.setPreset(HudPreset.valueOf(p.getProperty("preset", "PVP"))); }
        catch (IllegalArgumentException ignored) { layout.setPreset(HudPreset.PVP); }

        for (HudElement element : layout.getElements()) {
            String key = "element." + element.getId();
            layout.move(element.getId(), parseFloat(p.getProperty(key + ".x"), element.getX()),
                    parseFloat(p.getProperty(key + ".y"), element.getY()));
            element.setScale(parseFloat(p.getProperty(key + ".scale"), element.getScale()));
            element.setEnabled(Boolean.parseBoolean(p.getProperty(key + ".enabled", Boolean.toString(element.isEnabled()))));
        }
    }

    private float parseFloat(String value, float fallback) {
        if (value == null) return fallback;
        try { return Float.parseFloat(value); }
        catch (NumberFormatException ignored) { return fallback; }
    }
}
