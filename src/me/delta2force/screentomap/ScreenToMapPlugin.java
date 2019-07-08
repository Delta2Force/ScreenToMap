package me.delta2force.screentomap;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.bukkit.map.MapView.Scale;
import org.bukkit.plugin.java.JavaPlugin;

import me.delta2force.screentomap.MapRenderMan.MapRenderSettings;
import net.md_5.bungee.api.ChatColor;

public class ScreenToMapPlugin extends JavaPlugin{
	private MapRenderMan renderMan; 
	
	@Override
	public void onEnable() {
		renderMan = new MapRenderMan(this);
		Bukkit.getLogger().info("The map render man is here!");
	}
	
	@Override
	public void onDisable() {
		renderMan = null;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			
			if(command.getName().equals("screentomap")) {
				if(args.length < 1) {
					ItemStack mapItem = new ItemStack(Material.FILLED_MAP);
					MapMeta mapMeta = (MapMeta) mapItem.getItemMeta();
					mapMeta.setMapView(Bukkit.createMap(player.getWorld()));
					mapMeta.getMapView().addRenderer(renderMan);
					mapItem.setItemMeta(mapMeta);
					
					player.getInventory().addItem(mapItem);
					player.sendMessage(ChatColor.GREEN + "Here you go!");	
				}else {
					if(args[0].equals("cfg")) {
						if(args.length < 2) {
							player.sendMessage(ChatColor.GOLD + "/screentomap cfg 0 0 1920 1080");
						}else {
							MapRenderSettings mrs = new MapRenderSettings(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]));
							renderMan.settings = mrs;
						}
					}
				}
			}
		}
		return true;
	}
}
