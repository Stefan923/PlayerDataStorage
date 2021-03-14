package me.stefan923.playerdatastorage;

import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.io.Serializable;
import java.util.UUID;

public class PlayerData implements Serializable {

    private final ItemStack[] inventoryContent;
    private final ItemStack[] enderChestContent;
    private final PotionEffect[] potionEffects;
    private final float experience;

    private transient UUID uuid;

    public PlayerData(ItemStack[] inventoryContent, ItemStack[] enderChestContent, PotionEffect[] potionEffects, float experience) {
        this.inventoryContent = inventoryContent;
        this.enderChestContent = enderChestContent;
        this.potionEffects = potionEffects;
        this.experience = experience;
    }

    public PlayerData(UUID uuid, ItemStack[] inventoryContent, ItemStack[] enderChestContent, PotionEffect[] potionEffects, float experience) {
        this(inventoryContent, enderChestContent, potionEffects, experience);
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

    public PotionEffect[] getPotionEffects() {
        return potionEffects;
    }

    public float getExperience() {
        return experience;
    }

}
