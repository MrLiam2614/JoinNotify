package me.mrliam2614;

import me.mrliam2614.commands.CommandHandler;
import me.mrliam2614.config.ConfigVariables;
import me.mrliam2614.config.FConfig;
import me.mrliam2614.events.JoinListener;
import me.mrliam2614.events.LeaveListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class JoinNotify extends JavaPlugin {
    private FacilitisAPI facilitisAPI;
    private FConfig messageConfig;
    private FConfig userConfig;
    private ConfigVariables configVariables;

    private CommandHandler commandHandler;

    @Override
    public void onEnable() {
        facilitisAPI = FacilitisAPI.getInstance();

        facilitisAPI.messages.EnableMessage(this);
        messageConfig = new FConfig(this, "message_" + getConfig().getString("lang") + ".yml");
        if (!messageConfig.getConfig().isSet("message.NoPerm")) {
            messageConfig = new FConfig(this, "message_en.yml");
        }
        userConfig = new FConfig(this, "users.yml");

        configVariables = new ConfigVariables(this);

        reloadConfig();

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new JoinListener(this), this);
        pm.registerEvents(new LeaveListener(this), this);

        commandHandler = new CommandHandler(this);

        getCommand("joinnotify").setExecutor(commandHandler);
        getCommand("setspawn").setExecutor(commandHandler);
        getCommand("spawn").setExecutor(commandHandler);

    }

    public void onDisable() {
        facilitisAPI.messages.DisableMessage(this);
    }

    public FacilitisAPI getFacilitisAPI() {
        return facilitisAPI;
    }

    public FConfig getMessageConfig() {
        return messageConfig;
    }

    public FConfig getUserConfig() {
        return userConfig;
    }

    public ConfigVariables getVars() {
        return configVariables;
    }
}
