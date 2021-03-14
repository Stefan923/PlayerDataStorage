package me.stefan923.playerdatastorage.playerdata;

import me.stefan923.playerdatastorage.util.ExperienceUtil;
import me.stefan923.playerdatastorage.util.NBTUtil;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import net.minecraft.server.v1_16_R3.NBTTagList;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public class NBTPlayerData implements IPlayerData {

    public static final String INVENTORY_NBT_TAG = "Inventory";
    public static final String ENDERCHEST_NBT_TAG = "EnderItems";
    public static final String ACTIVE_EFFECTS_NBT_TAG = "ActiveEffects";
    public static final String XP_LEVEL_NBT_TAG = "XpLevel";
    public static final String XP_P_NBT_TAG = "XpP";

    private final NBTTagCompound nbtTagCompound;

    public NBTPlayerData(NBTTagCompound nbtTagCompound) {
        this.nbtTagCompound = nbtTagCompound;
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
    public int getTotalExperience() {
        return ExperienceUtil.getTotalExperience(
                nbtTagCompound.getInt(XP_LEVEL_NBT_TAG),
                nbtTagCompound.getFloat(XP_P_NBT_TAG)
        );
    }

}
