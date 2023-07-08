package me.jumper251.replay.listener;


import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import space.commandf1.fastbuilder.AdvancedFastBuilderPlugin;

public abstract class AbstractListener implements Listener{
	
	protected AdvancedFastBuilderPlugin plugin;
	
	public AbstractListener(){
		this.plugin = AdvancedFastBuilderPlugin.getInstance();
	}
	
	public void register(){
		Bukkit.getPluginManager().registerEvents(this, this.plugin);
	}
	
	public void unregister(){
		HandlerList.unregisterAll(this);
	}
	

}
