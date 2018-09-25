package me.scribble;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.LinkedHashMap;

public class Main extends JavaPlugin implements Listener {

    static LinkedHashMap check = new LinkedHashMap();

    @Override
    public void onEnable() {

        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        Bukkit.getScheduler().runTaskLater(this, () -> setUp(), 1L);
    }

    @Override
    public void onDisable() {
        check.clear();
    }

    static void setUp() {

        for (Player p : Bukkit.getOnlinePlayers()) {
            Main.check.put(p, null);
        }
    }

    @EventHandler
    public void onInventoryInteract(InventoryClickEvent event) {
        if (!(event.getInventory().getType() == InventoryType.ANVIL)) {
            return;
        }

        if (!(event.getWhoClicked().hasPermission("Anvil.use"))) {
            return;
        }


        Player player = (Player) event.getWhoClicked();
        if (Main.check.get(player) == null || System.currentTimeMillis() == (Long) Main.check.get(player)) {
            Bukkit.broadcastMessage("test4");
            return;
        }
        Main.check.put(player, System.currentTimeMillis());
    }


}
