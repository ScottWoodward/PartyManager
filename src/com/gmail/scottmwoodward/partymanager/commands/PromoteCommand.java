package com.gmail.scottmwoodward.partymanager.commands;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.gmail.scottmwoodward.partymanager.Party;
import com.gmail.scottmwoodward.partymanager.PartyManager;

public class PromoteCommand {

    public static void execute(Player sender, String player, PartyManager plugin) {
        UUID id = plugin.getPlayers().get(sender.getName());
        if(id!=null){
            Party party = plugin.getParties().get(id);
            if(party.getLeader().equalsIgnoreCase(sender.getName())){
                for(String member : party.getMembers()){
                    if(member != null){
                        if(member.equalsIgnoreCase(player)){
                            Player p = Bukkit.getServer().getPlayer(player);
                            if(p!=null){
                                String oldLeader = party.getLeader();
                                party.setLeader(p.getName());
                                party.removeMember(p.getName());
                                party.addMember(oldLeader);
                                sender.sendMessage(ChatColor.YELLOW+"You have promoted "+p.getName()+" to leader");
                                p.sendMessage(ChatColor.YELLOW+sender.getName()+" has promoted you to leader");
                            }
                            else{
                                sender.sendMessage(ChatColor.YELLOW+player+" is not online");
                            }
                            return;
                        }
                    }
                }
                sender.sendMessage(ChatColor.YELLOW+player+" is not in your party");
            }
            else{
                sender.sendMessage(ChatColor.YELLOW+"You must be the party leader to promote another player");
            }
        }
        else{
            sender.sendMessage(ChatColor.YELLOW+"You are not in a party");
        }
        
        
    }

}
