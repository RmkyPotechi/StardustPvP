package com.stardustpvp.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

/** Opens the HUD editor from a client-side keybind. */
public final class HudEditorController {
    private final Minecraft minecraft;
    private final HudLayoutManager layout;

    public HudEditorController(Minecraft minecraft, HudLayoutManager layout) {
        this.minecraft = minecraft;
        this.layout = layout;
        ClientRegistry.registerKeyBinding(HudKeybind.OPEN_EDITOR);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        KeyBinding key = HudKeybind.OPEN_EDITOR;
        if (key.isPressed() && minecraft.currentScreen == null) {
            minecraft.displayGuiScreen(new HudEditorScreen(layout));
        }
    }
}
