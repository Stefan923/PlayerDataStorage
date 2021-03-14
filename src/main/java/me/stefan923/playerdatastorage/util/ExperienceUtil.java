package me.stefan923.playerdatastorage.util;

import org.bukkit.entity.Player;

public final class ExperienceUtil {

    private ExperienceUtil() { }

    public static void setTotalExperience(final Player player, final int exp) {
        if (exp < 0) {
            throw new IllegalArgumentException("Experience is negative!");
        }
        player.setExp(0);
        player.setLevel(0);
        player.setTotalExperience(0);

        int amount = exp;
        while (amount > 0) {
            final int expToLevel = getExpAtLevel(player);
            amount -= expToLevel;
            if (amount >= 0) {
                player.giveExp(expToLevel);
            } else {
                amount += expToLevel;
                player.giveExp(amount);
                amount = 0;
            }
        }
    }

    private static int getExpAtLevel(final Player player) {
        return getExpAtLevel(player.getLevel());
    }

    public static int getExpAtLevel(final int level) {
        if (level <= 15) {
            return (2 * level) + 7;
        }
        if (level <= 30) {
            return (5 * level) - 38;
        }
        return (9 * level) - 158;

    }

    public static int getTotalExperience(final int experienceLevels, final float experiencePercentage) {
        int currentLevel = experienceLevels;
        int totalExperience = Math.round(getExpAtLevel(experienceLevels) * experiencePercentage);

        while (currentLevel > 0) {
            currentLevel--;
            totalExperience += getExpAtLevel(currentLevel);
        }
        if (totalExperience < 0) {
            totalExperience = Integer.MAX_VALUE;
        }

        return totalExperience;
    }
}
