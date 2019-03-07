package FundHolders;


import FundTransfers.InterAccountTransferOfFunds;

import java.util.ArrayList;

public abstract class Account {
    // user can have multiple accounts
    private int accountNum;
    private String holderName;
    private double balance;
    private static ArrayList<InterAccountTransferOfFunds> interAccountTransferOfFundsRecord = new ArrayList<>();


    public Account(int accountNum, String holderName, double balance){
        this.accountNum = accountNum;
        this.holderName = holderName;
        this.balance = balance;
    }

//    public abstract void deposit(double amount);
//    public abstract void withdraw(double amount);

    public String getAccountType(Account account){
        String s = "";
        if (account instanceof ChequingAccount){
            s += "ChequingAccount";}
        else if (account instanceof SavingsAccount){
            s += "SavingsAccount";}
        else if (account instanceof Credit){
            if (((Credit) account).isLOC)
                s += "LineOfCreditAccount";}
             else{
                 s += "CreditCardccount"; }

        return s;}

    public int getAccountNum() {
        return accountNum;
    }

    public double getBalance(){
        return this.balance;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }}

