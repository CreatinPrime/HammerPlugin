package de.deinplugin.hammer;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class HammerPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Hammer in allen Varianten registrieren
        registerHammer("wooden_hammer", Material.WOODEN_AXE, Material.OAK_PLANKS, "Holzhammer", 5, -3.2);
        registerHammer("stone_hammer", Material.STONE_AXE, Material.COBBLESTONE, "Steinhammer", 6, -3.2);
        registerHammer("iron_hammer", Material.IRON_AXE, Material.IRON_INGOT, "Eisenhammer", 7, -3.3);
        registerHammer("golden_hammer", Material.GOLDEN_AXE, Material.GOLD_INGOT, "Goldhammer", 5, -3.0);
        registerHammer("diamond_hammer", Material.DIAMOND_AXE, Material.DIAMOND, "Diamanthammer", 8, -3.3);
        registerHammer("netherite_hammer", Material.NETHERITE_AXE, Material.NETHERITE_INGOT, "Netherithammer", 9, -3.4);
    }

    private void registerHammer(String keyName, Material baseItem, Material recipeMat, String displayName, double attackDamage, double attackSpeed) {
        ItemStack hammer = new ItemStack(baseItem);
        ItemMeta meta = hammer.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(displayName);
            // Attribute überschreiben
            meta.addAttributeModifier(org.bukkit.attribute.Attribute.GENERIC_ATTACK_DAMAGE,
    new AttributeModifier(UUID.randomUUID(), "generic.attack_damage", 10.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));

meta.addAttributeModifier(org.bukkit.attribute.Attribute.GENERIC_ATTACK_SPEED,
    new AttributeModifier(UUID.randomUUID(), "generic.attack_speed", -2.8, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));


        // Rezept definieren
        NamespacedKey key = new NamespacedKey(this, keyName);
        ShapedRecipe recipe = new ShapedRecipe(key, hammer);
        recipe.shape("MMM", "MSM", " S ");
        recipe.setIngredient('M', recipeMat);
        recipe.setIngredient('S', Material.STICK);

        Bukkit.addRecipe(recipe);
    }
}
