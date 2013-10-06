package com.hl;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class Listeners implements Listener {

	@EventHandler(priority=EventPriority.HIGH)
	public void onPlayerUse(PlayerInteractEvent event){
	    Player p = event.getPlayer();
	 
	    System.out.println("Class: " + event.getClickedBlock().getClass());
	    /*
	    if(p.getItemInHand().getType() == Material.BLAZE_POWDER){
	        Fireball fire = p.getWorld().spawn(event.getPlayer().getLocation(), Fireball.class);
	        fire.setShooter(p);
	    }
	    else if(p.getItemInHand().getType() == Material.BLAZE_ROD){
	        //Do whatever
	    }*/
	}
}
