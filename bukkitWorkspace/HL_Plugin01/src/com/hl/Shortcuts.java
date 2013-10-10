package com.hl;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class Shortcuts extends JavaPlugin implements Listener {

	@Override
	public void onEnable(){
		getLogger().info("Shortcuts.onEnable has been invoked!");
		PluginManager pm = this.getServer().getPluginManager();
		getLogger().info("got PluginManager");
	    pm.registerEvents(this, this);
		getLogger().info("registered listener");
        getServer().getScheduler().runTaskLaterAsynchronously(this, new StartingPointTeleporter(), 0L);
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Team red = board.registerNewTeam("RED");
        Team blue = board.registerNewTeam("BLUE");
    }
	
	@Override
	public void onDisable(){
		getLogger().info("Shortcuts.onDisable has been invoked!");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if (cmd.getName().equals("c")){
			System.out.println("Go to Creative");
			Bukkit.getServer().dispatchCommand(sender, "gamemode 1");
			return true;
		} else if (cmd.getName().equals("s")){
			System.out.println("Go to Survival");
			Bukkit.getServer().dispatchCommand(sender,  "gamemode survival @a");
		} else if (cmd.getName().equals("day")){
			System.out.println("/time set day");
			Bukkit.getServer().dispatchCommand(sender,  "time set day");
		} else if (cmd.getName().equals("night")){
			System.out.println("/time set night");
			Bukkit.getServer().dispatchCommand(sender,  "time set night");
		} else if (cmd.getName().equals("sun")){
			System.out.println("/weather sun");
			Bukkit.getServer().dispatchCommand(sender,  "weather sun");
		} else if (cmd.getName().equals("rain")){
			System.out.println("/weather rain");
			Bukkit.getServer().dispatchCommand(sender,  "weather storm");
		} else if (cmd.getName().equals("ct")){
			if (free)
			{
				System.out.println("/custom teleport " + args[0]);
				System.out.println(sender);
				Bukkit.getServer().dispatchCommand(sender,  "warp " + args[0]);
			}
		} 
		return false;
	}
	
	
	private boolean free = true;
	//String = map name
	private static Map<String, Occupation> audience = new HashMap<String, Occupation>();
	
	
	private void teleport(Sign sign, Player p)
	{
	    String counters = sign.getLine(3);
	    String counter[] = counters.split("/");
   
	    try
	    {
	    	int count = Integer.parseInt(counter[0]);
	    	int max = Integer.parseInt(counter[1]);
	    	if (count >= max)
	    	{
	    		free = false;
	    	}
	    	else
	    	{
		    	count = count + 1;
	    		String newCounters = "" + count + "/" + counter[1];
	    		sign.setLine(3, newCounters);
	    		sign.update();
			    String lobby = sign.getLine(0).split("@")[1].split("]")[0];
			    
			    if (!audience.containsKey(lobby))
			    {
			    	audience.put(lobby, new Occupation());
			    }
			    else
			    {
			    	Occupation o = audience.get(lobby);
			    	o.setActual(count);
			    	o.setMax(max);
			    	audience.put(lobby, o);
			    }
			    
				Bukkit.getServer().dispatchCommand(p,  "warp " + lobby);
	    	}
	    }
	    catch (NumberFormatException ex)
	    {
	    	p.sendMessage("Format invalide");
	    }
	}
	
	private void joinTeam(Sign sign, Player p)
	{
		
		
    	String startingPoint = sign.getLine(0).substring(1, sign.getLine(0).length() - 1);
		Bukkit.getServer().dispatchCommand(p,  "warp " + startingPoint);		    	
	}
	
	@EventHandler(priority=EventPriority.HIGH)
	public void onPlayerUse(PlayerInteractEvent event){
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK)
		{
		    Player p = event.getPlayer();
		    //Block b = event.getClickedBlock();
		    Sign sign = (Sign)event.getClickedBlock().getState();
		    System.out.println(event.getAction());
		    String line0 = sign.getLine(0);
		    if (line0.startsWith("[@"))
		    {
			    teleport(sign, p);
		    }
		    else if (line0.startsWith("[red@"))
		    {
		    	joinTeam(sign, p);
		    }
		    else if (line0.startsWith("[blue@"))
		    {
		    	
		    }

		}
	}
	
	@EventHandler(priority=EventPriority.HIGH)
	public void onPlayerDeath(PlayerDeathEvent event)
	{
		
	}
}
