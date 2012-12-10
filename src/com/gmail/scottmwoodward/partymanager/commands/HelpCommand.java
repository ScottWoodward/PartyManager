package com.gmail.scottmwoodward.partymanager.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class HelpCommand {
    public static void execute(CommandSender sender, String[] args){
        String help = ChatColor.YELLOW+"";
        if(args.length == 0 || (args.length==1 && args[0].equalsIgnoreCase("help"))){
            help = "Party Manager Help\n";
            help+= "------------------\n";
            help+="- /party invite : Invites a player to your party\n";
            help+="- /party join : Joins the party you were invited to\n";
            help+="- /party leave : Leaves your current party\n";
            help+="- /party decline: Declines your current party invite\n";
            help+="- /party list: Displays your current party members\n";
            help+="- /party kick: Removes a player from your party\n";
            help+="- /party promote: Promotes another player to party leader\n";
            help+="- /party chat: Toggles whether or not to speak in party chat only\n";
            help+="For more information on a commant type /party help <command>";
            
        }
        else if(args[0].equalsIgnoreCase("help")||args[0].equalsIgnoreCase("?")){
            if(args[1].equalsIgnoreCase("invite")){
                help = "Party Invite Help\n";
                help += "-----------------\n";
                help += "Usage: /party invite <player>\n";
                help += "Invites the specified player to join your party.\n";
                help += "You must be the party leader, or not part of a party to use this command. This will start a new party if you are not in one.\n";
            }
            if(args[1].equalsIgnoreCase("join")){
                help = "Party Join Help\n";
                help += "---------------\n";
                help += "Usage: /party join\n";
                help += "Accepts your most recently received party invite.\n";
                help += "You may not use this command if you are in a party.\n";
            }
            if(args[1].equalsIgnoreCase("leave")){
                help = "Party Leave Help\n";
                help += "----------------\n";
                help += "Usage: /party leave\n";
                help += "Leaves your current party.\n";
                help += "You must be in a party to use this command. Your current party will be disbanded if you are the leader, or the last remaining non-leader.\n";
            }
            if(args[1].equalsIgnoreCase("decline")){
                help = "Party Decline Help\n";
                help += "-----------------\n";
                help += "Usage: /party decline\n";
                help += "Declines your pending party invite.\n";
                help += "You must have a pending invite to use this command.\n";
            }
            if(args[1].equalsIgnoreCase("list")){
                help = "Party List Help\n";
                help += "---------------\n";
                help += "Usage: /party list\n";
                if(sender.hasPermission("partymanager.admin.list")){
                    help+= "OR\n";
                    help+= "Usage: /party list <playername>\n";
                }

                help += "Lists your current party members and their status.\n";
                help += "You must be in a party to use this command. The party leader's name is gold, online members are white, and offline members are gray.\n";
            }
            if(args[1].equalsIgnoreCase("kick")){
                help = "Party Kick Help\n";
                help += "---------------\n";
                help += "Usage: /party kick <player>\n";
                help += "Removes the specified player from the party\n";
                help += "You must be the party leader to use this command\n";
            }
            if(args[1].equalsIgnoreCase("promote")){
                help = "Party Promote Help\n";
                help += "------------------\n";
                help += "Usage: /party promote <player>\n";
                help += "Promotes the specified player to party leader\n";
                help += "You must be the party leader to use this command\n";
            }
            if(args[1].equalsIgnoreCase("chat")){
                help = "Party Promote Help\n";
                help += "------------------\n";
                help += "Usage: /party chat\n";
                help += "Toggles whether or not you are speaking in party chat\n";
                help += "You must be in a party to talk in part chat\n";
            }
        }
        sender.sendMessage(help);
    }
}
