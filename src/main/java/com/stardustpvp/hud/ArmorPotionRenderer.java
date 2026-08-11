package com.stardustpvp.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/** Lightweight armor and potion status widget. */
public final class ArmorPotionRenderer {
    private final Minecraft mc = Minecraft.getMinecraft();
    private final HudLayoutManager layout;

    public ArmorPotionRenderer(HudLayoutManager layout) { this.layout = layout; }

    @SubscribeEvent
    public void render(RenderGameOverlayEvent.Post event) {
        if (event.type != RenderGameOverlayEvent.ElementType.ALL) return;
        EntityPlayerSP player = mc.thePlayer;
        if (player == null) return;
        FontRenderer f = mc.fontRendererObj;
        HudElementState armor = layout.get(HudWidgetIds.ARMOR);
        if (armor != null && armor.isEnabled()) {
            int count = 0;
            for (ItemStack stack : player.inventory.armorInventory) if (stack != null) count++;
            f.drawStringWithShadow("Armor: " + count, Math.round(armor.getX()), Math.round(armor.getY()), 0xFFFFFF);
        }
        HudElementState potions = layout.get(HudWidgetIds.POTIONS);
        if (potions != null && potions.isEnabled()) {
            int count = 0;
            for (PotionEffect ignored : player.getActivePotionEffects()) count++;
            f.drawStringWithShadow("Potions: " + count, Math.round(potions.getX()), Math.round(potions.getY()), 0xFFFFFF);
        }
    }
}
