# PlayerDataStorage
A highly lightweight and effective player data storage library for Bukkit plugins. The primary goal of PlayerDataStorage is to enable storage of player data, including player inventory content, enderchest content, active potion effects etc.
## Using PlayerDataStorage
PlayerDataStorage is integrated into Bukkit plugins through the use of Maven.</br></br>
**Requirements:**
* [Java 8](https://www.oracle.com/java/technical-resources/)
* [Maven 3](http://maven.apache.org/download.cgi)
* [GIT](https://git-scm.com/downloads)

Then use the following command to install canvas to your local maven repository:</br>
```shell
git clone https://github.com/Stefan923/PlayerDataStorage.git
cd PlayerDataStorage/
mvn clean install
```

You will now be able to add canvas as a dependency in your pom.xml files with the following:

```xml
<dependency>
    <groupId>me.stefan923</groupId>
    <artifactId>PlayerDataStorage</artifactId>
    <version>1.0-SNAPSHOT</version>
    <scope>compile</scope>
</dependency>
```

**Note:** You will need to use the Maven shade plugin in order to package your final .jar file. Add the following to your maven plugins section:
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-shade-plugin</artifactId>
    <version>3.2.4</version>
    <executions>
        <execution>
            <phase>package</phase>
            <goals>
                <goal>shade</goal>
            </goals>
        </execution>
    </executions>
</plugin> 
```

Once the dependency is registered, the only thing left to do is to instantiate a MySQLPlayerDataStorage object and create the table.
```java
MySQLConnection connection = new MySQLConnection("tablePrefix_", "127.0.0.1", 3306, "database", "user", "password");
PlayerDataStorage storage = new MySQLPlayerDataStorage(connection);
storage.createTable();
```

## API

This is an example code in which the player's data is loaded from database when he joins the server.

```java
private final PlayerDataStorage storage;

@EventHandler
public void onJoin(PlayerJoinEvent event) {
    Player player = event.getPlayer();
    PlayerData playerData = storage.getPlayerData(player.getUniqueId());
    
    if (playerData != null) {
        player.getInventory().setContents(playerData.getInventoryContent());
        player.getEnderChest().setContents(playerData.getEnderChestContent());
        player.addPotionEffects(Arrays.asList(playerData.getPotionEffects()));
        ExperienceUtil.setTotalExperience(player, playerData.getTotalExperience());
    }
}
```

This is an example code in which the player's data is saved to database when he quits the server.

```java
private final PlayerDataStorage storage;

@EventHandler
public void onQuit(PlayerQuitEvent event) {
    Player player = event.getPlayer();
    Collection<PotionEffect> potionEffects = player.getActivePotionEffects();
    PlayerData playerData = new PlayerData(
            player.getUniqueId(),
            player.getInventory().getContents(),
            player.getEnderChest().getContents(),
            potionEffects.toArray(new PotionEffect[0]),
            ExperienceUtil.getTotalExperience(player.getLevel(), player.getExp())
    );

    storage.savePlayerData(playerData);
}
```

This is an example code that migrates locally saved player data to the database.

```java
String directoryPath = getServer().getWorldContainer() + File.separator + "world" + File.separator + "playerdata";
Migration migration = new Migration(storage);
migration.migrateAllFilesToDatabase(directoryPath);
```
