package me.fivevl.deathban

import org.bukkit.event.EventHandler
import org.bukkit.event.entity.PlayerDeathEvent

class Listener(instance: Main) : org.bukkit.event.Listener {
    private var permission = ""
    private var message = ""
    private var time = 0

    init {
        this.permission = instance.config.getString("bypass-permission")!!
        this.time = instance.config.getInt("deathban-time")
        this.message = instance.config.getString("deathban-message")!!.replace("%time%", time.toString())
    }
    @EventHandler
    fun onDeath(e: PlayerDeathEvent) {

    }
}