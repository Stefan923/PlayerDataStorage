package me.stefan923.playerdatastorage.mysql;

public final class SQLStatement {

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS `{prefix}playerdata` (`uuid` VARCHAR(36), `playerData` BLOB, PRIMARY KEY(`uuid`));";
    public static final String SAVE_PLAYER_DATA = "INSERT INTO `{prefix}playerdata` (`uuid`, `playerData`) VALUES(?, ?) ON DUPLICATE KEY UPDATE `playerData` = ?;";
    public static final String GET_PLAYER_DATA = "SELECT `playerData` FROM `{prefix}playerdata` WHERE `uuid` LIKE ?;";

    private SQLStatement() { }

}
