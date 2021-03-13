package me.stefan923.playerdatastorage;

import org.bukkit.Effect;
import org.bukkit.inventory.ItemStack;

import java.io.Serializable;
import java.util.UUID;

public class PlayerData implements Serializable {

    private final ItemStack[] inventoryContent;
    private final ItemStack[] enderChestContent;
    private final Effect[] effects;
    private final float experience;

    private transient UUID uuid;

    public PlayerData(ItemStack[] inventoryContent, ItemStack[] enderChestContent, Effect[] effects, float experience) {
        this.inventoryContent = inventoryContent;
        this.enderChestContent = enderChestContent;
        this.effects = effects;
        this.experience = experience;
    }

    public PlayerData(UUID uuid, ItemStack[] inventoryContent, ItemStack[] enderChestContent, Effect[] effects, float experience) {
        this(inventoryContent, enderChestContent, effects, experience);
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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
