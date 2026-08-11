package com.stardustpvp.hud;

/** Visual-only crosshair customization. */
public final class CrosshairSettings {
    private int size = 5;
    private int gap = 2;
    private int thickness = 1;
    private boolean dot;
    private int color = 0xFFFFFFFF;

    public int getSize() { return size; }
    public int getGap() { return gap; }
    public int getThickness() { return thickness; }
    public boolean isDot() { return dot; }
    public int getColor() { return color; }
    public void setSize(int value) { size = Math.max(1, Math.min(20, value)); }
    public void setGap(int value) { gap = Math.max(0, Math.min(20, value)); }
    public void setThickness(int value) { thickness = Math.max(1, Math.min(5, value)); }
    public void setDot(boolean value) { dot = value; }
    public void setColor(int value) { color = value | 0xFF000000; }
}
