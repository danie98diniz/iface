import java.util.ArrayList;
import java.util.Scanner;

public class Community {
    private String Community_name;
    private ArrayList<String> Description = new ArrayList<>();
    private ArrayList<Account> CommunityMembers = new ArrayList<>();
    private Account Owner;
    private ArrayList<Account> MemberRequest = new ArrayList<>();
    private Chat CommunityChat = new Chat();

    //---------------------------------------------   COMMUNITIES   --------------------------------------------------------
    public void CommunityMenu(Account YourAccount, ArrayList<Account> AccountsList, ArrayList<Community> CommunityList){
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
                        Community CommunityToShow = CommunityList.get(option);
                        CommunityToShow.CommunityLobby(CommunityToShow, YourCommunityList, YourAccount);
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
                            Community newCommunity = new Community();
                            newCommunity.ManageYourCommunity(AccountsList, YourAccount.getOwnedCommunities().get(option));
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
                        Community CommunityToShow = CommunityList.get(option);
                        CommunityToShow.CommunityLobby(CommunityToShow, CommunityList, YourAccount);
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

    //------------------------------------------ MANAGE YOUR COMMUNITY -----------------------------------------------------
    public void ManageYourCommunity(ArrayList<Account> AccountsList, Community CommunityToManage){
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

    //-------------------------------------------   COMMUNITY LOBBY    -----------------------------------------------------
    public void CommunityLobby(Community CommunityToShow, ArrayList<Community> CommunityList, Account YourAccount){
        Scanner scan = new Scanner(System.in); // creates a object called scan to scan inputs along the code


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
            CommunityToShow.CommunityChatting(YourAccount, CommunityToShow);
        }
    }

    //------------------------------------------  COMMUNITY CHATTING  ------------------------------------------------------
    public void CommunityChatting(Account YourAccount, Community CommunityToChat){
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

    public int GetCommunityIndexInYourCommunities(Community CommunityToIndex, ArrayList<Community> YourCommunities){
        int index;
        for(int i = 0; i < YourCommunities.size(); i++){
            if(CommunityToIndex.getCommunity_name() .equals(YourCommunities.get(i).getCommunity_name())){
                index = i;
                return index;
            }
        }
        return -1;
    }


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
