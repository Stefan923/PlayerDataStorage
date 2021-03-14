package me.stefan923.playerdatastorage.playerdata;

import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.io.Serializable;
import java.util.UUID;

public interface IPlayerData extends Serializable {

    UUID getUuid();

    ItemStack[] getInventoryContent();

    ItemStack[] getEnderChestContent();

    PotionEffect[] getPotionEffects();

    float getExperience();

}
