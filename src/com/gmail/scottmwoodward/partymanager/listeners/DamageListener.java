package com.gmail.scottmwoodward.partymanager.listeners;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.gmail.scottmwoodward.partymanager.Party;
import com.gmail.scottmwoodward.partymanager.PartyManager;

public class DamageListener implements Listener{
    PartyManager plugin;

    public DamageListener(PartyManager plugin){
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerDamage(EntityDamageByEntityEvent event){
        if(!event.isCancelled()){
            if(event.getDamager() instanceof Player){
                if(event.getEntity() instanceof Player){
                    Player damager = (Player)event.getDamager();
                    Player damaged = (Player)event.getEntity();
                    UUID id = plugin.getPlayers().get(damaged.getName());
                    if(id!=null){
                        Party party = plugin.getParties().get(id);
                        if(party != null){
                            for(String member : party.getMembers()){
                                if(member != null){
                                    if(member.equalsIgnoreCase(damager.getName())){
                                        event.setDamage(0);
                                        event.setCancelled(true);
                                        return;
                                    }
                                }

                            }
                            if(party.getLeader().equalsIgnoreCase(damager.getName())){
                                event.setDamage(0);
                                event.setCancelled(true);
                                return; 
                            }

                        }
                    }
                }
            }
        }
    }
}
