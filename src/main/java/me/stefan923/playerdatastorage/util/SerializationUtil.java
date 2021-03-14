package me.stefan923.playerdatastorage.util;

import me.stefan923.playerdatastorage.playerdata.PlayerData;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public final class SerializationUtil {

    private SerializationUtil() { }

    public static byte[] playerDataToByteArray(PlayerData playerData) throws IllegalStateException {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream)) {

            dataOutput.writeObject(playerData);
            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new IllegalStateException("Unable to serialize player data.", e);
        }
    }

    public static PlayerData byteArrayToPlayerData(InputStream inputStream) throws IOException {
        try (BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream)) {
            return (PlayerData) dataInput.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException("Unable to deserialize player data.", e);
        } finally {
            inputStream.close();
        }
    }

}
