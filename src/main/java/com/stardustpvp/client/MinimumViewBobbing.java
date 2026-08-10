package com.stardustpvp.client;

import com.stardustpvp.config.StardustConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.client.event.RenderTickEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/** Reduces camera bob amplitude without changing movement physics. */
public final class MinimumViewBobbing {
    private static final float BOB_SCALE = 0.15F;
    private float savedYaw;
    private float savedPitch;
    private boolean changed;

    @SubscribeEvent
    public void onRenderTick(RenderTickEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.thePlayer == null || !StardustConfig.minimumViewBobbing) return;

        EntityPlayerSP player = mc.thePlayer;
        if (event.phase == RenderTickEvent.Phase.START) {
            savedYaw = player.cameraYaw;
            savedPitch = player.cameraPitch;
            player.cameraYaw *= BOB_SCALE;
            player.cameraPitch *= BOB_SCALE;
            changed = true;
        } else if (event.phase == RenderTickEvent.Phase.END && changed) {
            player.cameraYaw = savedYaw;
            player.cameraPitch = savedPitch;
            changed = false;
        }
    }
}
