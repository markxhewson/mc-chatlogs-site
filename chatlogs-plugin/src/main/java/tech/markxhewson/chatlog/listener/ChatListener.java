package tech.markxhewson.chatlog.listener;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import tech.markxhewson.chatlog.ChatLog;
import tech.markxhewson.chatlog.util.Tasks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

public class ChatListener implements Listener {

    private final ChatLog chatLog;

    public ChatListener(ChatLog chatLog) {
        this.chatLog = chatLog;
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onChat(AsyncPlayerChatEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        String message = ChatColor.stripColor(event.getMessage());

        Tasks.runAsync(() -> saveMessage(uuid, message));
    }

    public void saveMessage(UUID uuid, String message) {
        String SQL = "INSERT INTO messages (uuid, message, server, timestamp) VALUES (?, ?, ?, ?)";

        try (Connection connection = chatLog.getSqlManager().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setString(1, uuid.toString());
            preparedStatement.setString(2, message);
            preparedStatement.setString(3, chatLog.getConfig().getString("server.name"));
            preparedStatement.setTimestamp(4, new Timestamp(new Date().getTime()));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
