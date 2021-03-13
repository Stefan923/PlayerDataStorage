package me.stefan923.playerdatastorage.mysql;

public final class SQLStatement {

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS `{prefix}playerdata` (`uuid` VARCHAR(36), `inventory` BLOB, `enderchest` BLOB, `effects` BLOB, `experience` FLOAT, PRIMARY KEY (`uuid`))";
    private static final String SAVE_PLAYER_DATA = "INSERT INTO `{prefix}playerdata` (`uuid`, `inventory`, `enderchest`, `effects`, `experience`) VALUES(?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE `inventory` = ?, `enderchest` = ?, `effects` = ?, `experience` = ?;";
    private static final String GET_PLAYER_DATA = "SELECT `inventory`, `enderchest`, `effects`, `experience` FROM `{prefix}playerdata` WHERE `uuid` LIKE ?;";

    private SQLStatement() { }

}
