package com.stardustpvp.hud;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Central registry for client HUD elements. */
public final class HudRegistry {
    private final List<HudElement> elements = new ArrayList<HudElement>();

    public void register(HudElement element) {
        if (element == null) return;
        for (HudElement existing : elements) {
            if (existing.getId().equals(element.getId())) return;
        }
        elements.add(element);
    }

    public List<HudElement> getElements() {
        return Collections.unmodifiableList(elements);
    }
}
