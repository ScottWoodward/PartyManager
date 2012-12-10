package com.gmail.scottmwoodward.partymanager;
import java.util.UUID;

public class Party {
    private String leader;
    private String[] members;
    private UUID id;
    
    
    public Party(String leader){
       this.leader = leader;
       this.members = new String[PartyManager.getMaxPartySize()-1];  
       id = UUID.randomUUID();
    }
    
    public String getLeader(){
        return leader;
    }
    
    public void setLeader(String player){
        leader = player;
    }
    
    public String[] getMembers(){
        return members;
    }
    
    /*
     * Adds the passed playername to the party. Returns true if successful, false otherwise.
     */
    public boolean addMember(String player){
        for(int i=0;i<PartyManager.getMaxPartySize()-1;i++){
            if(members[i]==null){
                members[i]=player;
                return true;
            }
        }
        return false;
    }
    
    public boolean hasMember(String player){
        for(String member : members){
            if(player.equalsIgnoreCase(member)){
                return true;
            }
        }
        if(leader.equalsIgnoreCase(player)){
            return true;
        }
        return false;
    }
    /*
     * Removes the passed player name from the party. Returns true if successful, false otherwise.
     */
    public boolean removeMember(String player){
        for(int i=0;i<PartyManager.getMaxPartySize()-1;i++){
            if(members[i]!=null){
                if(members[i].equalsIgnoreCase(player)){
                    members[i]=null;
                    return true;
                }
            }
        }
        return false;
    }
    
    public void removeAll(){
        for(int i=0;i<PartyManager.getMaxPartySize()-1;i++){
            members[i]=null;
        }
    }
    
    public UUID getID(){
        return id;
    }
    
    public boolean hasNoMembers(){
        for(int i=0;i<PartyManager.getMaxPartySize()-1;i++){
            if(members[i] != null){
                return false;
            }
        }
        return true;
    }
    
    public boolean hasRoom(){
        for(int i=0;i<PartyManager.getMaxPartySize()-1;i++){
            if(members[i] == null){
                return true;
            }
        }
        return false;
    }

}
