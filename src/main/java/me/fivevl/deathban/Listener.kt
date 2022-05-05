package me.fivevl.deathban

import net.md_5.bungee.api.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.PlayerDeathEvent
import java.util.*

class Listener(instance: Main) : org.bukkit.event.Listener {
    private var permission = ""
    private var message = ""
    private var time = 0

    init {
        this.permission = instance.config.getString("bypass-permission")!!
        this.time = instance.config.getInt("deathban-time")
        this.message = ChatColor.translateAlternateColorCodes('&', instance.config.getString("deathban-message")!!.replace("%time%", time.toString()))
    }
    @EventHandler
    fun onDeath(e: PlayerDeathEvent) {
        val p = e.player
        if (p.hasPermission(permission)) return
        val date = Date()
        date.time += time * 1000 * 60
        p.banPlayer(message, date)
        p.kickPlayer(message)
    }
}