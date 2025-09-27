package de.deinplugin.hammer;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class HammerPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Hammer in allen Varianten registrieren (IDs passen zu deinem ResourcePack)
        registerHammer("wooden_hammer", Material.WOODEN_AXE, Material.OAK_PLANKS, "Holzhammer", 1001);
        registerHammer("stone_hammer", Material.STONE_AXE, Material.COBBLESTONE, "Steinhammer", 1002);
        registerHammer("iron_hammer", Material.IRON_AXE, Material.IRON_INGOT, "Eisenhammer", 1003);
        registerHammer("golden_hammer", Material.GOLDEN_AXE, Material.GOLD_INGOT, "Goldhammer", 1004);
        registerHammer("diamond_hammer", Material.DIAMOND_AXE, Material.DIAMOND, "Diamanthammer", 1005);
        registerHammer("netherite_hammer", Material.NETHERITE_AXE, Material.NETHERITE_INGOT, "Netherithammer", 1006);
    }

    private void registerHammer(String keyName, Material baseItem, Material recipeMat, String displayName, int customModelData) {
        ItemStack hammer = new ItemStack(baseItem);
        ItemMeta meta = hammer.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(displayName);

            // Setze die CustomModelData ID → sagt Minecraft, welche Textur aus dem ResourcePack benutzt wird
            meta.setCustomModelData(customModelData);

            hammer.setItemMeta(meta);
        }

        // Rezept definieren
        NamespacedKey key = new NamespacedKey(this, keyName);
        ShapedRecipe recipe = new ShapedRecipe(key, hammer);
        recipe.shape("MMM", "MSM", " S ");
        recipe.setIngredient('M', recipeMat);
        recipe.setIngredient('S', Material.STICK);

        Bukkit.addRecipe(recipe);
    }
}
