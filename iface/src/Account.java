import java.util.ArrayList;
import java.util.Scanner;

public class Account {

    private String login;        // name to log into account
        private String password;     // password to log into account
        private String username;     // name to identify yourself as
        private ArrayList<String> FriendRequests = new ArrayList<>(); // list of friendship requests
        private ArrayList<String> FriendsList = new ArrayList<>(); // list of friends usernames
        private ArrayList<Chat> YourChats = new ArrayList<>(); // list of your chats
        private ArrayList<Community> YourCommunities = new ArrayList<>(); // list of communities
        private ArrayList<Community> OwnedCommunities = new ArrayList<>(); // list of communities you own
        private int age;
        private String gender;
        private String profession;
        private String maritalStatus;

    //---------------------------------------------      LOG IN      -------------------------------------------------------
    public void Log_into_Account(Account yourAccount, ArrayList<Account> AccountsList, ArrayList<Community> CommunityList){
        Scanner scan = new Scanner(System.in); // creates a object called scan to scan inputs along the code
        Login_Menu:
        while(true){
            System.out.println("Welcome "+yourAccount.getUsername()+"!");
            System.out.println("type 0 to log out");
            System.out.println("type 1 to add a friends");
            System.out.println("type 2 to go to your friend list/requests");
            System.out.println("type 3 to go to chats");
            System.out.println("type 4 to edit your account");
            System.out.println("type 5 to go to communities");
            System.out.println("type 6 to recover info of your account");
            System.out.println("type 7 to terminate account");

            int option = scan.nextInt();
            scan.nextLine();
            if(option == 0){
                return;
            }//return
            else if(option == 1){
                while(true){
                    System.out.println("Type the username of your friend!");
                    String username = scan.nextLine();
                    for(int i = 0; i < AccountsList.size(); i++){
                        if(username .equals(AccountsList.get(i).getUsername())){
                            AccountsList.get(i).SendFriendRequest(yourAccount.getUsername());
                            System.out.println("Friend request sent to "+AccountsList.get(i).getUsername()+"!");
                            continue Login_Menu;
                        }
                    }
                    System.out.println("No users found");
                    System.out.println("Type 1 to try again");
                    System.out.println("Type 2 to go back");
                    option = scan.nextInt();
                    scan.nextLine();
                    if(option == 2){
                        continue Login_Menu;
                    }
                }
            }//add friends
            else if(option == 2){
                yourAccount.FriendRequests(yourAccount, AccountsList);
            }//view friend requests
            else if(option == 3){

                System.out.println("Type 1 to see your chats");
                System.out.println("Type 2 to add a new chat");
                option = scan.nextInt();
                scan.nextLine();
                if(option == 1){
                    yourAccount.ChatWithAFriend(yourAccount);
                }
                else if( option == 2){
                    System.out.println("You can chat with friends or others accounts registered");
                    ArrayList<String> FriendsList;
                    FriendsList = yourAccount.getFriendsList();
                    if(FriendsList.size() > 0){
                        System.out.println("friends:");
                    }

                    if(FriendsList.size() > 0){ //print list of friends if you have more than 0
                        for(int i = 0; i < FriendsList.size(); i++){
                            System.out.println(FriendsList.get(i));
                        }
                    }
                    System.out.println("Who do you want to begin a chat with?");
                    System.out.println("Type -1 to go back");
                    String username = scan.nextLine();
                    if(username .equals("-1")){
                        continue Login_Menu;
                    }
                    int HisIndex = -1;
                    for (int i = 0; i < AccountsList.size(); i++){
                        if(username .equals(AccountsList.get(i).getUsername())){
                            HisIndex = i;
                        }
                    }
                    if(HisIndex == -1){
                        System.out.println("There are no users named: "+username);
                        System.out.println("Type 1 to go back");
                        System.out.println("Type other number to try again");
                        option = scan.nextInt();
                        scan.nextLine();
                        if(option == 1){
                            continue Login_Menu;
                        }
                    }
                    else{
                        Account hisAccount = AccountsList.get(HisIndex);
                        Chat newChat = new Chat();
                        newChat.CreateANewChat(yourAccount, hisAccount);
                        continue Login_Menu;
                    }
                }//adding a new chat to your chat list
            }//go to chat options
            else if(option == 4){
                AccountEditing(yourAccount, AccountsList);
            }//edit account
            else if(option == 5){
                Community new_community = new Community();
                new_community.CommunityMenu(yourAccount, AccountsList, CommunityList);
            }//go to communities
            else if(option == 6){
                yourAccount.ShowAccountInformation(yourAccount, CommunityList);
            }//recover account info
            else if(option == 7){
                System.out.println("All your data will be deleted.");
                System.out.println("This cannot be undone.");
                System.out.println("Are you sure you want to delete your account?");
                System.out.println("y/n:");
                String Confirmation = scan.nextLine();
                if(Confirmation .equals("y")){
                    System.out.println("type your password to DELETE your account:");
                    String password = scan.nextLine();
                    if(password .equals(yourAccount.getPassword())){
                        yourAccount.AccountTermination(yourAccount, AccountsList, CommunityList);
                        System.out.println("Your account was terminated");
                        return;
                    }
                    else{
                        System.out.println("Wrong password");
                        System.out.println("exiting...");
                    }
                }
            }//account termination
        }
    }
    //--------------------------------------------- ACCOUNT CREATION -------------------------------------------------------
    public void CreateAccount(ArrayList<Account> AccountsList){
        Scanner scan = new Scanner(System.in); // creates a object called scan to scan inputs along the code
        String login;
        while(true){
            System.out.println("Please type a valid login: ");
            login = scan.nextLine();
            int isvalid = 1;
            for(int i = 0; i < AccountsList.size(); i++){
                if(login .equals(AccountsList.get(i).getLogin())){
                    isvalid = 0;
                }
            }
            if(isvalid == 1){
                break;
            }
            System.out.println("This login is already taken");
        }
        String password;
        String username;
        while( true){
            System.out.println("Please type a password: ");
            password = scan.nextLine();
            System.out.println("Please confirm your password: ");
            String confirmation_password = scan.nextLine();
            if( password .equals(confirmation_password)){ // compare if both passwords are the same
                break;
            }
            System.out.println("Passwords don`t match");
        }

        while(true){
            System.out.println("Please type a username");
            username = scan.nextLine();
            int isvalid = 1;
            for(int i = 0; i < AccountsList.size(); i++){
                if(username .equals(AccountsList.get(i).getUsername())){
                    isvalid = 0;
                }
            }
            if(isvalid == 1){
                break;
            }
            System.out.println("Username already taken!");
        }
        Builder newBuilder = new Builder();
        System.out.println("Would you like to specify your gender? (y/n)");
        String option = scan.nextLine();
        if(option.equals("y")){
            System.out.println("Type your gender:");
            String gender = scan.nextLine();
            newBuilder = newBuilder.withGender(gender);
        }
        System.out.println("Would you like to specify your age? (y/n)");
        option = scan.nextLine();
        if(option.equals("y")){
            System.out.println("Type your age:");
            int age = scan.nextInt();
            scan.nextLine();
            newBuilder = newBuilder.withAge(age);
        }
        System.out.println("Would you like to specify your profession? (y/n)");
        option = scan.nextLine();
        if(option.equals("y")){
            System.out.println("Type your profession:");
            String profession = scan.nextLine();
            newBuilder = newBuilder.withProfession(profession);
        }
        System.out.println("Would you like to specify your marital status? (y/n)");
        option = scan.nextLine();
        if(option.equals("y")){
            System.out.println("Type your marital status:");
            String maritalStatus = scan.nextLine();
            newBuilder = newBuilder.withMaritalStatus(maritalStatus);
        }

        Account new_account = new Account(login, password , username, newBuilder);
        AccountsList.add(new_account);

        System.out.println("Account created with success!");
        System.out.println("Done! Exiting..");
    }
    //------------------------------------------  TERMINATE ACCOUNT  -------------------------------------------------------
    public void AccountTermination(Account YourAccount, ArrayList<Account> AccountsList, ArrayList<Community> CommunityList){
        YourAccount.QuitAllCommunities(YourAccount, CommunityList);
        YourAccount.DeleteYourChats(YourAccount, AccountsList);
        YourAccount.DeleteYourFriends(YourAccount, AccountsList);
        for(int i = 0; i < AccountsList.size(); i++){
            if(YourAccount == AccountsList.get(i)){
                AccountsList.remove(i);
            }
        }
    }

