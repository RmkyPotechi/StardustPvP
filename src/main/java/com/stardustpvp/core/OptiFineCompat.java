package com.stardustpvp.core;

/**
 * Lightweight OptiFine compatibility guard. It only detects the optional
 * OptiFine runtime and exposes safe capability checks; it never modifies or
 * redistributes OptiFine classes.
 */
public final class OptiFineCompat {
    private static final String OPTIFINE_CONFIG = "Config";
    private final boolean installed;

    public OptiFineCompat() {
        installed = isClassPresent(OPTIFINE_CONFIG);
    }

    private static boolean isClassPresent(String name) {
        try {
            Class.forName(name, false, OptiFineCompat.class.getClassLoader());
            return true;
        } catch (ClassNotFoundException ignored) {
            return false;
        } catch (LinkageError ignored) {
            return false;
        }
    }

    public boolean isInstalled() {
        return installed;
    }

    /**
     * Returns whether Stardust should avoid replacing OptiFine's renderer.
     * Kept deliberately conservative until a specific integration is tested.
     */
    public boolean shouldYieldRenderer() {
        return installed;
    }
}
