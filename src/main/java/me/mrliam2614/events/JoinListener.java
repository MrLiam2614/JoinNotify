package me.mrliam2614.events;

import me.mrliam2614.JoinNotify;
import me.mrliam2614.config.ConfigVariables;
import me.mrliam2614.utils.ReflectionUtil;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.Random;

public class JoinListener implements Listener {
    private final JoinNotify plugin;

    public JoinListener(JoinNotify plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        String playerIp = player.getAddress().getHostName();
        String playerName = player.getName();

        if (plugin.getUserConfig().getConfig().contains("users." + playerName + ".IP")) {
            String playerCIp = plugin.getUserConfig().getConfig().getString("users." + playerName + ".IP");
            if (!playerIp.equals(playerCIp)) {
                player.kickPlayer(ConfigVariables.kickMSG);
                plugin.getFacilitisAPI().console.sendMessage(plugin, "\n&7Player &6" + playerName + " &7tried to join with ip: &c" + playerIp + "\n", "info");
            }
        }
        handleJoin(e);
    }

    public void handleJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (ConfigVariables.titleEnabled) {
            ReflectionUtil reflectionUtil = new ReflectionUtil();
            if (!reflectionUtil.is117()) {
                reflectionUtil.sendTitle(e.getPlayer(), ConfigVariables.titleTitleMSG.replace("{PLAYER}", e.getPlayer().getName()), ConfigVariables.titleSubtitleMSG.replace("{PLAYER}", e.getPlayer().getName()), ConfigVariables.titleFadeIN, ConfigVariables.titleStay, ConfigVariables.titleFadeOUT);
            } else {
                reflectionUtil.sendTitleNatively(e.getPlayer(), ConfigVariables.titleTitleMSG.replace("{PLAYER}", e.getPlayer().getName()), ConfigVariables.titleSubtitleMSG.replace("{PLAYER}", e.getPlayer().getName()), ConfigVariables.titleFadeIN, ConfigVariables.titleStay, ConfigVariables.titleFadeOUT);
            }
        }
        if (!ConfigVariables.joinEnabled) {
            e.setJoinMessage(null);
            if (p.isOp()) {
                plugin.getFacilitisAPI().msg.sendMessage(p, "&c&lJoin message is disabled!");
            }
        } else e.setJoinMessage(ConfigVariables.joinM.replace("{PLAYER}", p.getName()));

        if (ConfigVariables.spawnEnabled) {
            p.teleport(ConfigVariables.spawnLoc);
        }

        if (ConfigVariables.fireworkEnabled) {
            plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                public void run() {
                    Random r = new Random();

                    Firework fw = (Firework) p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
                    FireworkMeta fwm = fw.getFireworkMeta();

                    FireworkEffect.Type type = FireworkEffect.Type.valueOf(ConfigVariables.fireworkType);

                    int r1i = r.nextInt(17);
                    int r2i = r.nextInt(17);
                    Color c1 = getColor(r1i);
                    Color c2 = getColor(r2i);

                    FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(c1).withFade(c2).with(type).trail(r.nextBoolean()).build();
                    fwm.addEffect(effect);

                    fwm.setPower(ConfigVariables.fireworkPower);

                    fw.setFireworkMeta(fwm);
                }
            }, ConfigVariables.fireworkDelay);
        }
    }

    public Color getColor(int i) {
        if (i < 0 || i >= FireworkColor.values().length) return Color.RED;
        return FireworkColor.values()[i].getColor();
    }

    enum FireworkColor {
        AQUA(Color.AQUA),
        BLACK(Color.BLACK),
        BLUE(Color.BLUE),
        FUCHSIA(Color.FUCHSIA),
        GRAY(Color.GRAY),
        GREEN(Color.GREEN),
        LIME(Color.LIME),
        MAROON(Color.MAROON),
        NAVY(Color.NAVY),
        OLIVE(Color.OLIVE),
        ORANGE(Color.ORANGE),
        PURPLE(Color.PURPLE),
        RED(Color.RED),
        SILVER(Color.SILVER),
        TEAL(Color.TEAL),
        WHITE(Color.WHITE),
        YELLOW(Color.YELLOW);

        private Color c;

        FireworkColor(Color c) {
            this.c = c;
        }

        public Color getColor() {
            return c;
        }
    }
}
