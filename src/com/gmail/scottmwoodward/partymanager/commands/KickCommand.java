package com.gmail.scottmwoodward.partymanager.commands;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.gmail.scottmwoodward.partymanager.Party;
import com.gmail.scottmwoodward.partymanager.PartyManager;

public class KickCommand {

    public static void execute(Player sender, String player, PartyManager plugin) {
        UUID id = plugin.getPlayers().get(sender.getName());
        if(id!=null){
            Party party = plugin.getParties().get(id);
            if(party.getLeader().equalsIgnoreCase(sender.getName())){
                for(String members : party.getMembers()){
                    if(members != null){
                        if(members.equalsIgnoreCase(player)){
                            party.removeMember(player);
                            plugin.getPlayers().remove(player);
                            sender.sendMessage(ChatColor.YELLOW+player+" was kicked from the party");
                            Player p = Bukkit.getServer().getPlayer(player);
                            if(p!=null){
                                p.sendMessage(ChatColor.YELLOW+"You were kicked from the party");
                            }
                            if(party.hasNoMembers()){
                                PartyManager.endParty(sender.getName(), id);
                            }
                            for(String member : party.getMembers()){
                                if(member != null){
                                    Player play = Bukkit.getServer().getPlayer(member);
                                    if(play!=null){
                                        play.sendMessage(ChatColor.YELLOW+player+" was kicked from the party");
                                    }
                                }
                            }
                            return;
                        }
                    }
                }
                sender.sendMessage(ChatColor.YELLOW+"Player "+player+" is not in your party");
            }
            else{
                sender.sendMessage(ChatColor.YELLOW+"You must be the party leader to kick another player");
            }
        }
        else{
            sender.sendMessage(ChatColor.YELLOW+"You are not in a party");
        }
    }

}
