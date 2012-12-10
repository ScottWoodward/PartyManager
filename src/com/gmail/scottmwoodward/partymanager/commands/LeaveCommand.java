package com.gmail.scottmwoodward.partymanager.commands;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.gmail.scottmwoodward.partymanager.Party;
import com.gmail.scottmwoodward.partymanager.PartyManager;

public class LeaveCommand {
    public static void execute(Player sender, PartyManager plugin){
        UUID id = plugin.getPlayers().get(sender.getName());
        if(id != null){
            Party party = plugin.getParties().get(id);
            if(party.getLeader().equalsIgnoreCase(sender.getName())){
                PartyManager.endParty(party.getLeader(), id);
            }
            else{
                party.removeMember(sender.getName());
                plugin.getPlayers().remove(sender.getName());
                if(party.hasNoMembers()){
                    PartyManager.endParty(party.getLeader(), id);
                }
                for(String member : party.getMembers()){
                    if(member != null){
                        Player p = Bukkit.getServer().getPlayer(member);
                        if(p!=null){
                            p.sendMessage(ChatColor.YELLOW+sender.getName()+" has left the party");
                        }
                    }
                }
                sender.sendMessage(ChatColor.YELLOW+"You have left the party");
            }
        }
        else{
            sender.sendMessage(ChatColor.YELLOW+"You are not in a party");
        }
    }
}
