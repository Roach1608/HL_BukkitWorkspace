package com.hl;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Signs extends JavaPlugin {

	@Override
	public void onEnable(){
		getLogger().info("Signs.onEnable has been invoked!");
	}
	
	@Override
	public void onDisable(){
		getLogger().info("Signs.onDisable has been invoked!");
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if (cmd.getName().equals("c")){
			System.out.println("Go to Creative");
			Bukkit.getServer().dispatchCommand(sender, "gamemode 1");
			return true;
		} 
		return false;
	}
}
