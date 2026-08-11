package com.stardustpvp.hud;

/** Client-side coordinate/facing snapshot for the Coordinates HUD. */
public final class CoordinatesData {
    public final double x, y, z;
    public final String facing;
    public CoordinatesData(double x, double y, double z, String facing) {
        this.x=x; this.y=y; this.z=z; this.facing=facing == null ? "" : facing;
    }
}
