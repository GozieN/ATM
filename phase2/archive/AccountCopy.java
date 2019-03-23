//package archive;
//
//import phase2.FundHolders.Account;
//import FundTransfers.*;
//
//import java.io.Serializable;
//import java.util.*;
//
//
//public abstract class AccountCopy implements Serializable {
//    private int accountNum = 0;
//    private String holderName;
//
//    private double balance;
//    //public String accountType; NOT NEEDED AS DO DIRECTLY new ChequingAccount(...);
//    private ArrayList<String> history;
//
//    /**
//     * Account class constructor
//     * @param accountNum Number used to identify a specific account
//     * @param holderName Name of holder of the account
//     * @param balance Amount of money found in account
//     *
//     */
//    public AccountCopy(int accountNum, String holderName, double balance){ //, String accountType){ NOT NEEDED
//        history = new ArrayList<String>();
//        //this.accountType = accountType;
//        this.accountNum = accountNum;
//        this.holderName = holderName;
//        this.balance = balance;
//    }
//
//    /**
//     * Get name of account holder
//     * @return String of holder's name
//     */
//    public String getHolderName(){
//        return holderName;
//    }
//
//    /**
//     * Get number of account
//     * @return Int for account's number
//     */
//    public int getAccountNum(){
//        return accountNum;
//    }
//
//    /**
//     * Get the history of the account's transactions
//     * @return The list of transaction history
//     */
//    public ArrayList<String> getHistory(){
//        return history;
//    }
//
//    /**
//     * Add new transactions to history
//     * @param eeee Something to update the history with
//     */
//    public void updateHistory(String eeee){
//        history.add("STRING WITH TRANSACTION + AMOUNT");
//    }
//    //-------------------------------------------------------------
//    /**
//     * Deposit amount into account
//     * @param amount Amount of money to deposit
//     */
//    public void depositToAccount(double amount) {
//        this.balance = balance+amount;
//        history.add("deposit " + amount);
//        System.out.println("Deposit successful, Account: " + this.getAccountNum() +
//                " now has an increased balance of: " + this.getBalance() + "CAD$");
//    }
//
//    /**
//     * Transfer funds from sender to receiver
//     * @param amount Amount of money to be transferred
//     * @param receiverAccount Account which money will be transferred to
//     */
//    public void transfer(double amount, Account receiverAccount) {
//        this.withdraw(amount);
//        receiverAccount.setBalance(receiverAccount.getBalance()+amount);
//        history.add("transfer from "+accountNum+" to "+receiverAccount.getAccountNum()+" for "+amount);
//    }
//
//    public void withdraw(double amt){
//        this.balance=balance-amt;
//    }
//
//    /**
//     * Set balance of account
//     * @param balance Total amount of money in account
//     */
//    public void setBalance(double balance){
//        this.balance = balance;
//    }
//
//    /**
//     * Get balance of account
//     * @return Int for balance of account
//     */
//    public double getBalance(){
//        return this.balance;
//    }

}