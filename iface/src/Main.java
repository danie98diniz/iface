import java.util.*;

public class Main {
//---------------------------------------------     MAIN MENU    -------------------------------------------------------
    public static void main(String[] args) {

        ArrayList<Community> CommunityList = new ArrayList<>();
        ArrayList<Account> AccountsList = new ArrayList<>();// creates a list of accounts
        Scanner scan = new Scanner(System.in); // creates a object called scan to scan inputs along the code
        Main_menu: // saving position to go back to this line of code when needed
        while(true){
            System.out.println("type 0 to exit");   // display options for the user
            System.out.println("type 1 to log in");
            System.out.println("type 2 to create a account");
            int option = scan.nextInt();    // scan input from user
            scan.nextLine();
            if(option == 0){
                return;
            }//exit
            if(option == 1){
                while(true){
                    System.out.println("Please enter your login");
                    String login;
                    login = scan.nextLine();
                    for(int i =0; i < AccountsList.size(); i++){
                        if(login .equals(AccountsList.get(i).getLogin())){
                            while(true){
                                System.out.println("Please enter your password");
                                String password = scan.nextLine();
                                if(password .equals(AccountsList.get(i).getPassword())){
                                    Log_into_Account(i, AccountsList, CommunityList);
                                    continue Main_menu;
                                }
                                System.out.println("Password is incorrect");

                            }
                        }
                    }
                    System.out.println("Login invalid");
                    System.out.println("Type 1 to go back");
                    System.out.println("Type 2 to try again");
                    option = scan.nextInt();
                    scan.nextLine();
                    if(option == 1){
                        continue Main_menu;
                    }
                }
            }//login
            if(option == 2){
                CreateAccount(AccountsList);
            }//account creation
        }
    }
//---------------------------------------------      LOG IN      -------------------------------------------------------
    private static void Log_into_Account(int  YourIndex, ArrayList<Account> AccountsList, ArrayList<Community> CommunityList){
        Scanner scan = new Scanner(System.in); // creates a object called scan to scan inputs along the code
        Login_Menu:
        while(true){
            System.out.println("Welcome "+AccountsList.get(YourIndex).getUsername()+"!");
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
                            String YourName = AccountsList.get(YourIndex).getUsername();
                            AccountsList.get(i).SendFriendRequest(YourName);
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
                FriendRequests(YourIndex, AccountsList);
            }//view friend requests
            else if(option == 3){

                System.out.println("Type 1 to see your chats");
                System.out.println("Type 2 to add a new chat");
                option = scan.nextInt();
                scan.nextLine();
                if(option == 1){
                    ChatWithAFriend(AccountsList.get(YourIndex));
                }
                else if( option == 2){
                    System.out.println("You can chat with friends or others accounts registered");
                    ArrayList<String> FriendsList;
                    FriendsList = AccountsList.get(YourIndex).getFriendsList();

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
                        CreateANewChat(AccountsList.get(YourIndex), AccountsList.get(HisIndex));
                        continue Login_Menu;
                    }
                }//adding a new chat to your chat list
            }//go to chat options
            else if(option == 4){
                AccountEditing(AccountsList.get(YourIndex), AccountsList);
            }//edit account
            else if(option == 5){
                CommunityMenu(AccountsList.get(YourIndex), AccountsList, CommunityList);
            }//go to communities
            else if(option == 6){
                ShowAccountInformation(AccountsList.get(YourIndex), CommunityList);
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
                    if(password .equals(AccountsList.get(YourIndex).getPassword())){
                        AccountTermination(AccountsList.get(YourIndex), AccountsList, CommunityList);
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
    private static void CreateAccount(ArrayList<Account> AccountsList){
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
        Account new_account = new Account(login, password , username );
        AccountsList.add(new_account);
        System.out.println("Account created with success!");
        System.out.println("Done! Exiting..");
    }
//--------------------------------------------- ACCOUNT EDITING  -------------------------------------------------------
    private static void AccountEditing(Account YourAccount, ArrayList<Account> AccountsList){
        Scanner scan = new Scanner(System.in); // creates a object called scan to scan inputs along the code
        int option;
        Editing_Menu:
        while(true){
            System.out.println("Type 0 to go back");
            System.out.println("Type 1 to change your login");
            System.out.println("Type 2 to change your password");
            System.out.println("Type 3 to change your username");
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
        }
    }
//--------------------------------------------- FRIEND REQUESTS --------------------------------------------------------
    private static void FriendRequests(int index, ArrayList<Account> AccountsList){
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
                FriendsList = AccountsList.get(index).getFriendsList();
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
                FriendRequest = AccountsList.get(index).getFriendRequests();
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
                                YourFriendsList = AccountsList.get(index).getFriendsList();
                                YourFriendsList.add(FriendUsername);
                                // adding your username into his friends list
                                ArrayList<String> HisFriendsList;
                                int friend_index;
                                friend_index = GetIndexByUsername(FriendUsername, AccountsList);
                                HisFriendsList = AccountsList.get(friend_index).getFriendsList();
                                HisFriendsList.add(AccountsList.get(index).getUsername());
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
//---------------------------------------------  ADDING A CHAT  --------------------------------------------------------
    private static void CreateANewChat(Account YourAccount, Account HisAccount){
        Chat new_chat = new Chat();
        new_chat.AddMember(YourAccount);
        new_chat.AddMember(HisAccount);
        YourAccount.AddChatToAccount(new_chat);
        HisAccount.AddChatToAccount(new_chat);
        System.out.println("You can now chat with "+HisAccount.getUsername()+"!");
        System.out.println("Go to 'your chats' to begin a conversation");
    }
//---------------------------------------------     CHATTING    --------------------------------------------------------
    private static void ChatWithAFriend(Account YourAccount){
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
//---------------------------------------------   COMMUNITIES   --------------------------------------------------------
    private static void CommunityMenu(Account YourAccount, ArrayList<Account> AccountsList, ArrayList<Community> CommunityList){
        Scanner scan = new Scanner(System.in); // creates a object called scan to scan inputs along the code

        int option;
        ArrayList<Community> YourCommunityList = YourAccount.getYourCommunities();
        Community_Menu:
        while(true){
            System.out.println("type 0 to go back");
            System.out.println("type 1 to create a community");
            System.out.println("type 2 to go to communities you´re in");
            System.out.println("type 3 to manage your communities");
            System.out.println("type 4 to see all communities");

            option = scan.nextInt();
            scan.nextLine();
            if(option == 0){
                return;
            }// return
            else if(option == 1){
                Community new_community = new Community();
                new_community.setOwner(YourAccount);//setting owner of the community
                System.out.println("Give your community a name:");
                String CommunityName;
                name:
                while(true){
                    CommunityName = scan.nextLine();
                    for(int i = 0; i < CommunityList.size(); i++){
                        if(CommunityList.get(i).getCommunity_name() .equals(CommunityName)){
                            System.out.println("This Community name is already taken!");
                            System.out.println("Please choose another name:");
                            continue name;
                        }
                    }
                    break;
                }

                new_community.setCommunity_name(CommunityName);
                System.out.println("Give your community a description");
                System.out.println("Type 'DONE' when ready:");
                String InputFromUser;
                ArrayList<String> Description = new ArrayList<>();
                while(true){
                    InputFromUser = scan.nextLine();
                    if(InputFromUser .equals("DONE")){
                        break;
                    }
                    Description.add(InputFromUser);
                }
                new_community.setDescription(Description);
                new_community.AddMember(YourAccount);
                CommunityList.add(new_community);
                YourAccount.AddCommunityToAccount(new_community);
                YourAccount.AddCommunityYouOwnToAccount(new_community);
                System.out.println("Your community is ready!");
            }//creates a community
            else if(option == 2){
                if(YourCommunityList.size() > 0){
                    System.out.println("COMMUNITIES YOU´RE IN:");
                    for(int i = 0; i < YourCommunityList.size(); i++){
                        System.out.println(i+". "+YourCommunityList.get(i).getCommunity_name());
                    }
                    System.out.println("Choose a community:");
                    System.out.println("Or type -1 to go back");
                    option = scan.nextInt();
                    scan.nextLine();
                    if(option == -1){
                        continue Community_Menu;
                    }
                    else if(option >= 0 && option < YourCommunityList.size()){
                        CommunityLobby(option, YourCommunityList, YourAccount);
                    }
                    else{
                        System.out.println("please type a valid community");
                    }
                }
                else{
                    System.out.println("You´re not in any communities!");
                }
            }//communities you´re in
            else if(option == 3){
                while(true){
                    System.out.println("YOUR OWNED COMMUNITIES:");
                    for(int i = 0; i < YourAccount.getOwnedCommunities().size(); i++){
                        System.out.println(i+". "+YourAccount.getOwnedCommunities().get(i).getCommunity_name());
                    }
                    System.out.println("Choose a community to manage");
                    System.out.println("Or type -1 to exit");
                    while(true){
                        option = scan.nextInt();
                        scan.nextLine();
                        if(option == -1){
                            continue Community_Menu;
                        }
                        if(option >= 0 && option < YourAccount.getOwnedCommunities().size()){
                            ManageYourCommunity(AccountsList, YourAccount.getOwnedCommunities().get(option));
                            continue Community_Menu;
                        }
                        System.out.println("Please choose a valid community");

                    }

                }
            }
            else if(option == 4){
                System.out.println("COMMUNITIES:");
                for(int i = 0; i < CommunityList.size(); i++){
                    System.out.println(i+". "+CommunityList.get(i).getCommunity_name());
                }
                System.out.println("Choose a community!");
                while(true){
                    System.out.println("or type -1 to exit");
                    option = scan.nextInt();
                    scan.nextLine();
                    if(option >= 0 && option < CommunityList.size()){
                        CommunityLobby(option, CommunityList, YourAccount);
                        continue Community_Menu;
                    }
                    else if(option == -1){
                        continue Community_Menu;
                    }
                    else {
                        System.out.println("please choose a valid community");
                    }
                }
            }//show all communities
        }
    }
//-------------------------------------------   COMMUNITY LOBBY    -----------------------------------------------------
    private static void CommunityLobby(int CommunityIndex, ArrayList<Community> CommunityList, Account YourAccount){
        Scanner scan = new Scanner(System.in); // creates a object called scan to scan inputs along the code

        Community CommunityToShow = CommunityList.get(CommunityIndex);

        System.out.println("-----------"+CommunityToShow.getCommunity_name()+"----------");
        System.out.println(CommunityToShow.getDescription());
        System.out.println("Owner: "+CommunityToShow.getOwner().getUsername());
        System.out.println("Members:");
        int member = 0;
        for(int i = 0; i < CommunityToShow.getCommunityMembers().size(); i++){
            if(YourAccount.getUsername() .equals(CommunityToShow.getCommunityMembers().get(i).getUsername())){
                member = 1;
            }
            System.out.println(i+". "+CommunityToShow.getCommunityMembers().get(i).getUsername());
        }
        System.out.println("type 1 to exit:");
        if(member == 0){
            System.out.println("type 2 to request to join this community");
        }
        if(member == 1){
            System.out.println("type 3 to see Community chat");
        }
        int option = scan.nextInt();
        scan.nextLine();
        if(option == 2 && member == 0){
            CommunityToShow.getMemberRequest().add(YourAccount);
            System.out.println("Your request has been sent!");
        }
        else if(member == 1 && option == 3){
            CommunityChatting(YourAccount, CommunityList.get(CommunityIndex));
        }
    }
//------------------------------------------ MANAGE YOUR COMMUNITY -----------------------------------------------------
    private static void ManageYourCommunity(ArrayList<Account> AccountsList, Community CommunityToManage){
        Scanner scan = new Scanner(System.in); // creates a object called scan to scan inputs along the code
        Manage_menu:
        while(true){
            System.out.println("type 0 to exit");
            System.out.println("type 1 to edit the Community name");
            System.out.println("type 2 to edit the Community description");
            System.out.println("type 3 to manage members");
            int option = scan.nextInt();
            scan.nextLine();
            if(option == 0){
                break;
            }//break
            else if(option == 1){
                System.out.println("Please type a new Community name");
                String new_name = scan.nextLine();
                CommunityToManage.setCommunity_name(new_name);
                System.out.println("Community name updated!");
            }//edit community name
            else if(option == 2){
                System.out.println("Please type a new Community description");
                //edit community description
                ArrayList<String> new_description = new ArrayList<>();
                String sentence;
                System.out.println("Type 'DONE' when its ready");
                while(true){
                    sentence = scan.nextLine();
                    if(sentence .equals("DONE")){
                        CommunityToManage.setDescription(new_description);
                        System.out.println("Community description updated!");
                        continue Manage_menu;

                    }
                    new_description.add(sentence);
                }
            }//edit community description
            else if(option == 3){
                Manage_members_menu:
                while(true){
                    System.out.println("type 0 to go back");
                    System.out.println("type 1 to see/remove members");
                    System.out.println("type 2 to see members requests");

                    option = scan.nextInt();
                    scan.nextLine();
                    if(option == 0){
                        continue Manage_menu;
                    }
                    else if(option == 1){
                        while(true){
                            System.out.println("Members of "+CommunityToManage.getCommunity_name());
                            System.out.println("Owner (you)");
                            for(int i = 1; i < CommunityToManage.getCommunityMembers().size(); i++){
                                System.out.println(i+". "+CommunityToManage.getCommunityMembers().get(i).getUsername());
                            }
                            System.out.println("type the number of the member you want to remove");
                            System.out.println("or type -1 to exit");
                            option = scan.nextInt();
                            scan.nextLine();
                            if(option == -1){
                                continue Manage_members_menu;
                            }
                            else if(option > 0 && option < CommunityToManage.getCommunityMembers().size()){
                                System.out.println(CommunityToManage.getCommunityMembers().get(option).getUsername()+" is no longer part of your community");
                                int index = GetCommunityIndexInYourCommunities(CommunityToManage, CommunityToManage.getCommunityMembers().get(option).getYourCommunities());
                                CommunityToManage.getCommunityMembers().get(option).getYourCommunities().remove(index);
                                CommunityToManage.getCommunityMembers().remove(option);
                                //remove a member
                            }
                        }
                        //see members and kick
                    }//see members and remove
                    else if(option == 2){
                        if(CommunityToManage.getMemberRequest().size() > 0){
                            while(true){
                                for(int i = 0; i < CommunityToManage.getMemberRequest().size(); i++){
                                    System.out.println(i+". "+CommunityToManage.getMemberRequest().get(i).getUsername()+" wants to join your community!");
                                }
                                System.out.println("Choose someone to accept or reject");
                                System.out.println("or type -1 to go back");
                                option = scan.nextInt();
                                scan.nextLine();
                                if(option == -1){
                                    continue Manage_members_menu;
                                }
                                else if(option >= 0 && option < CommunityToManage.getMemberRequest().size()){
                                    System.out.println("Do you want "+CommunityToManage.getMemberRequest().get(option).getUsername()+" to join the community?");
                                    System.out.println("type 1 to accept or 2 to reject");
                                    int choice = scan.nextInt();
                                    scan.nextLine();
                                    if(choice == 1){
                                        CommunityToManage.getCommunityMembers().add(CommunityToManage.getMemberRequest().get(option));
                                        System.out.println(CommunityToManage.getMemberRequest().get(option).getUsername()+" is now part of your community");
                                        CommunityToManage.getMemberRequest().get(option).getYourCommunities().add(CommunityToManage);
                                        CommunityToManage.getMemberRequest().remove(option);
                                    }
                                    else if(choice == 2){
                                        System.out.println(CommunityToManage.getMemberRequest().get(option).getUsername()+" will not join your community");
                                        CommunityToManage.getMemberRequest().remove(option);
                                    }
                                    continue Manage_members_menu;
                                }
                                else{
                                    System.out.println("please type a valid input");
                                    continue Manage_members_menu;
                                }
                            }

                        }
                        else{
                            System.out.println("Your Community has no requests at this moment.");
                            continue Manage_menu;
                        }
                    }//see members requests
                }
            }//manage members
        }
    }
//------------------------------------------  COMMUNITY CHATTING  ------------------------------------------------------
    private static void CommunityChatting(Account YourAccount, Community CommunityToChat){
        Scanner scan = new Scanner(System.in); // creates a object called scan to scan inputs along the code

        CommunityToChat.getCommunityChat().setChatMembers(CommunityToChat.getCommunityMembers());//chat members receive community members
        ArrayList<String> Messages = CommunityToChat.getCommunityChat().getMessages();
        System.out.println("CHAT: "+CommunityToChat.getCommunity_name());
        System.out.println("Type 'EXIT CHAT' at any moment to exit");
        for(int i = 0; i < Messages.size(); i++){
            System.out.println(Messages.get(i));
        }
        String YourMessage;
        while(true){ // Chat
            YourMessage = scan.nextLine();
            if(YourMessage .equals("EXIT CHAT")){
                break;
            }
            String TextInChat = (YourAccount.getUsername()+": "+YourMessage);
            Messages.add(TextInChat);
        }
    }
//--------------------------------------- SHOW (RECOVER) ACCOUNT INFO --------------------------------------------------
    private static void ShowAccountInformation(Account YourAccount, ArrayList<Community> CommunityList){
        Scanner scan = new Scanner(System.in); // creates a object called scan to scan inputs along the code

        Info_Menu:
        while(true){
            System.out.println("("+YourAccount.getUsername()+") ACCOUNT INFORMATION:");
            System.out.println("LOGIN: "+YourAccount.getLogin());
            System.out.println("USERNAME: "+YourAccount.getUsername());
            System.out.println("type 1 to see friends list");
            System.out.println("type 2 to see your chats");
            System.out.println("type 3 to see communities you´re in");
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
                            CommunityLobby(choice, CommunityList, YourAccount);
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
//------------------------------------------  TERMINATE ACCOUNT  -------------------------------------------------------
    private static void AccountTermination(Account YourAccount, ArrayList<Account> AccountsList, ArrayList<Community> CommunityList){
        QuitAllCommunities(YourAccount, CommunityList);
        DeleteYourChats(YourAccount, AccountsList);
        DeleteYourFriends(YourAccount, AccountsList);
        for(int i = 0; i < AccountsList.size(); i++){
            if(YourAccount == AccountsList.get(i)){
                AccountsList.remove(i);
            }
        }
    }
//----------------------------------------  OTHER SMALL FUNCTIONS  -----------------------------------------------------
    private static int GetIndexByUsername(String username, ArrayList<Account> AccountsList){
        for(int i = 0; i < AccountsList.size(); i++){
            if(username .equals(AccountsList.get(i).getUsername())){
                return i;
            }
        }
        return -1;
    }
    private static int GetCommunityIndexInYourCommunities(Community CommunityToIndex, ArrayList<Community> YourCommunities){
        int index;
        for(int i = 0; i < YourCommunities.size(); i++){
            if(CommunityToIndex.getCommunity_name() .equals(YourCommunities.get(i).getCommunity_name())){
                index = i;
                return index;
            }
        }
        return -1;
    }
    private static String GetWhoYouTalkingTo(Chat YourChat, Account YourAccount){
        String Username;
        if(YourChat.getChatMembers().get(0).getUsername() .equals(YourAccount.getUsername())){
            Username = YourChat.getChatMembers().get(1).getUsername();
        }
        else{
            Username = YourChat.getChatMembers().get(0).getUsername();
        }
        return Username;
    }
    private static void QuitAllCommunities(Account YourAccount, ArrayList<Community> CommunityList){
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
    private static void DeleteYourChats(Account YourAccount, ArrayList<Account> AccountsList){
        Chat ChatToUse;
        for(int i = 0; i < YourAccount.getYourChats().size(); i++){
            ChatToUse = YourAccount.getYourChats().get(i);
            String UsernameOfFriend = GetWhoYouTalkingTo(ChatToUse, YourAccount);
            int index = GetIndexByUsername(UsernameOfFriend, AccountsList);
            Account FriendAccount;
            FriendAccount = AccountsList.get(index);
              for(int j = 0; j < FriendAccount.getYourChats().size(); j++){
                  String YourName = GetWhoYouTalkingTo(FriendAccount.getYourChats().get(j), FriendAccount);
                  if(YourName .equals(YourAccount.getUsername())){
                      FriendAccount.getYourChats().remove(j);
                  }
              }
        }
    }
    private static void DeleteYourFriends(Account YourAccount, ArrayList<Account> AccountsList){
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





}