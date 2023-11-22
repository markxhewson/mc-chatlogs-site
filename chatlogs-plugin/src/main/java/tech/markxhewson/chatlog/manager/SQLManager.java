package tech.markxhewson.chatlog.manager;

import lombok.Getter;
import tech.markxhewson.chatlog.ChatLog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Getter
public class SQLManager {

    private final ChatLog chatLog;

    private final String username, password, address, database;
    private final int port;

    public SQLManager(ChatLog chatLog) {
        this.chatLog = chatLog;

        username = chatLog.getConfig().getString("database.username");
        password = chatLog.getConfig().getString("database.password");
        address = chatLog.getConfig().getString("database.address");
        database = chatLog.getConfig().getString("database.database");
        port = chatLog.getConfig().getInt("database.port");

        updateTables();
    }

    public Connection getConnection() {
        String url = "jdbc:mysql://" + address + ":" + port + "/" + database;

        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateTables() {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS `messages` (`id` INT NOT NULL AUTO_INCREMENT, `uuid` VARCHAR(36) NOT NULL, `message` VARCHAR(256) NOT NULL, `server` VARCHAR(200) NOT NULL, `timestamp` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, PRIMARY KEY (`id`))")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
