package phase1.FundHolders;

import phase1.FundTransfers.*;
import phase1.Operators.*;
import java.util.*;


public abstract class Account {
    private int accountNum;
    private String holderName;

    private double balance;
    public String accountType;
    private ArrayList<TransferOfFunds> history;

    /**
     * Account class constructor
     * @param accountNum
     * @param holderName
     * @param balance
     * @param accountType
     */
    public Account(int accountNum, String holderName, double balance, String accountType){
        history = new ArrayList<TransferOfFunds>();
        this.accountType = accountType;
        this.accountNum = accountNum;
        this.holderName = holderName;
        this.balance = balance;
    }

    public int getAccountNum(){
        return accountNum;
    }
    public void updateHistory(TransferOfFunds transactionInfo){
        history.add(transactionInfo);
    }


    public abstract void deposit(double amount);


    public abstract void withdraw(double amount);


    public void setBalance(double balance){
        this.balance = balance;
    }


    public double getBalance(){
        return this.balance;
    }

    public static class transaction {
        private int senderAccount;
        private int receiverAccount;
        private double amount;

    }
}