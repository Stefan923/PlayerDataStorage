package me.stefan923.playerdatastorage;

import java.util.UUID;

public interface PlayerDataStorage {

    void createTable();

    PlayerData getPlayerData(UUID uuid);

    void savePlayerData(PlayerData playerData);

}
