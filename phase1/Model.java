package phase1;

import phase1.FundHolders.*;
import phase1.Operators.*;
import phase1.FundTransfers.*;

import java.io.*;
import java.util.*;

 public final class Model {
    private BankManager BM = new BankManager("BM12345", "BMpassword");
    private ATM atm = new ATM();
    private ArrayList<String> userUsernames = new ArrayList<>();
    private ArrayList<String> userPasswords = new ArrayList<>();

    // model constructor
    public Model() {

    }
//NEED TO SET THE TRANSACTION ATM TO THIS ATM
     public ATM getAtm() {
         return atm;
     }

     //NEED TO HAVE AN OPTION TO DEPOSIT A CHEQUE - CALLS THE DEPOSIT CHEQUE METHOD IN TRANSACTIONS

     /**
      * Menu for in person user
      */
    public void menuOperatorSelect() {
        // options: 1. bankmanager, 2. normal user
        System.out.println("enter 1 for bankmanager \n" +
                "enter 2 for normal user");
        Scanner numberScan = new Scanner(System.in);
        while (numberScan.hasNext()) {
            String numberIn = numberScan.next();
            if (numberIn.equals("1")) {
                // BM username, password check
                System.out.println("enter your username");
                Scanner usernameScan = new Scanner(System.in);
                String usernameIn = usernameScan.next();
                if (usernameIn.equals(this.BM.getUsername())) {
                    System.out.println("enter your password");
                    Scanner passwordScan = new Scanner(System.in);
                    String passwordIn = passwordScan.next();
                    if (passwordIn.equals(this.BM.getPassword())) {
                        // TODO: send to next BM menu
                    } else {
                        System.out.println("wrong password. enter your password");
                    }
                } else {
                    System.out.println("wrong username. enter your username");
                }
            } else if (numberIn.equals("2")) {
                // options: 1. login to existing user, 2. request creation of new user, "exit". exit
                System.out.println("enter 1 to login to existing user \n" +
                        "enter 2 to request creation of new user \n" +
                        "enter 'exit' to exit");
                Scanner numberScan2 = new Scanner(System.in);
                while (numberScan2.hasNext()) {
                    String numberIn2 = numberScan2.next();
                    if (numberIn2.equals("1")) {
                        // user username, password check
                        System.out.println("enter your username");
                        Scanner usernameScan = new Scanner(System.in);
                        String usernameIn = usernameScan.next();
                        int index = -1;
                        for (String username : this.userUsernames) {
                            index += 1;
                            if (username.equals(usernameIn)) {
                                System.out.println("enter your password");
                                Scanner passwordScan = new Scanner(System.in);
                                String passwordIn = passwordScan.next();
                                if (passwordIn.equals(this.userPasswords.get(index))) {
                                    // TODO: send to next user menu
                                } else {
                                    System.out.println("wrong password. enter your password");
                                }
                            }
                            System.out.println("wrong username. enter your username");
                        }
                    } else if (numberIn2.equals("2")) {
                        System.out.println("enter a new username");
                        Scanner newUsernameScan = new Scanner(System.in);
                        String newUsernameIn = newUsernameScan.next();
                        if (!(this.userUsernames.contains(newUsernameIn))) {
                            this.userUsernames.add(newUsernameIn);
                            System.out.println("enter a new password");
                            Scanner newPasswordScan = new Scanner(System.in);
                            String newPasswordIn = newPasswordScan.next();
                            this.userPasswords.add(newPasswordIn);
                            BM.createUser(newUsernameIn, newPasswordIn);
                            System.out.println("your user creation has been requested");
                        }
                    } else if (numberIn2.equals("exit")) {
                        // returns to previous screen
                        // deeper menus should "log off" and return to first menu (menuoperatorselect)
                        menuOperatorSelect();
                    }
                }
            }
        }

    // method ___

    // scanner: reads user inputs and translates it into method calls
    // methods would deal with what is called

    public static void updateDate(String date, File f) throws IOException{
        FileWriter fw = new FileWriter(f);
        fw.write(date);
        fw.close();
    }

    public static void main(String[] args)  {

        // Upon starting the program, read a file which contains the only instance of Bank Manager. (Everything can be
        // accessed from here
        // try
        // catch
        // BankManger BM = the one read from the file

        // menus
        // menu options

        try{
            File f = new File("./src/date.txt");
            updateDate("01012019", f);

        } catch(IOException e){}

        Model model = new Model();
        model.menuOperatorSelect();
    }
}