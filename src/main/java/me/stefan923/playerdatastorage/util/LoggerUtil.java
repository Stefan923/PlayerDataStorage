package me.stefan923.playerdatastorage.util;

import org.bukkit.Bukkit;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerUtil {

    private static final Logger logger = Bukkit.getLogger();

    private LoggerUtil() { }

    public static void sendInfo(String message) {
        logger.log(Level.INFO, message);
    }

    public static void sendSevere(String message) {
        logger.log(Level.SEVERE, message);
    }

}
