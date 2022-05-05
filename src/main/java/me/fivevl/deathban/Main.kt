package me.fivevl.deathban

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    override fun onEnable() {
        logger.info("Deathban plugin by 5vl for u/SkiryHatesEmoji")
        saveDefaultConfig()
        Bukkit.getPluginManager().registerEvents(Listener(this), this)
    }
}