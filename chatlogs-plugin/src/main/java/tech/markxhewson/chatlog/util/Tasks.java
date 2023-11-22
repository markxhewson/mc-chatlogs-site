package tech.markxhewson.chatlog.util;

import tech.markxhewson.chatlog.ChatLog;

public class Tasks {

    public static void runAsync(Runnable runnable) {
        ChatLog.getInstance().getServer().getScheduler().runTaskAsynchronously(ChatLog.getInstance(), runnable);
    }

}
