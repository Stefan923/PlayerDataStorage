package me.stefan923.playerdatastorage;

import me.stefan923.playerdatastorage.playerdata.PlayerData;

import java.util.UUID;

public interface PlayerDataStorage {

    /**
     * Creates database table if it doesn't exist.
     */
    void createTable();

    /**
     * Returns player data after loading it from the database.
     *
     * @param uuid - player's uuid
     * @return {@link PlayerData} if given player exists into
     *                            the database. Otherwise it
     *                            returns null.
     */
    PlayerData getPlayerData(UUID uuid);

    /**
     * Saves the player data in the database.
     *
     * @param playerData - player's data to be saved
     */
    void savePlayerData(PlayerData playerData);

}
