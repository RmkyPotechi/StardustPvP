package com.stardustpvp.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.potion.PotionEffect;
import net.minecraft.item.ItemStack;

/** Collects read-only client state for HUD rendering. */
public final class HudWidgetCollector {
    private final Minecraft mc = Minecraft.getMinecraft();
    private final CpsTracker cpsTracker;

    public HudWidgetCollector(CpsTracker cpsTracker) {
        this.cpsTracker = cpsTracker;
    }

    public HudWidgetState collect(long nowMs) {
        HudWidgetState state = new HudWidgetState();
        state.fps = Minecraft.getDebugFPS();
        state.cps = cpsTracker.getClicksLastSecond(nowMs);
        EntityPlayerSP player = mc.thePlayer;
        if (player == null) return state;

        state.x = player.posX;
        state.y = player.posY;
        state.z = player.posZ;
        int potions = 0;
        for (PotionEffect ignored : player.getActivePotionEffects()) potions++;
        state.activePotionCount = potions;

        int armor = 0;
        for (ItemStack stack : player.inventory.armorInventory) if (stack != null) armor++;
        state.armorCount = armor;

        if (mc.getNetHandler() != null) {
            NetworkPlayerInfo info = mc.getNetHandler().getPlayerInfo(player.getUniqueID());
            if (info != null) state.ping = info.getResponseTime();
        }
        return state;
    }
}
