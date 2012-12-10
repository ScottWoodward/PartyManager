package com.gmail.scottmwoodward.partymanager.commands;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.gmail.scottmwoodward.partymanager.Party;
import com.gmail.scottmwoodward.partymanager.PartyManager;

public class DeclineCommand {

    public static void execute(Player sender, PartyManager plugin) {
        UUID id = plugin.getInvites().get(sender.getName());
        if(id != null){
            Party party = plugin.getParties().get(id);
            if(party!=null){
                Player player = Bukkit.getServer().getPlayer(party.getLeader());
                if(player!=null){
                    player.sendMessage(ChatColor.YELLOW+sender.getName()+" has declined your invitation");
                }
                if(party.hasNoMembers()){  
                    PartyManager.endParty(party.getLeader(), id);
                    sender.sendMessage(ChatColor.YELLOW+"You have declined "+party.getLeader()+"'s invitation");
                }
            }
            plugin.getInvites().remove(sender.getName());
            
        }
        else{
            sender.sendMessage(ChatColor.YELLOW+"You do not have a pending invite");
        }
    }

}
