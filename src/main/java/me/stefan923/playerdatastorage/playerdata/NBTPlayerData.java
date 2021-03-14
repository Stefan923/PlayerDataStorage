package me.stefan923.playerdatastorage.playerdata;

import me.stefan923.playerdatastorage.util.NBTUtil;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import net.minecraft.server.v1_16_R3.NBTTagList;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.UUID;

public class NBTPlayerData implements IPlayerData {

    public static final String INVENTORY_NBT_TAG = "Inventory";
    public static final String ENDERCHEST_NBT_TAG = "EnderItems";
    public static final String ACTIVE_EFFECTS_NBT_TAG = "ActiveEffects";
    public static final String EXPERIENCE_NBT_TAG = "XpP";
    private final UUID uuid;

    private final NBTTagCompound nbtTagCompound;

    public NBTPlayerData(UUID uuid, NBTTagCompound nbtTagCompound) {
        this.uuid = uuid;
        this.nbtTagCompound = nbtTagCompound;
    }

    @Override
    public UUID getUuid() {
        return uuid;
    }

    @Override
    public ItemStack[] getInventoryContent() {
        return NBTUtil.nbtTagListToItemStackArray((NBTTagList) nbtTagCompound.get(INVENTORY_NBT_TAG));
    }

    @Override
    public ItemStack[] getEnderChestContent() {
        return NBTUtil.nbtTagListToItemStackArray((NBTTagList) nbtTagCompound.get(ENDERCHEST_NBT_TAG));
    }

    @Override
    public PotionEffect[] getPotionEffects() {
        return NBTUtil.nbtTagListToPotionEffectArray((NBTTagList) nbtTagCompound.get(ACTIVE_EFFECTS_NBT_TAG));
    }

    @Override
    public float getExperience() {
        return nbtTagCompound.getFloat(EXPERIENCE_NBT_TAG);
    }

}
