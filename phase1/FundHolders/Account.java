package phase1.FundHolders;

import phase1.FundTransfers.*;
import phase1.Operators.*;
import java.util.*;

public abstract class Account {
    // user can have multiple accounts
    private int accountNum;
    private String holderName;
    private double balance;
    public String accountType;
    private ArrayList<TransferOfFunds> history;

    public Account(int accountNum, String holderName, double balance, String accountType){
        history = new ArrayList<transaction>();
        this.accountType = accountType;
        this.accountNum = accountNum;
        this.holderName = holderName;
        this.balance = balance;
    }

    public void updatehistory(transactioninfo transactioninfo){
        history.add(transactioninfo);
    }

    public abstract void deposit(double amount);
    public abstract void withdraw(double amount);

    public int getAccountNum() { return accountNum; }

    public String getHolderName() {
        return holderName;
    }

    public String getAccountType() { return accountType; }

    public double getBalance() { return this.balance; }

    public void setBalance(double balance){
        this.balance = balance;
    }

    public static class transaction {
        private int senderAccount;
        private int receiverAcocount;
        private double amount;

    }
}