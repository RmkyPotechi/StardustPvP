package com.stardustpvp.hud;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Stores editable HUD layout state and named presets. */
public final class HudLayoutManager {
    private final Map<String, HudElementState> elements = new LinkedHashMap<String, HudElementState>();
    private HudPreset preset = HudPreset.PVP;

    public void add(HudElementState element) {
        if (element != null) elements.put(element.getId(), element);
    }

    public HudElementState get(String id) { return elements.get(id); }

    public List<HudElement> getElements() {
        return Collections.unmodifiableList(new ArrayList<HudElement>(elements.values()));
    }

    public void move(String id, float x, float y) {
        HudElementState element = elements.get(id);
        if (element != null) element.setPosition(x, y);
    }

    public void scale(String id, float scale) {
        HudElementState element = elements.get(id);
        if (element != null) element.setScale(scale);
    }

    public void toggle(String id) {
        HudElementState element = elements.get(id);
        if (element != null) element.setEnabled(!element.isEnabled());
    }

    public HudPreset getPreset() { return preset; }
    public void setPreset(HudPreset preset) { this.preset = preset == null ? HudPreset.CUSTOM : preset; }
}
