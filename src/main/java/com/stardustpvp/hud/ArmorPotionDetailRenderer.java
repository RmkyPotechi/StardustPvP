package com.stardustpvp.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/** Detailed but lightweight armor and potion information. */
public final class ArmorPotionDetailRenderer {
    private final Minecraft mc = Minecraft.getMinecraft();
    private final HudLayoutManager layout;

    public ArmorPotionDetailRenderer(HudLayoutManager layout) { this.layout = layout; }

    @SubscribeEvent
    public void render(RenderGameOverlayEvent.Post event) {
        if (event.type != RenderGameOverlayEvent.ElementType.ALL) return;
        EntityPlayerSP player = mc.thePlayer;
        if (player == null) return;
        FontRenderer font = mc.fontRendererObj;
        HudElementState armor = layout.get(HudWidgetIds.ARMOR);
        if (armor != null && armor.isEnabled()) renderArmor(font, player, armor);
        HudElementState potions = layout.get(HudWidgetIds.POTIONS);
        if (potions != null && potions.isEnabled()) renderPotions(font, player, potions);
    }

    private void renderArmor(FontRenderer font, EntityPlayerSP player, HudElementState e) {
        int y = Math.round(e.getY());
        for (ItemStack stack : player.inventory.armorInventory) {
            if (stack == null) continue;
            int max = stack.getMaxDamage();
            int used = stack.getItemDamage();
            int remaining = Math.max(0, max - used);
            int percent = max > 0 ? Math.round(100.0F * remaining / max) : 100;
            font.drawStringWithShadow(stack.getDisplayName() + " " + percent + "%", Math.round(e.getX()), y, HudTheme.TEXT);
            y += 10;
        }
    }

    private void renderPotions(FontRenderer font, EntityPlayerSP player, HudElementState e) {
        int y = Math.round(e.getY());
        for (PotionEffect effect : player.getActivePotionEffects()) {
            Potion potion = Potion.potionTypes[effect.getPotionID()];
            if (potion == null) continue;
            int seconds = Math.max(0, effect.getDuration() / 20);
            String name = potion.getName();
            font.drawStringWithShadow(name + " " + formatTime(seconds), Math.round(e.getX()), y, HudTheme.TEXT_MUTED);
            y += 10;
        }
    }

    private String formatTime(int seconds) {
        return (seconds / 60) + ":" + String.format("%02d", seconds % 60);
    }
}
