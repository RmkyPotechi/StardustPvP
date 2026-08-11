package com.stardustpvp.hud;

import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

/** Client-only keybind for opening the HUD editor. */
public final class HudKeybind {
    public static final KeyBinding OPEN_EDITOR = new KeyBinding(
            "key.stardust.hud_editor", Keyboard.KEY_RSHIFT, "key.categories.stardust");

    private HudKeybind() {}
}
