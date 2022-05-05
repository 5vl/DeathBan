package me.fivevl.deathban

import net.md_5.bungee.api.ChatColor
import org.bukkit.BanList
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerPreLoginEvent
import java.util.*
import kotlin.math.roundToInt

class Listener(instance: Main) : org.bukkit.event.Listener {
    private var permission = ""
    private var message = ""
    private var time = 0

    init {
        this.permission = instance.config.getString("bypass-permission")!!
        this.time = instance.config.getInt("deathban-time")
        this.message = ChatColor.translateAlternateColorCodes('&', instance.config.getString("deathban-message")!!)
    }
    @EventHandler
    fun onDeath(e: PlayerDeathEvent) {
        val p = e.player
        if (p.hasPermission(permission)) return
        val date = Date()
        date.time += time * 1000 * 60
        p.banPlayer(message.replace("%time%", time.toString()), date)
        p.kickPlayer(message)
    }

    @EventHandler
    fun onJoin(e: PlayerPreLoginEvent) {
        if (e.result == PlayerPreLoginEvent.Result.KICK_BANNED) {
            val endDate = Bukkit.getBanList(BanList.Type.NAME).getBanEntry(e.name)!!.expiration?.time ?: return
            val date = Date().time
            val min = ((endDate - date) / 1000 / 60).toDouble().roundToInt()
            e.kickMessage = message.replace("%time%", min.toString())
        }
    }
}