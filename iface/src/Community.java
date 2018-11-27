import java.util.ArrayList;

public class Community {
    private String Community_name;
    private ArrayList<String> Description = new ArrayList<>();
    private ArrayList<Account> CommunityMembers = new ArrayList<>();
    private Account Owner;
    private ArrayList<Account> MemberRequest = new ArrayList<>();
    private Chat CommunityChat = new Chat();


    public Chat getCommunityChat() {
        return CommunityChat;
    }

    public ArrayList<Account> getMemberRequest() {
        return MemberRequest;
    }


    public void AddMember(Account NewMember){
        ArrayList<Account> Members = getCommunityMembers();
        Members.add(NewMember);
    }

    public Account getOwner() {
        return Owner;
    }

    public void setOwner(Account owner) {
        Owner = owner;
    }

    public ArrayList<Account> getCommunityMembers() {
        return CommunityMembers;
    }

    public String getCommunity_name() {
        return Community_name;
    }

    public void setCommunity_name(String community_name) {
        Community_name = community_name;
    }

    public ArrayList<String> getDescription() {
        return Description;
    }

    public void setDescription(ArrayList<String> description) {
        Description = description;
    }
}
