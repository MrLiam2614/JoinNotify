package me.mrliam2614.events;

import me.mrliam2614.JoinNotify;
import me.mrliam2614.config.ConfigVariables;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveListener implements Listener {
    private final JoinNotify plugin;

    public LeaveListener(JoinNotify plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onQuit(PlayerQuitEvent q)
    {
        if(!ConfigVariables.leaveEnabled) q.setQuitMessage(null);
        else q.setQuitMessage(ConfigVariables.quitM.replace("{PLAYER}", q.getPlayer().getName()));
    }
}
