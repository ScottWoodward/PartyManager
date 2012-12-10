package com.gmail.scottmwoodward.partymanager.commands;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.gmail.scottmwoodward.partymanager.PartyManager;

public class InviteCommand {
    public static void execute(Player sender, String player, PartyManager plugin){
        UUID partyID = plugin.getPlayers().get(sender.getName());
        if(partyID == null){
            //Initiates and stores a new party
            partyID = PartyManager.startParty(sender);
            //Stores the pending invite
            sendInvite(sender, player, partyID, plugin);      
        }
        else if(plugin.getParties().get(partyID).hasRoom()){
            if(plugin.getParties().get(partyID).getLeader().equalsIgnoreCase(sender.getName())){
                sendInvite(sender, player, partyID, plugin);
            }
            else{
                sender.sendMessage(ChatColor.YELLOW+"You must be the party leader to send invites");
            }

        }
        else{
            sender.sendMessage(ChatColor.YELLOW+"Your party is full");
        }
    }

    public static void sendInvite(Player sender, String player, UUID id, PartyManager plugin){
        Player p = Bukkit.getServer().getPlayer(player);
        if(p!=null){
            if(plugin.getPlayers().get(p.getName())!=null){
                sender.sendMessage(ChatColor.YELLOW+player+" is already in a party");
                if(plugin.getParties().get(id).hasNoMembers()){
                    PartyManager.endParty(sender.getName(), id);
                }
                return;
            }
            else if(plugin.getInvites().containsKey(p.getName())){
                if(plugin.getInvites().containsValue(id)){
                    sender.sendMessage(ChatColor.YELLOW+player+" already has a pending invite from you");
                    return;
                }
            }

            plugin.getInvites().put(p.getName(), id);
            p.sendMessage(ChatColor.YELLOW+"You have been invited to a party by "+sender.getName());
            sender.sendMessage(ChatColor.YELLOW+"You have invited "+player+" to join your party");

        }
        else{
            sender.sendMessage(ChatColor.YELLOW+"Could not find player "+player);
            if(plugin.getParties().get(id).hasNoMembers()){
                PartyManager.endParty(sender.getName(), id);
            }
        }
    }



}
