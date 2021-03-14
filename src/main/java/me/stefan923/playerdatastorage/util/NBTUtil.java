package me.stefan923.playerdatastorage.util;

import net.minecraft.server.v1_16_R3.NBTTagCompound;
import net.minecraft.server.v1_16_R3.NBTTagList;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_16_R3.potion.CraftPotionEffectType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public final class NBTUtil {

    private NBTUtil() { }

    public static PotionEffect[] nbtTagListToPotionEffectArray(NBTTagList nbtTagList) {
        if (nbtTagList == null) {
            return null;
        }

        int potionEffectCount = nbtTagList.size();
        PotionEffect[] potionEffects = new PotionEffect[potionEffectCount];
        for (int i = 0; i < potionEffectCount; ++i) {
            NBTTagCompound compound = (NBTTagCompound) nbtTagList.get(i);
            if (!compound.isEmpty()) {
                @SuppressWarnings("deprecation")
                PotionEffectType potionEffectType = CraftPotionEffectType.getById(compound.getTypeId());

                if (potionEffectType != null) {
                    PotionEffect potionEffect = new PotionEffect(
                            potionEffectType,
                            compound.getInt("Duration"),
                            compound.getInt("Amplifier"),
                            compound.getBoolean("Ambient"),
                            compound.getBoolean("ShowParticles"),
                            compound.getBoolean("ShowIcon")
                    );
                    potionEffects[i] = potionEffect;
                }
            }
        }

        return potionEffects;
    }

    public static ItemStack[] nbtTagListToItemStackArray(NBTTagList nbtTagList) {
        if (nbtTagList == null) {
            return null;
        }

        int itemStackCount = nbtTagList.size();
        ItemStack[] inventoryContent = new ItemStack[itemStackCount];
        for (int i = 0; i < itemStackCount; ++i) {
            NBTTagCompound compound = (NBTTagCompound) nbtTagList.get(i);
            if (!compound.isEmpty()) {
                ItemStack stack = CraftItemStack.asBukkitCopy(net.minecraft.server.v1_16_R3.ItemStack.a(compound));
                inventoryContent[i] = stack;
            }
        }

        return inventoryContent;
    }

}
