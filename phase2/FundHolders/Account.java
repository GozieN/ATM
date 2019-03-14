package phase2.FundHolders;

import phase2.FundTransfers.*;

import java.io.Serializable;
import java.util.*;


public abstract class Account implements Serializable {
    private int accountNum = 0;
    private String holderName;

    private double balance;
    public String accountType;
    private ArrayList<Transactions> history;

    /**
     * Account class constructor
     * @param accountNum Number used to identify a specific account
     * @param holderName Name of holder of the account
     * @param balance Amount of money found in account
     * @param accountType Type of account: Credit Card, Line of Credit, Chequing, or Savings
     */
    public Account(int accountNum, String holderName, double balance, String accountType){
        history = new ArrayList<Transactions>();
        this.accountType = accountType;
        this.accountNum++;
        this.holderName = holderName;
        this.balance = balance;
    }

    /**
     * Get name of account holder
     * @return String of holder's name
     */
    public String getHolderName(){
        return holderName;
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
     *Get the history of the history of the account's transactions
     * @return Set the of transaction history
     */
    public ArrayList<Transactions> setHistory(){
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