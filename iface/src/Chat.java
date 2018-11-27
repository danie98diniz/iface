import java.util.ArrayList;

public class Chat {
    private ArrayList<Account> ChatMembers = new ArrayList<>(); // list o accounts that are members in chat
    private ArrayList<String> Messages = new ArrayList<>(); // list of messages in chat


    public void AddMember (Account AccountToBeAdded){ // add a account to the members list
        this.ChatMembers.add(AccountToBeAdded);
    }

    public ArrayList<String> getMessages() {
        return Messages;
    }

    public ArrayList<Account> getChatMembers() {
        return ChatMembers;
    }

    public void setChatMembers(ArrayList<Account> chatMembers) {
        ChatMembers = chatMembers;
    }
}







