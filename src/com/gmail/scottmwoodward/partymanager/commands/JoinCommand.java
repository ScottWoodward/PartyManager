package com.gmail.scottmwoodward.partymanager.commands;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.gmail.scottmwoodward.partymanager.Party;
import com.gmail.scottmwoodward.partymanager.PartyManager;

public class JoinCommand {

    public static void execute(Player sender, PartyManager plugin) {
        UUID partyID = plugin.getInvites().get(sender.getName());
        if(partyID != null){
            Party party = plugin.getParties().get(partyID);
            if(party != null){
                if(plugin.getParties().get(partyID).hasRoom()){
                    party.addMember(sender.getName());
                    plugin.getPlayers().put(sender.getName(), partyID);
                    sender.sendMessage(ChatColor.YELLOW+"You have joined "+party.getLeader()+"'s party");
                    Player player = Bukkit.getServer().getPlayer(party.getLeader());
                    player.sendMessage(ChatColor.YELLOW+sender.getName()+ " has joined your party");
                    for(String member : party.getMembers()){
                        if(member!=null){
                            Player p = Bukkit.getServer().getPlayer(member);
                            if(p!=null){
                                p.sendMessage(ChatColor.YELLOW+sender.getName()+ " has joined your party");
                            }
                        }
                    }
                }
                else{
                    sender.sendMessage(ChatColor.YELLOW+"The party is full");
                }
            }

            else{
                sender.sendMessage(ChatColor.YELLOW+"The party does not exist"); 
            }
            plugin.getInvites().remove(sender.getName());
        }
        else{
            sender.sendMessage(ChatColor.YELLOW+"You do not have a pending invite");
        }

    }

}
