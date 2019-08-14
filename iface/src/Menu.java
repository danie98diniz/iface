import java.util.ArrayList;
import java.util.Scanner;

public class Menu { //singleton design pattern

    private static Menu ourInstance = new Menu();

    public static Menu getInstance() {
        return ourInstance;
    }

    public void RunMenu (){

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
                                    Account newAccount = AccountsList.get(i);

                                    newAccount.Log_into_Account(newAccount, AccountsList, CommunityList);
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
                Account newAccount = new Account();
                newAccount.CreateAccount(AccountsList);
            }//account creation
        }
    }

    private Menu() {
    }
}
