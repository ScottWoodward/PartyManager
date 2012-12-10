package com.gmail.scottmwoodward.partymanager;

import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

import net.milkbowl.vault.chat.Chat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.scottmwoodward.partymanager.listeners.DamageListener;
import com.gmail.scottmwoodward.partymanager.listeners.PartyChatListener;
import com.gmail.scottmwoodward.partymanager.listeners.PlayerQuitListener;

public class PartyManager extends JavaPlugin{
    private static Map<String, UUID> players = new HashMap<String, UUID>();
    private static Map<UUID, Party> parties = new HashMap<UUID, Party>();
    private static Map<String, UUID> invites = new HashMap<String, UUID>();
    private static Set<String> partyChat = new HashSet<String>();
    private static Chat chat = null;
    private static boolean usePref = false;
    private static boolean useVault;
    private static int partySize;

    @Override
    public void onEnable(){
        getCommand("party").setExecutor(new CommandHandler(this));
        getServer().getPluginManager().registerEvents(new DamageListener(this), this);
        getServer().getPluginManager().registerEvents(new PartyChatListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(this),this);
        useVault = getConfig().getBoolean("useVault");
        partySize = getConfig().getInt("partySize");
        saveDefaultConfig();
        
        if(useVault){
            if(setupChat()){
                getLogger().info("Vault hooked, chat prefixes enabled");
                usePref = true;
            }
            else{
                getLogger().info("Vault did NOT hook, chat prefixes disabled");
                usePref = false;
            }
        }
    }

    protected static boolean getUsePref(){
        return usePref;
    }

    protected static Chat getChat(){
        return chat;
    }

    private boolean setupChat(){
        RegisteredServiceProvider<Chat> chatProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
        if (chatProvider != null) {
            chat = chatProvider.getProvider();
        }

        return (chat != null);
    }

    protected Map<String, UUID> getPlayers(){
        return players;
    }

    protected Map<UUID, Party> getParties(){
        return parties;
    }

    protected Map<String, UUID> getInvites(){
        return invites;
    }

    protected Set<String> getPartyChat(){
        return partyChat;
    }

    protected static UUID startParty(Player player){
        Party party = new Party(player.getName());
        players.put(player.getName(), party.getID());
        parties.put(party.getID(), party);
        if(player!=null){
            player.sendMessage(ChatColor.YELLOW+"You have created a new party");
        }
        return party.getID();
    }

    protected static void endParty(String name, UUID id){
        Party party = parties.get(id);
        Player player;
        for(String members : party.getMembers()){
            if(members != null){
                player = Bukkit.getServer().getPlayer(members);
                if(player != null){
                    player.sendMessage(ChatColor.YELLOW+"Your party has been disbanded");
                }
                players.remove(members);
            }
        }
        party.removeAll();
        player = Bukkit.getServer().getPlayer(name);
        if(player != null){
            player.sendMessage(ChatColor.YELLOW+"Your party has been disbanded");
        }
        players.remove(name);
        parties.remove(id);
    }
    
    
    public static int getMaxPartySize(){
        return partySize;
    }
    
    public String getParty(Player p){
        UUID id = players.get(p.getName());
        if(id != null){
            Party party = parties.get(id);
            if(party!=null){
                String partyMembers = party.getLeader();
                for(String member : party.getMembers()){
                    if(member != null){
                        partyMembers +=","+member;
                    }
                }
                return partyMembers;
            }
            else{
                return null;
            }
        }
        else{
            return null;
        }
    }
}
