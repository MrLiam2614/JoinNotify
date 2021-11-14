package me.mrliam2614.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

import java.lang.reflect.Method;

public class ReflectionUtil {

    public Class<?> getNmsClass(String name) {
        String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];

        try {
            return Class.forName("net.minecraft.server." + version + "." + name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean is117() {
        String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        return version.contains("17");
    }

    private void sendPacket(Player player, Object packet) {
        try {
            Object handle = player.getClass().getMethod("getHandle").invoke(player);
            Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
            playerConnection.getClass().getMethod("sendPacket", getNmsClass("Packet")).invoke(playerConnection, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendTitleNatively(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        for(Method method : player.getClass().getDeclaredMethods()) {
            if(method.getName().equals("sendTitle")) {
                if(method.getParameterCount() == 5) {
                    try {
                        method.invoke(player, title, subtitle, fadeIn, stay, fadeOut);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void sendTitle(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        try {
            Object enumTitle = getNmsClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null);
            Object titleChat = getNmsClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + title + "\"}");

            Object enumSubTitle = getNmsClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get(null);
            Object subtitleChat = getNmsClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + subtitle + "\"}");

            Constructor<?> titleConstructor = getNmsClass("PacketPlayOutTitle").getConstructor(getNmsClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNmsClass("IChatBaseComponent"), int.class, int.class, int.class);
            Object titlePacket = titleConstructor.newInstance(enumTitle, titleChat, fadeIn, stay, fadeOut);
            Object subtitlePacket = titleConstructor.newInstance(enumSubTitle, subtitleChat, fadeIn, stay, fadeOut);

            sendPacket(player, titlePacket);
            sendPacket(player, subtitlePacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
