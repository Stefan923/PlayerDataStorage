package me.stefan923.playerdatastorage.playerdata;

import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.UUID;

public class PlayerData implements IPlayerData {

    private final ItemStack[] inventoryContent;
    private final ItemStack[] enderChestContent;
    private final PotionEffect[] potionEffects;
    private final int totalExperience;

    private transient UUID uuid;

    public PlayerData(ItemStack[] inventoryContent, ItemStack[] enderChestContent, PotionEffect[] potionEffects, int totalExperience) {
        this.inventoryContent = inventoryContent;
        this.enderChestContent = enderChestContent;
        this.potionEffects = potionEffects;
        this.totalExperience = totalExperience;
    }

    public PlayerData(UUID uuid, ItemStack[] inventoryContent, ItemStack[] enderChestContent, PotionEffect[] potionEffects, int totalExperience) {
        this(inventoryContent, enderChestContent, potionEffects, totalExperience);
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public ItemStack[] getInventoryContent() {
        return inventoryContent;
    }

    @Override
    public ItemStack[] getEnderChestContent() {
        return enderChestContent;
    }

    @Override
    public PotionEffect[] getPotionEffects() {
        return potionEffects != null ? potionEffects : new PotionEffect[0];
    }

    @Override
    public int getTotalExperience() {
        return totalExperience;
    }

}
