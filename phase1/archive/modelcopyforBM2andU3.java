package phase1.archive;

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

public class modelcopyforBM2andU3 {
    private BankManager BM = new BankManager("BMuser", "BMpass");
    private ATM atm = new ATM();
    private ArrayList<String> userUsernames = new ArrayList<>();
    private ArrayList<String> userPasswords = new ArrayList<>();

    // model constructor
    public modelcopyforBM2andU3() {

    }

    //NEED TO SET THE TRANSACTION ATM TO THIS ATM
    public ATM getAtm() {
        return atm;
    }

    public void menuU3(int i, User user) {
        // options: 1. login to existing user, 2. request creation of new user, e. exit
        System.out.println("Enter 'I' to View Info, Enter 'T' to Perform a Transaction, or Enter 'A' to Request a New Account");
        Scanner menuOption = new Scanner(System.in);
        String optionIn = menuOption.next();
        ArrayList<Account> accounts = user.getAccountsCreated(); // NEED TO FIGURE OUT HOW TO STORE THE ACCOUNTS FROM BEFORE!!!!
        if (optionIn == "I") {
            user.viewInfo();
        } else if (optionIn == "T") {
            System.out.println("Enter '' to Withdraw from Account, Enter 'WM' to Withdraw from ATM, Enter 'DA' to Deposit into Account, Enter 'DM' to Deposit into ATM, Enter 'CA' to Deposit Cheque into ATM, Enter 'TA' to Transfer from Account to Account, Enter 'PB' to Pay Bill, A to create new Account: ");
            Scanner transOption = new Scanner(System.in);
            String transIn = transOption.next();
            System.out.println("Enter the amount: \n");
            Scanner amtOption = new Scanner(System.in);
            double amt = Double.parseDouble(amtOption.next());
            Transactions transactions = new Transactions(new ChequingAccount(100, "me", 1000, true)); // FIX THIS!!! JUST TESTING
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
                Account receiverAccount = new SavingsAccount(accNum, holder, balance); // NEED TO CHANGE HOW TO CHANGE TRANSACTIONS
                transactions.transfer((int) amt, receiverAccount);
            } else if (transIn == "PB") {
                transactions.payBill(amt);
            } else if (optionIn == "A") {
                user.addToAccountsCreated(new ChequingAccount(101, "me", 1000, true)); // NEED TO CHANGE AFTER TESTING
            }
        }
    }

    public void menuBM2(User user){
        System.out.println("Press 1 to view info or 2 to perform a transacation");
        Scanner option = new Scanner(System.in);
        String optionIn = option.next();
        if (optionIn.toLowerCase()=="e"){
            exit();
        }
        else if (optionIn.toLowerCase()=="b"){
            back(2, user);
        }
        else if (Integer.parseInt(optionIn)==1){
            user.viewInfo();
        }
        else if (Integer.parseInt(optionIn)==2){
            Account acc = new SavingsAccount(100, "he",100); // FOR TESTING
            Transactions tran = new Transactions(acc);
        }
    }

    /*
     * JUST TO SHOW OFF WHAT IT SHOULD LOOK LIKE DIDN'T CODE YET
     */
    public void back(int i, User user){
        if (i==3){
            menuBM2(user);
        }
        else if (i==2){
            menuBM1();
        }
        else if (i==1){
            menuOperatorSelect();
        }
    }


    public void exit(){
        menuOperatorSelect();
    }
}