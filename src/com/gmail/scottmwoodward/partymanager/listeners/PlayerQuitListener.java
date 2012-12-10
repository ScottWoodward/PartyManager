package com.gmail.scottmwoodward.partymanager.listeners;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.gmail.scottmwoodward.partymanager.Party;
import com.gmail.scottmwoodward.partymanager.PartyManager;

public class PlayerQuitListener implements Listener{

    private PartyManager plugin;

    public PlayerQuitListener(PartyManager plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        Player p = event.getPlayer();
        if(p!=null){
            UUID id = plugin.getPlayers().get(p.getName());
            if(id!=null){
                Party party = plugin.getParties().get(id);
                if(party!=null){
                    if(p.getName().equalsIgnoreCase(party.getLeader())){
                        PartyManager.endParty(party.getLeader(), id);
                    }
                }
            }
        }

    }

}
