package com.stardustpvp.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

/** Records real mouse-button input only; it never synthesizes input. */
public final class CpsInputHandler {
    private final CpsTracker tracker;

    public CpsInputHandler(CpsTracker tracker) { this.tracker = tracker; }

    @SubscribeEvent
    public void onMouseInput(InputEvent.MouseInputEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.currentScreen != null || mc.thePlayer == null) return;
        KeyBinding left = mc.gameSettings.keyBindAttack;
        KeyBinding right = mc.gameSettings.keyBindUseItem;
        long now = System.currentTimeMillis();
        if (left.isPressed()) tracker.recordLeftClick(now);
        if (right.isPressed()) tracker.recordRightClick(now);
    }
}