    public void QuitAllCommunities(Account YourAccount, ArrayList<Community> CommunityList){
        Community Communitytosearch;
        for(int i = 0; i < YourAccount.getYourCommunities().size(); i++){//will go through all communities you´re in
            Communitytosearch = YourAccount.getYourCommunities().get(i);
            if(Communitytosearch.getCommunityMembers().get(0).getUsername() .equals(YourAccount.getUsername())){//if you are the leader
                if(Communitytosearch.getCommunityMembers().size() < 2){
                    for(int k = 0; k < CommunityList.size(); i++){
                        if(CommunityList.get(k).getCommunity_name() .equals(Communitytosearch.getCommunity_name())){
                            CommunityList.remove(k);
                            break;
                        }
                    }
                }
                else{
                    Communitytosearch.setOwner(Communitytosearch.getCommunityMembers().get(1));
                    Communitytosearch.getCommunityMembers().get(1).getOwnedCommunities().add(Communitytosearch);
                    Communitytosearch.getCommunityMembers().remove(0);
                }
            }
            else{
                int index = 0;
                ArrayList<Account> CommunityMembers = YourAccount.getYourCommunities().get(i).getCommunityMembers();
                for(int j = 0; j < CommunityMembers.size();j++){
                    if(YourAccount.getUsername() .equals(CommunityMembers.get(j).getUsername())){
                        index = j;
                        break;
                    }
                }
                CommunityMembers.remove(index);
            }
        }



    }
    public void DeleteYourChats(Account YourAccount, ArrayList<Account> AccountsList){
        Chat ChatToUse;
        for(int i = 0; i < YourAccount.getYourChats().size(); i++){
            ChatToUse = YourAccount.getYourChats().get(i);
            String UsernameOfFriend = ChatToUse.GetWhoYouTalkingTo(ChatToUse, YourAccount);
            int index = GetIndexByUsername(UsernameOfFriend, AccountsList);
            Account FriendAccount;
            FriendAccount = AccountsList.get(index);
            for(int j = 0; j < FriendAccount.getYourChats().size(); j++){
                String YourName = ChatToUse.GetWhoYouTalkingTo(FriendAccount.getYourChats().get(j), FriendAccount);
                if(YourName .equals(YourAccount.getUsername())){
                    FriendAccount.getYourChats().remove(j);
                }
            }
        }
    }
    public void DeleteYourFriends(Account YourAccount, ArrayList<Account> AccountsList){
        for(int i = 0; i < YourAccount.getFriendsList().size(); i++){
            String Friend = YourAccount.getFriendsList().get(i);
            int index = GetIndexByUsername(Friend, AccountsList);
            for(int j = 0; j < AccountsList.get(index).getFriendsList().size(); j++){
                if(AccountsList.get(index).getFriendsList().get(j) .equals(YourAccount.getUsername())){
                    AccountsList.get(index).getFriendsList().remove(j);
                    break;
                }
            }
        }
    }
    //--------------------------------------- SHOW (RECOVER) ACCOUNT INFO --------------------------------------------------
    public void ShowAccountInformation(Account YourAccount, ArrayList<Community> CommunityList){
        Scanner scan = new Scanner(System.in); // creates a object called scan to scan inputs along the code

        Info_Menu:
        while(true){
            System.out.println("("+YourAccount.getUsername()+") ACCOUNT INFORMATION:");
            System.out.println("Login: "+YourAccount.getLogin());
            System.out.println("Username: "+YourAccount.getUsername());
            if(YourAccount.gender == null){
                System.out.println("Gender: Undefined");
            }
            else{
                System.out.println("Gender: "+YourAccount.gender);
            }
            if(YourAccount.age == 0){
                System.out.println("Age: Undefined");
            }
            else{
                System.out.println("Age: "+YourAccount.age);
            }
            if(YourAccount.profession == null){
                System.out.println("Profession: Undefined");
            }
            else{
                System.out.println("Profession: "+YourAccount.profession);
            }
            if(YourAccount.maritalStatus == null){
                System.out.println("Marital Status: Undefined");
            }
            else{
                System.out.println("Marital Status: "+YourAccount.maritalStatus);
            }
            System.out.println("type 1 to see friends list");
            System.out.println("type 2 to see your chats");
            System.out.println("type 3 to see communities you´re in");
            System.out.println("type 4 to change your profile");
            System.out.println("type 0 to exit");

            int option = scan.nextInt();
            scan.nextLine();

            if(option == 0){
                break;
            }//exit
            else if(option == 1){
                System.out.println("YOUR FRIENDS LIST:");
                for(int i = 0; i < YourAccount.getFriendsList().size(); i++){
                    System.out.println(" - "+YourAccount.getFriendsList().get(i));
                }
                System.out.println("type a number to exit");
                option = scan.nextInt();
                scan.nextLine();
            }//friends list
            else if(option == 2){
                ChatWithAFriend(YourAccount);
            }//your chats
            else if(option == 3){
                if(YourAccount.getYourCommunities().size() > 0){
                    while(true){
                        System.out.println("COMMUNITIES YOU ARE IN");
                        for(int i = 0; i < YourAccount.getYourCommunities().size(); i++){
                            System.out.println(i+". "+YourAccount.getYourCommunities().get(i).getCommunity_name());
                        }

                        System.out.println("choose a community to see");
                        System.out.println("or type -1 to go back");
                        int choice = scan.nextInt();
                        scan.nextLine();
                        if(choice == -1){
                            continue Info_Menu;
                        }
                        else if(choice >= 0 && choice < YourAccount.getYourCommunities().size()){
                            Community CommunityToShow = YourAccount.getYourCommunities().get(choice);
                            CommunityToShow.CommunityLobby(CommunityToShow, CommunityList, YourAccount);
                        }
                        else{
                            System.out.println("please choose a valid community");
                        }
                    }
                }
                else{
                    System.out.println("You are not in any Communities");
                }

            }//your communities
        }
    }
    //--------------------------------------------- ACCOUNT EDITING  -------------------------------------------------------
    public void AccountEditing(Account YourAccount, ArrayList<Account> AccountsList){
        Scanner scan = new Scanner(System.in); // creates a object called scan to scan inputs along the code
        int option;
        Editing_Menu:
        while(true){
            System.out.println("Type 0 to go back");
            System.out.println("Type 1 to change your login");
            System.out.println("Type 2 to change your password");
            System.out.println("Type 3 to change your username");
            System.out.println("Type 4 to change your profile");
            option = scan.nextInt();
            scan.nextLine();
            if(option == 0){
                break;
            }
            else if(option == 1){
                String login;
                while(true){
                    System.out.println("Please type a valid login: ");
                    login = scan.nextLine();
                    int isvalid = 1;
                    for(int i = 0; i < AccountsList.size(); i++){
                        if(login .equals(AccountsList.get(i).getLogin())){
                            isvalid = 0;
                        }
                    }
                    if(isvalid == 1){
                        break;
                    }
                    else{
                        System.out.println("This login is already taken");
                        continue Editing_Menu;
                    }

                }
                YourAccount.setLogin(login);
                System.out.println("Your login is updated!");
            }//change login of the user
            else if(option == 2){
                String password;
                while( true){
                    System.out.println("Please type a new password: ");
                    password = scan.nextLine();
                    System.out.println("Please confirm your new password: ");
                    String confirmation_password = scan.nextLine();
                    if( password .equals(confirmation_password)){ // compare if both passwords are the same
                        break;
                    }
                    else{
                        System.out.println("Passwords don`t match");
                        continue Editing_Menu;
                    }
                }
                YourAccount.setPassword(password);
                System.out.println("Your password is updated!");
            }//change password of the user
            else if(option == 3){
                String username;
                while(true){
                    System.out.println("Please type a new username");
                    username = scan.nextLine();
                    int isvalid = 1;
                    for(int i = 0; i < AccountsList.size(); i++){
                        if(username .equals(AccountsList.get(i).getUsername())){
                            isvalid = 0;
                        }
                    }
                    if(isvalid == 1){
                        break;
                    }
                    else{
                        System.out.println("Username already taken!");
                        continue Editing_Menu;
                    }
                }
                YourAccount.setUsername(username);
                System.out.println("Your username is updated!");
            }//change username of the user
            else if (option == 4){
                YourAccount.EditProfile(YourAccount);
            }
        }
    }
    public void EditProfile(Account YourAccount){
        Scanner scan = new Scanner(System.in); // creates a object called scan to scan inputs along the code

        while (true){
            System.out.println("choose what to change or type 0 to go back:");

            if(YourAccount.gender == null){
                System.out.println("1.Gender: Undefined");
            }
            else{
                System.out.println("1.Gender: "+YourAccount.gender);
            }
            if(YourAccount.age == 0){
                System.out.println("2.Age: Undefined");
            }
            else{
                System.out.println("2.Age: "+YourAccount.age);
            }
            if(YourAccount.profession == null){
                System.out.println("3.Profession: Undefined");
            }
            else{
                System.out.println("3.Profession: "+YourAccount.profession);
            }
            if(YourAccount.maritalStatus == null){
                System.out.println("4.Marital Status: Undefined");
            }
            else{
                System.out.println("4.Marital Status: "+YourAccount.maritalStatus);
            }

            int option = scan.nextInt();
            scan.nextLine();

            if(option == 0){
                break;
            }
            else if (option == 1){
                System.out.println("Would you like to change your gender? (y/n)");
                String choice = scan.nextLine();
                if(choice.equals("y")){
                    System.out.println("Type your gender:");
                    String gender = scan.nextLine();
                    YourAccount.setGender(gender);
                }
            }
            else if(option == 2){
                System.out.println("Would you like to change your age? (y/n)");
                String choice = scan.nextLine();
                if(choice.equals("y")){
                    System.out.println("Type your age:");
                    int age = scan.nextInt();
                    scan.nextLine();
                    YourAccount.setAge(age);
                }
            }
            else if (option == 3){
                System.out.println("Would you like to change your profession? (y/n)");
                String choice = scan.nextLine();
                if(choice.equals("y")){
                    System.out.println("Type your profession:");
                    String profession = scan.nextLine();
                    YourAccount.setProfession(profession);
                }
            }
            else if(option == 4){
                System.out.println("Would you like to change your marital status? (y/n)");
                String choice = scan.nextLine();
                if(choice.equals("y")){
                    System.out.println("Type your marital status:");
                    String maritalStatus = scan.nextLine();
                    YourAccount.setMaritalStatus(maritalStatus);
                }
            }

        }

    }
     //--------------------------------------------- FRIEND REQUESTS --------------------------------------------------------
    public void FriendRequests(Account yourAccount, ArrayList<Account> AccountsList){
        Scanner scan = new Scanner(System.in); // creates a object called scan to scan inputs along the code
        FriendRequestMenu:
        while(true){
            System.out.println("type 1 to see your friends list");
            System.out.println("type 2 to see your friends request");
            System.out.println("type 3 to exit");
            int option = scan.nextInt();
            scan.nextLine();
            if(option == 1){
                ArrayList<String> FriendsList;
                FriendsList = yourAccount.getFriendsList();
                while(true){
                    System.out.println("Friends List");
                    if(FriendsList.size() == 0){
                        System.out.println("Your friends list is empty :(");
                    }
                    else{
                        for(int i = 0; i < FriendsList.size(); i++){
                            System.out.println(FriendsList.get(i));
                        }
                    }
                    System.out.println("type a number to exit");
                    option = scan.nextInt();
                    scan.nextLine();
                    break;
                }
            }
            else if(option == 2){
                ArrayList<String> FriendRequest;
                FriendRequest = yourAccount.getFriendRequests();
                if(FriendRequest.size() > 0){
                    while(FriendRequest.size() > 0){
                        for(int j = 0; j < FriendRequest.size(); j++){
                            System.out.println(j+". "+FriendRequest.get(j)+ " wants to be your friend!");
                        }
                        System.out.println("choose a friend see options");
                        System.out.println("or choose -1 exit");
                        int choice = scan.nextInt();
                        scan.nextLine();
                        if(choice >= 0 && choice < FriendRequest.size()){
                            System.out.println("Type 1 to accept or 2 to reject");
                            option = scan.nextInt();
                            scan.nextLine();
                            if(option == 1){
                                // adding his username into your friends list
                                String FriendUsername = FriendRequest.get(choice);
                                FriendRequest.remove(choice);
                                ArrayList<String> YourFriendsList;
                                YourFriendsList = yourAccount.getFriendsList();
                                YourFriendsList.add(FriendUsername);
                                // adding your username into his friends list
                                ArrayList<String> HisFriendsList;
                                int friend_index;
                                friend_index = GetIndexByUsername(FriendUsername, AccountsList);
                                HisFriendsList = AccountsList.get(friend_index).getFriendsList();
                                HisFriendsList.add(yourAccount.getUsername());
                                System.out.println("You and " +FriendUsername+" are now friends!");
                            }
                            else if(option == 2){
                                FriendRequest.remove(0);
                                System.out.println("friend request rejected!");
                            }
                        }
                        else if(choice == -1){
                            continue FriendRequestMenu;
                        }
                        else{
                            System.out.println("please choose a valid friend request");
                        }

                    }
                }
                else{
                    System.out.println("You have no friends requests at this moment, go make some friends!");
                }
            }
            else if(option == 3){
                return;
            }
            else{
                System.out.println("please type a valid answer");
            }
        }
    }

