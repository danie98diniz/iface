import java.util.ArrayList;

public class Account {

        private String login;        // name to log into account
        private String password;     // password to log into account
        private String username;     // name to identify yourself as
        private ArrayList<String> FriendRequests = new ArrayList<>(); // list of friendship requests
        private ArrayList<String> FriendsList = new ArrayList<>(); // list of friends usernames
        private ArrayList<Chat> YourChats = new ArrayList<>(); // list of your chats
        private ArrayList<Community> YourCommunities = new ArrayList<>(); // list of communities
        private ArrayList<Community> OwnedCommunities = new ArrayList<>(); // list of communities you own


    public Account (String login, String password, String username){
        this.login = login;
        this.password = password;
        this.username = username;
    } // constructor

    public ArrayList<Community> getOwnedCommunities() {
        return OwnedCommunities;
    }

    public ArrayList<Chat> getYourChats() {
        return YourChats;
    }

    public ArrayList<Community> getYourCommunities() {
        return YourCommunities;
    }

    public void AddChatToAccount(Chat new_chat){
        this.YourChats.add(new_chat);
    }

    public void AddCommunityToAccount(Community new_community){
        this.YourCommunities.add(new_community);
    }

    public void AddCommunityYouOwnToAccount(Community new_community){
        this.OwnedCommunities.add(new_community);
    }

    public ArrayList<String> getFriendsList() {
        return FriendsList;
    }

    public void SendFriendRequest(String RequestOwner){
        this.getFriendRequests().add(RequestOwner);
  }

    public ArrayList<String> getFriendRequests() {
        return FriendRequests;
    }

    public  void setLogin(String login){
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}


