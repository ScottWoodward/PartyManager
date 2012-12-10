package com.gmail.scottmwoodward.partymanager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.scottmwoodward.partymanager.commands.ChatCommand;
import com.gmail.scottmwoodward.partymanager.commands.DeclineCommand;
import com.gmail.scottmwoodward.partymanager.commands.HelpCommand;
import com.gmail.scottmwoodward.partymanager.commands.InviteCommand;
import com.gmail.scottmwoodward.partymanager.commands.JoinCommand;
import com.gmail.scottmwoodward.partymanager.commands.KickCommand;
import com.gmail.scottmwoodward.partymanager.commands.LeaveCommand;
import com.gmail.scottmwoodward.partymanager.commands.ListCommand;
import com.gmail.scottmwoodward.partymanager.commands.PromoteCommand;

public class CommandHandler implements CommandExecutor{
    private PartyManager plugin;

    public CommandHandler(PartyManager plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(args.length==0 || args[0].equalsIgnoreCase("help")||args[0].equalsIgnoreCase("?")){
            HelpCommand.execute(sender, args);
            return true;
        }
        if(sender instanceof Player){
            Player player = (Player)sender;
            if(args.length==1 && args[0].equalsIgnoreCase("list")){
                ListCommand.execute(player, plugin);
            }
            else if(args.length==2 && args[0].equalsIgnoreCase("list") && player.hasPermission("partymanager.admin.list")){
                ListCommand.execute(player, args[1], plugin);
            }            		
            else if(args.length==1 && args[0].equalsIgnoreCase("leave")){
                LeaveCommand.execute(player, plugin);
            }
            else if(args.length==2 && args[0].equalsIgnoreCase("invite")){
                InviteCommand.execute(player, args[1], plugin);
            }
            else if(args.length==2 && args[0].equalsIgnoreCase("kick")){
                KickCommand.execute(player, args[1], plugin);
            }
            else if(args.length==1 && args[0].equalsIgnoreCase("join")){
                JoinCommand.execute(player, plugin);
            }
            else if(args.length==1 && args[0].equalsIgnoreCase("decline")){
                DeclineCommand.execute(player, plugin);
            }
            else if(args.length==2 && args[0].equalsIgnoreCase("promote")){
                PromoteCommand.execute(player, args[1], plugin);
            }
            else if(args.length==1 && args[0].equalsIgnoreCase("chat")){
                ChatCommand.execute(player, plugin);
            }
            else{
                player.sendMessage("Please see /party for usage information");
            }
        }
        else{
            sender.sendMessage("You may only execute the help command from the console");
        }
        return true;
    }



}