    public int GetIndexByUsername(String username, ArrayList<Account> AccountsList){
        for(int i = 0; i < AccountsList.size(); i++){
            if(username .equals(AccountsList.get(i).getUsername())){
                return i;
            }
        }
        return -1;
    }
    //---------------------------------------------     CHATTING    --------------------------------------------------------
    public void ChatWithAFriend(Account YourAccount){
        Scanner scan = new Scanner(System.in); // creates a object called scan to scan inputs along the code
        ArrayList<Chat> YourChatList = YourAccount.getYourChats();
        String Friend;
        Chat_Menu:
        while(true){

            if(YourAccount.getYourChats().size() > 0){
                System.out.println("Your Chats:");
                for(int i = 0; i < YourAccount.getYourChats().size(); i++){
                    ArrayList<Account> Members = YourChatList.get(i).getChatMembers(); // Members receive the accounts o the members in the chat
                    if(Members.get(0).getUsername() .equals(YourAccount.getUsername())){
                        Friend = Members.get(1).getUsername();
                    }
                    else{
                        Friend = Members.get(0).getUsername();
                    }
                    System.out.println("Type "+i+" to chat with "+Friend);
                }
            }
            else{
                System.out.println("You have no chats");
                break;
            }
            System.out.println("Type -1 to exit");
            int option = scan.nextInt();
            scan.nextLine();
            if(option == -1){
                return;
            }
            ArrayList<Account> Members = YourChatList.get(option).getChatMembers(); // Members receive the accounts o the members in the chat
            if(Members.get(0).getUsername() .equals(YourAccount.getUsername())){
                Friend = Members.get(1).getUsername();
            }
            else{
                Friend = Members.get(0).getUsername();
            }
            ArrayList<String> Messages = YourChatList.get(option).getMessages();
            System.out.println("CHAT: "+Friend);
            System.out.println("Type 'EXIT CHAT' at any moment to exit");
            for(int i = 0; i < Messages.size(); i++){
                System.out.println(Messages.get(i));
            }
            String YourMessage;
            while(true){ // Chat
                YourMessage = scan.nextLine();
                if(YourMessage .equals("EXIT CHAT")){
                    continue Chat_Menu;
                }
                String TextInChat = (YourAccount.getUsername()+": "+YourMessage);
                Messages.add(TextInChat);
            }
        }
    }


    public Account() {
    }

    public Account (String login, String password, String username, Builder builder){
        this.login = login;
        this.password = password;
        this.username = username;
        this.age = builder.age;
        this.gender = builder.gender;
        this.profession = builder.profession;
        this.maritalStatus = builder.maritalStatus;
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

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public void setAge(int age) {
        this.age = age;
    }

    //builder design pattern
    public class Builder {
        private int age;
        private String gender;
        private String profession;
        private String maritalStatus;

        public Builder withAge(int age){
            this.age = age;
            return this;
        }

        public Builder withGender(String gender){
            this.gender = gender;
            return this;
        }

        public Builder withProfession(String profession){
            this.profession = profession;
            return this;
        }

        public Builder withMaritalStatus(String maritalStatus){
            this.maritalStatus = maritalStatus;
            return this;
        }
    }

}


