package com.stardustpvp.core;

/**
 * Visual-only interpolation helper. Intended for rendering projectile motion
 * smoothly between client updates without predicting or changing collision.
 */
public final class ProjectileSmoothing {
    private double previousX;
    private double previousY;
    private double previousZ;
    private double currentX;
    private double currentY;
    private double currentZ;

    public void update(double x, double y, double z) {
        previousX = currentX;
        previousY = currentY;
        previousZ = currentZ;
        currentX = x;
        currentY = y;
        currentZ = z;
    }

    public double x(float partialTicks) {
        return previousX + (currentX - previousX) * partialTicks;
    }

    public double y(float partialTicks) {
        return previousY + (currentY - previousY) * partialTicks;
    }

    public double z(float partialTicks) {
        return previousZ + (currentZ - previousZ) * partialTicks;
    }
}
