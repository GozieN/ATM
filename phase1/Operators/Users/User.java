package phase1.Operators.Users;

import FundHolders.*;
import phase1.Operators.Operator;
import phase1.Operators.Workers.Manager;

import java.util.*;


public class User extends Observable implements OperatorUser {
    private static ArrayList<User> userdatabase = new ArrayList<User>();
    private static int numUsers = 0;
    private String username;
    private String password;
    private Credit cca = null;
    private Credit loca = null;
    private ChequingAccount ca = null;
    private SavingsAccount sa = null;
    private ArrayList<Account> AccountsCreated = new ArrayList<Account>();
    private Manager bmObserver = new Manager();


    // user constructor (BM use ONLY in console)
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        numUsers++;
        userdatabase.add(this);
    }

    // setpassword method (BM use ONLY in console)
    public void setInitialPassword(String newPassword) {
        this.password = newPassword;
        setChanged();
        notifyObservers(newPassword);
        clearChanged();
    }

    // changepassword method
    public String changePassword(String currentPassword, String newPassword) {
        if (currentPassword.equals(this.password)) {
            this.password = newPassword;
            return "your password has successfully been changed";
        } else {
            return "you have entered the wrong current password. unable to change password";
        }
    }
    //[Angela]
    //GOZIE - OBSERVER PATTERN
     public void requestAccountCreation(Account account) {
         setChanged();
         notifyObservers(account);
         clearChanged();
     }
     // must interact with bankmanager to do this
    // how to implement this? maybe::
    // requestnotifier setter method in BM class, user method calls it
    // user method passes username information and account type being requested to BM setter ?
    // this information would go in output.txt and the accounts
    // would be created the next time the program is launched (the next time input.txt is read)
    // requires information going from output.txt to input.txt

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setObserver(Manager observer) {
        this.bmObserver = observer;
    }

    public ArrayList<Account> getAccountsCreated() {
        return AccountsCreated;
    }

    public void setAccountsCreated(ArrayList<Account> accountsCreated) {
        AccountsCreated = accountsCreated;
    }
    public void addToAccountsCreated(Account account) {
        AccountsCreated.add(account);
    }

    public void singleAccountSummary(Account account) {
        System.out.println("Account holder: " + this.username + " "
                + "DATE AND TIME " +
                "" + "Account summary:" + account.getAccountType(account) +"Account Number: "
                + account.getAccountNum() + " contains: " + account.getBalance() + "currency");}

    // user will not have to input any parameters (direct call)
    //CONSIDER OPTION OF THIS VIEW

    public String viewInfo(){

        int totalDebitAmount = 0;
        int totalCreditAmount = 0;

        String s = "Account holder: " + this.username + " Report of FundHolders:";
        for(int i = 0; i < AccountsCreated.size(); i++){
            s += AccountsCreated.get(i).getAccountType(AccountsCreated.get(i)) + "Number: " + AccountsCreated.get(i).getAccountNum() + "\n" +
                    " created on: GETDATEOFCREATION" + "\n Current Balance:" +
                    AccountsCreated.get(i).getBalance() + " Most Recent Transaction: " + "BM GET MOSTRECENTTRANSACTION";
            if (AccountsCreated.get(i) instanceof Debit){
                totalDebitAmount += AccountsCreated.get(i).getBalance();
            }else{
                totalCreditAmount += AccountsCreated.get(i).getBalance();}
        }
        s += "Net Total: " + (totalDebitAmount - totalCreditAmount);
        return s;
    }

    // user will not have to input any parameters (direct call)

    public void viewBalance(Account account) {
        System.out.println("Account: " + account.getAccountNum() + " has a balance of: " + account.getBalance());
    }
}

    // user input parameters: account num/type

//    public void transfer(int amount, Account from, Account to) {
//        from.setBalance(from.getBalance() - amount);
//        to.setBalance(to.getBalance() + amount);
//
//        //[Angela]
//        try {
//            PrintStream originalOut = System.out;
//            PrintStream fileOut = new PrintStream("/.Outgoing.txt");
//            System.setOut(fileOut);
//            originalOut.println("[Amount] transferred to [Receiver Account]");
//            System.setOut(originalOut);
//        } catch (FileNotFoundException ex) {ex.printStackTrace();}
//
//    }
//
//    public void withdraw(int amount, Account account) {
//
//        account.withdraw(amount);
//        System.out.println("Withdrawal successful, Account: " + account.getAccountNum() +
//                " now has a decreased balance of: " + account.getBalance() + "currency");
//    }
//
//    public void deposit(int amount, Account account) {
////deposit money into their account by entering a cheque
//// or cash into the machine (This will be simulated by individual lines
//// in an input file called deposits.txt. You can decide the format of the file.
//// This will increase their balance.)
//        account.deposit(amount);
//        System.out.println("Deposit successful, Account: " + account.getAccountNum() +
//                " now has an increased balance of: " + account.getBalance() + "currency");
//    }
////CONSIDER DATA CLUMPING CODE SMELL - AVOID
//    public void eTransfer(int amount, Account from, Account to) {
//        from.setBalance(from.getBalance() - amount);
//        to.setBalance(to.getBalance() + amount);}
//
//    public void payBill(int amount, Account from, Account to) {
//        from.setBalance(from.getBalance() - amount);
//        to.setBalance(to.getBalance() + amount);
//        //CHECK specs
//
//    }}