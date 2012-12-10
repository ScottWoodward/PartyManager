package com.gmail.scottmwoodward.partymanager.commands;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.gmail.scottmwoodward.partymanager.Party;
import com.gmail.scottmwoodward.partymanager.PartyManager;

public class ListCommand {

    public static void execute(Player sender, PartyManager plugin){
        UUID id = plugin.getPlayers().get(sender.getName());
        if(id!=null){
            Player player;
            Party party = plugin.getParties().get(id);
            String list = ChatColor.GOLD+party.getLeader()+" ";
            for(String member : party.getMembers()){
                if(member!=null){
                    player = Bukkit.getServer().getPlayer(member);
                    if(player==null){
                        list += ChatColor.DARK_GRAY+member+" ";
                    }
                    else{
                        list += ChatColor.WHITE+member+" ";
                    }

                }
            }
            sender.sendMessage(list);
        }
        else{
            sender.sendMessage(ChatColor.YELLOW+"You are not in a party");
        }
    }

    public static void execute(Player sender,String args, PartyManager plugin){
        Player p = Bukkit.getServer().getPlayer(args);
        if(p!=null){
            UUID id = plugin.getPlayers().get(p.getName());
            if(id!=null){
                Player player;
                Party party = plugin.getParties().get(id);
                String list = ChatColor.GOLD+party.getLeader()+" ";
                for(String member : party.getMembers()){
                    if(member!=null){
                        player = Bukkit.getServer().getPlayer(member);
                        if(player==null){
                            list += ChatColor.DARK_GRAY+member+" ";
                        }
                        else{
                            list += ChatColor.WHITE+member+" ";
                        }

                    }
                }
                sender.sendMessage(list);
            }
            else{
                sender.sendMessage(ChatColor.YELLOW+args+" is not in a party");
            }
        }
        else{
            sender.sendMessage(ChatColor.YELLOW+args+" is not online");
        }
    }

}
