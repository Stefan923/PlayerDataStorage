package me.stefan923.playerdatastorage.migration;

import me.stefan923.playerdatastorage.mysql.MySQLPlayerDataStorage;
import me.stefan923.playerdatastorage.playerdata.NBTPlayerData;
import me.stefan923.playerdatastorage.playerdata.PlayerData;
import me.stefan923.playerdatastorage.util.LoggerUtil;
import net.minecraft.server.v1_16_R3.NBTCompressedStreamTools;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.UUID;

public class MySQLMigration {

    private final MySQLPlayerDataStorage dataStorage;

    public MySQLMigration(MySQLPlayerDataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    public void migrateAllFilesToMySQL(String directory) {
        File directoryFile = new File(directory);
        for (String fileName : Objects.requireNonNull(directoryFile.list())) {
            migrateFileToMySQL(new File(directoryFile + File.separator + fileName));
        }
    }

    public void migrateFileToMySQL(File playerDataFile) {
        String fileName = FilenameUtils.removeExtension(playerDataFile.getName());
        try (InputStream inputStream = new FileInputStream(playerDataFile)) {
            NBTPlayerData nbtPlayerData = new NBTPlayerData(NBTCompressedStreamTools.a(inputStream));
            dataStorage.savePlayerData(new PlayerData(
                    UUID.fromString(fileName),
                    nbtPlayerData.getInventoryContent(),
                    nbtPlayerData.getEnderChestContent(),
                    nbtPlayerData.getPotionEffects(),
                    nbtPlayerData.getExperience()
            ));
            LoggerUtil.sendInfo("Successfully migrated '" + fileName + "' to the MySQL database.");
        } catch (IOException e) {
            LoggerUtil.sendSevere("Couldn't migrate '" + fileName + "' to the MySQL database.");
            e.printStackTrace();
        }
    }

}
