package phase2.FundHolders;

import phase2.FundTransfers.*;
import phase2.Operators.*;


import java.io.Serializable;
import java.util.*;


public abstract class Account implements java.io.Serializable {
    private static int accountNum = 0;
    private String holderName;
    private User user;
    private double balance;
    public String accountType;
    private Transactions transactionsInstance;
    private ArrayList<Transactions> history;

    /**
     * Account class constructor
     * @param accountHolder holder of the account
     * @param accountType Type of account: Credit Card, Line of Credit, Chequing, or Savings
     */
    public Account(User accountHolder, String accountType){
        history = new ArrayList<Transactions>();
        this.user = accountHolder;
        this.accountType = accountType;
        this.accountNum++;
        this.transactionsInstance = transactionsInstance;
        this.holderName = accountHolder.getUsername();
    }

    /**
     * Get name of account holder
     * @return String of holder's name
     */
    public String getHolderName(){
        return holderName;
    }

    /**
     * Set the transaction instance of the account. Should only be used by the bank manager upon account
     * creation by user request.
     */
    public void setTransactionsInstance() {
        this.transactionsInstance = new Transactions(this);
    }

    /**
     * Get the transaction instance of the account. Should only be used by the bank manager upon account
     * creation by user request.
     */
    public Transactions getTransactionsInstance() {
        return this.transactionsInstance;
    }

    /**
     * Set the transaction instance of the account. This method should only every be called by the BM when it
     * creates a requested account
     */
    public void Transactions () {
        this.transactionsInstance = new Transactions(this);
    }



    /**
     * Get number of account
     * @return Int for account's number
     */
    public int getAccountNum(){
        return accountNum;
    }

    /**
     * Get type of account
     * @return String of account type
     */
    public String getAccountType() { return accountType; }

    /**
     * Get the history of the account's transactions
     * @return The list of transaction history
     */
    public ArrayList<Transactions> getHistory(){
        return history;
    }

    /**
     * Change actions performed in account history
     */
    public void alterHistory(){
        int lastElementIndex = history.size() - 1;
        if (history.get(lastElementIndex).getLastAction().equals("bill")){
            System.out.println("Sorry, your last action could not be reversed as you payed a bill");
    }else{
            if (history.get(lastElementIndex).getLastAction().equals("transfer")) {
                history.get(lastElementIndex).depositToAccount(history.get(lastElementIndex).getLastAmount());
                history.get(lastElementIndex).receiverBalanceAlterIncrease(history.get(lastElementIndex).getLastAmount());
            } else if (history.get(lastElementIndex).getLastAction().equals("withdraw")) {
                history.get(lastElementIndex).depositToAccount(history.get(lastElementIndex).getLastAmount());
            }else if (history.get(lastElementIndex).getLastAction().equals("deposit")){
                history.get(lastElementIndex).withdrawFromAccount(history.get(lastElementIndex).getLastAmount());
            }
            history.remove(lastElementIndex);
            System.out.println(getHolderName() + ", The last action that you performed a" + history.get(lastElementIndex).getLastAction()
                    + " has been reversed upon your request.");
        }
    }

    /**
     * Add new transactions to history
     * @param transactionInfo Variables that make up the Transactions class
     */
    public void updateHistory(Transactions transactionInfo){
        history.add(transactionInfo);
    }

    /**
     * Set balance of account
     * @param balance Total amount of money in account
     */
    public void setBalance(double balance){
        this.balance = balance;
    }

    /**
     * Get balance of account
     * @return Int for balance of account
     */
    public double getBalance(){
        return this.balance;
    }
}