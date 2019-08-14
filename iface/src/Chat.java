import java.util.ArrayList;

public class Chat {
    private ArrayList<Account> ChatMembers = new ArrayList<>(); // list o accounts that are members in chat
    private ArrayList<String> Messages = new ArrayList<>(); // list of messages in chat


    public String GetWhoYouTalkingTo(Chat YourChat, Account YourAccount){
        String Username;
        if(YourChat.getChatMembers().get(0).getUsername() .equals(YourAccount.getUsername())){
            Username = YourChat.getChatMembers().get(1).getUsername();
        }
        else{
            Username = YourChat.getChatMembers().get(0).getUsername();
        }
        return Username;
    }

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

    //---------------------------------------------  ADDING A CHAT  --------------------------------------------------------
    public void CreateANewChat(Account YourAccount, Account HisAccount){
        Chat new_chat = new Chat();
        new_chat.AddMember(YourAccount);
        new_chat.AddMember(HisAccount);
        YourAccount.AddChatToAccount(new_chat);
        HisAccount.AddChatToAccount(new_chat);
        System.out.println("You can now chat with "+HisAccount.getUsername()+"!");
        System.out.println("Go to 'your chats' to begin a conversation");
    }



}







