package com.gmail.scottmwoodward.partymanager.commands;

import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.gmail.scottmwoodward.partymanager.PartyManager;

public class ChatCommand {

    public static void execute(Player player, PartyManager plugin) {
        UUID id = plugin.getPlayers().get(player.getName());
        if(id!=null){
            if(plugin.getPartyChat().contains(player.getName())){
                plugin.getPartyChat().remove(player.getName());
                player.sendMessage(ChatColor.YELLOW+"You are no longer party chatting");
            }
            else{
                plugin.getPartyChat().add(player.getName());
                player.sendMessage(ChatColor.YELLOW+"You are now party chatting");
            }
        }
        else{
            player.sendMessage(ChatColor.YELLOW+"You must be in a party to use party chat");
        }
        
    }

}
