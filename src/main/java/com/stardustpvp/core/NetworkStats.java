package com.stardustpvp.core;

import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.network.NetHandlerPlayClient;

/** Read-only latency diagnostics. It never rewrites packets or connection timing. */
public final class NetworkStats {
    private NetworkStats() {}

    public static int getPing() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.thePlayer == null) return -1;
        NetHandlerPlayClient handler = mc.getNetHandler();
        if (handler == null) return -1;
        NetworkPlayerInfo info = handler.getPlayerInfo(mc.thePlayer.getUniqueID());
        return info == null ? -1 : info.getResponseTime();
    }
}
