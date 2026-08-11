package com.stardustpvp.hud;

/** Visual-only crosshair customization. */
public final class CrosshairSettings {
    private int size = 5;
    private int thickness = 1;
    private boolean dot;
    public int getSize(){return size;}
    public int getThickness(){return thickness;}
    public boolean isDot(){return dot;}
    public void setSize(int value){size=Math.max(1,Math.min(20,value));}
    public void setThickness(int value){thickness=Math.max(1,Math.min(5,value));}
    public void setDot(boolean value){dot=value;}
}
