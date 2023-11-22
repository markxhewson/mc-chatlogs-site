package tech.markxhewson.chatlog;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import tech.markxhewson.chatlog.listener.ChatListener;
import tech.markxhewson.chatlog.manager.SQLManager;

@Getter
@SuppressWarnings("LombokGetterMayBeUsed")
public final class ChatLog extends JavaPlugin {

    private static ChatLog instance;
    private SQLManager sqlManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        instance = this;
        sqlManager = new SQLManager(this);

        loadListeners();
    }

    @Override
    public void onDisable() {}

    public void loadListeners() {
        getServer().getPluginManager().registerEvents(new ChatListener(this), this);
    }

    public static ChatLog getInstance() {
        return instance;
    }
}
