package phase1.FundHolders;

import phase1.FundTransfers.*;
import phase1.Operators.*;
import java.util.*;


public abstract class Account {
    private int accountNum;
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
        this.accountNum = accountNum;
        this.holderName = holderName;
        this.balance = balance;
    }

    public String getHolderName(){
        return holderName;
    }

    public int getAccountNum(){
        return accountNum;
    }
    public String getAccountType() { return accountType; }

    public void updateHistory(Transactions transactionInfo){
        history.add(transactionInfo);
    }


    public void setBalance(double balance){
        this.balance = balance;
    }


    public double getBalance(){
        return this.balance;
    }

}