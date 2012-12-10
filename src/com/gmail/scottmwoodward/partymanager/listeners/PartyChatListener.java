package com.gmail.scottmwoodward.partymanager.listeners;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.gmail.scottmwoodward.partymanager.Party;
import com.gmail.scottmwoodward.partymanager.PartyManager;

public class PartyChatListener implements Listener{

    private PartyManager plugin;

    public PartyChatListener(PartyManager plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event){

        if(plugin.getPartyChat().contains(event.getPlayer().getName())){
            UUID id = plugin.getPlayers().get(event.getPlayer().getName());
            if(id!=null){
                Party party = plugin.getParties().get(id);
                if(party!=null){
                    for(String member : party.getMembers()){
                        if(member!=null){
                            Player p = Bukkit.getServer().getPlayer(member);
                            if(p!=null){
                                if(PartyManager.getUsePref()){
                                    String name = PartyManager.getChat().getPlayerPrefix(event.getPlayer())+ event.getPlayer().getName()+PartyManager.getChat().getPlayerSuffix(event.getPlayer());
                                    name = ChatColor.translateAlternateColorCodes('&', name);
                                    p.sendMessage(ChatColor.DARK_AQUA+"[P] "+name+ChatColor.DARK_AQUA+": "+event.getMessage()); 
                                }
                                else{
                                    p.sendMessage(ChatColor.DARK_AQUA+"[P] "+event.getPlayer().getDisplayName()+ChatColor.DARK_AQUA+": "+event.getMessage());
                                }
                            }
                        }
                    }
                    Player p = Bukkit.getServer().getPlayer(party.getLeader());
                    if(p!=null){

                        if(p!=null){
                            if(PartyManager.getUsePref()){

                                String name = PartyManager.getChat().getPlayerPrefix(event.getPlayer())+ event.getPlayer().getName()+PartyManager.getChat().getPlayerSuffix(event.getPlayer());
                                name = ChatColor.translateAlternateColorCodes('&', name);
                                p.sendMessage(ChatColor.DARK_AQUA+"[P] "+name+ChatColor.DARK_AQUA+": "+event.getMessage());
                            }
                            else{
                                p.sendMessage(ChatColor.DARK_AQUA+"[P] "+event.getPlayer().getDisplayName()+ChatColor.DARK_AQUA+": "+event.getMessage());
                            }
                        }
                    }
                }
                event.setCancelled(true);
                if(plugin.getConfig().getBoolean("partyChatToConsole")){
                    plugin.getLogger().info("[P] "+event.getPlayer().getDisplayName()+": "+event.getMessage());
                }
                for(Player p : Bukkit.getServer().getOnlinePlayers()){
                    if(p.hasPermission("partymanager.admin.spy") && !party.hasMember(p.getName())){
                        p.sendMessage(ChatColor.GRAY+"[P] "+event.getPlayer().getName()+": "+event.getMessage());
                    }
                }
            }
            else{
                plugin.getPartyChat().remove(event.getPlayer().getName());
            }
        }


    }



}
