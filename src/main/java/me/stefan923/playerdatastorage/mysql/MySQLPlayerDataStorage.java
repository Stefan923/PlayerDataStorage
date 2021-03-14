package me.stefan923.playerdatastorage.mysql;

import me.stefan923.playerdatastorage.PlayerDataStorage;
import me.stefan923.playerdatastorage.playerdata.PlayerData;
import me.stefan923.playerdatastorage.util.SerializationUtil;

import java.io.IOException;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class MySQLPlayerDataStorage implements PlayerDataStorage {

    private final MySQLConnection mySQLConnection;

    public MySQLPlayerDataStorage(MySQLConnection mySQLConnection) {
        this.mySQLConnection = mySQLConnection;
    }

    @Override
    public void createTable() {
        try (PreparedStatement preparedStatement = mySQLConnection.getConnection()
                .prepareStatement(SQLStatement.CREATE_TABLE.replace("{prefix}", mySQLConnection.getTablePrefix()))) {

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PlayerData getPlayerData(UUID uuid) {
        try (PreparedStatement preparedStatement = mySQLConnection.getConnection()
                .prepareStatement(SQLStatement.GET_PLAYER_DATA.replace("{prefix}", mySQLConnection.getTablePrefix()))) {

            preparedStatement.setString(1, String.valueOf(uuid));
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                PlayerData playerData = blobToPlayerData(resultSet.getBlob(1));
                if (playerData != null) {
                    playerData.setUuid(uuid);
                }
                return playerData;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void savePlayerData(PlayerData playerData) {
        try (PreparedStatement preparedStatement = mySQLConnection.getConnection()
                    .prepareStatement(SQLStatement.SAVE_PLAYER_DATA.replace("{prefix}", mySQLConnection.getTablePrefix()))) {

            Blob playerDataBlob = playerDataToBlob(playerData);
            preparedStatement.setString(1, String.valueOf(playerData.getUuid()));
            preparedStatement.setBlob(2, playerDataBlob);
            preparedStatement.setBlob(3, playerDataBlob);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Blob playerDataToBlob(PlayerData playerData) throws SQLException {
        Blob blob = mySQLConnection.getConnection().createBlob();
        blob.setBytes(1, SerializationUtil.playerDataToByteArray(playerData));

        return blob;
    }

    private PlayerData blobToPlayerData(Blob blob) throws SQLException {
        try {
            return SerializationUtil.byteArrayToPlayerData(blob.getBinaryStream());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            blob.free();
        }

        return null;
    }

}
