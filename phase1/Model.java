package phase1;

import phase1.FundHolders.ATM;
import phase1.FundHolders.Account;
import phase1.FundHolders.ChequingAccount;
import phase1.FundHolders.SavingsAccount;
import phase1.FundTransfers.Transactions;
import phase1.Operators.BankManager;
import phase1.Operators.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Model {
   private BankManager BM = new BankManager("BMuser", "BMpass");
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
       ATM atm = new ATM();
       System.out.println("enter 1 for bankmanager \n" +
               "enter 2 for normal user");
       Scanner numberScan = new Scanner(System.in);
       while (numberScan.hasNext()) {
           String numberIn = numberScan.next();
           if (numberIn.equals("1")) {
               System.out.println("enter your username");
               Scanner usernameScan = new Scanner(System.in);
               while (usernameScan.hasNext()) {
                   String usernameIn = usernameScan.next();
                   if (usernameIn.equals(this.BM.getUsername())) {
                       System.out.println("enter your password");
                       Scanner passwordScan = new Scanner(System.in);
                       while (passwordScan.hasNext()) {
                           String passwordIn = passwordScan.next();
                           if (passwordIn.equals(this.BM.getPassword())) {
                               // TODO: send to next BM menu
                           } else {
                               System.out.println("wrong password. enter your password");
                           }
                       }
                   } else {
                       System.out.println("wrong username. enter your username");
                   }
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
                       int index = 0;
                       for (String username : this.userUsernames) {
                           index += 1;
                           if (username.equals(usernameIn)) {
                               System.out.println("enter your password");
                               Scanner passwordScan = new Scanner(System.in);
                               String passwordIn = passwordScan.next();
                               if (passwordIn.equals(this.userPasswords.get(index))) {
                                   User user = new User(usernameIn, passwordIn);
                                   System.out.println("Enter 'I' to View Info, Enter 'T' to Perform a Transaction, or Enter 'A' to Request a New Account");
                                   Scanner menuOption = new Scanner(System.in);
                                   String optionIn = menuOption.next();
                                   ArrayList<Account> accounts = user.getAccountsCreated(); // NEED TO FIGURE OUT HOW TO STORE THE ACCOUNTS FROM BEFORE!!!!
                                   if (optionIn == "I") {
                                       user.viewInfo();
                                   }
                                   else if (optionIn == "T") {
                                       System.out.println("Enter 'WA' to Withdraw from Account, Enter 'WM' to Withdraw from ATM, Enter 'DA' to Deposit into Account, Enter 'DM' to Deposit into ATM, Enter 'CA' to Deposit Cheque into ATM, Enter 'TA' to Transfer from Account to Account, Enter 'PB' to Pay Bill, A to create new Account: ");
                                       Scanner transOption = new Scanner(System.in);
                                       String transIn = transOption.next();
                                       System.out.println("Enter the amount: \n");
                                       Scanner amtOption = new Scanner(System.in);
                                       double amt = Double.parseDouble(amtOption.next());
                                       Transactions transactions = new Transactions(new ChequingAccount(100, "me", 1000, true )); // FIX THIS!!! JUST TESTING
                                       if (transIn == "WA") {
                                           transactions.withdrawFromAccount(amt);
                                       } else if (transIn == "WM") {
                                           transactions.withdrawFromATM(atm, (int) amt);
                                       } else if (transIn == "DA") {
                                           transactions.depositToAccount(amt);
                                       } else if (transIn == "DM") {
                                           transactions.depositIntoATM(atm, (int) amt);
                                       } else if (transIn == "CA") {
                                           transactions.depositChequeToAccount(amt);
                                       } else if (transIn == "TA") { // SENDING TO AN ACC NEEDS TO BE ALTERED, TOO MUCH INFO NEEDED
                                           System.out.println("Enter the Account Number of the Receiving Account: ");
                                           Scanner accN = new Scanner(System.in);
                                           int accNum = Integer.parseInt(accN.next());
                                           System.out.println("Enter the Holder Name of the Receiving Account: ");
                                           Scanner holderN = new Scanner(System.in);
                                           String holder = holderN.next();
                                           System.out.println("Enter the Balance of the Receiving Account: ");
                                           Scanner balanceN = new Scanner(System.in);
                                           double balance = Double.parseDouble(balanceN.next());
                                           System.out.println("Enter the Type of the Receiving Account: ");
                                           Scanner typeS = new Scanner(System.in);
                                           String type = typeS.next();
                                           Account receiverAccount=new SavingsAccount(accNum, holder, balance); // NEED TO CHANGE HOW TO CHANGE TRANSACTIONS
                                           transactions.transfer((int) amt, receiverAccount);
                                       } else if (transIn == "PB") {
                                           transactions.payBill(amt);
                                       } else if (optionIn == "A") {
                                           user.addToAccountsCreated(new ChequingAccount(101, "me", 1000, true)); // NEED TO CHANGE AFTER TESTING
                                       }
                                   }
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
                           BM.getUsers().get(BM.getUsers().size()).setBM(BM);
                       }
                   } else if (numberIn2.equals("exit")) {
                       // returns to previous screen
                       // deeper menus should "log off" and return to first menu (menuoperatorselect)
                       menuOperatorSelect();
                   }
               }
           } else {
               System.out.println("that is not an option \n" +
                       "enter 1 for bankmanager \n" +
                       "enter 2 for user");
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
           DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddmmyyyy HH:mm:ss");
           LocalDateTime now = LocalDateTime.now();
           updateDate(dtf.format(now), f);

       } catch(IOException e){}

       Model model = new Model();
       model.menuOperatorSelect();
   }
}