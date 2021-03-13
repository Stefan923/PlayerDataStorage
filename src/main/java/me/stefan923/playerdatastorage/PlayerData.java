package me.stefan923.playerdatastorage;

import org.bukkit.Effect;
import org.bukkit.inventory.ItemStack;

public class PlayerData {

    private final ItemStack[] inventoryContent;
    private final ItemStack[] enderChestContent;
    private final Effect[] effects;
    private final float experience;

    public PlayerData(ItemStack[] inventoryContent, ItemStack[] enderChestContent, Effect[] effects, float experience) {
        this.inventoryContent = inventoryContent;
        this.enderChestContent = enderChestContent;
        this.effects = effects;
        this.experience = experience;
    }

    public ItemStack[] getInventoryContent() {
        return inventoryContent;
    }

    public ItemStack[] getEnderChestContent() {
        return enderChestContent;
    }

    public Effect[] getEffects() {
        return effects;
    }

    public float getExperience() {
        return experience;
    }

}
