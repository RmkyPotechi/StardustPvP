package com.stardustpvp.input;

import net.minecraft.client.Minecraft;
import net.minecraft.util.MouseHelper;
import org.lwjgl.input.Mouse;

/**
 * Lightweight mouse-input adapter. It intentionally consumes only the normal
 * LWJGL mouse deltas exposed by Minecraft; it never injects clicks or packets.
 */
public final class RawMouseInput {
    private RawMouseInput() {}

    public static int getDeltaX() {
        return Mouse.getDX();
    }

    public static int getDeltaY() {
        return Mouse.getDY();
    }

    public static boolean isAvailable() {
        return Mouse.isCreated() && Minecraft.getMinecraft().inGameHasFocus;
    }

    public static MouseHelper createHelper() {
        return new MouseHelper();
    }
}
