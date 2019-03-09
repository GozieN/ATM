package phase1.Operators;

import phase1.FundHolders.Account;

import java.util.Observable;

import phase1.FundHolders.Account;
import phase1.FundHolders.Debit;

import java.util.Observable;

public class Operator extends Observable{

    /**
     * Return a summary of the accounts
     * @param account
     */
    public void singleAccountSummary(Account account) {
        System.out.println("Account holder: " + account.getHolderName() + " "
                + "DATE AND TIME " +
                "" + "Account summary:" + account.getAccountType() +"Account Number: "
                + account.getAccountNum() + " contains: " + account.getBalance() + "currency");}

    /**
     * Vie the balance related to the account
     * @param account
     */
    public void viewBalance(Account account) {
        System.out.println("Account: " + account.getAccountNum() + " has a balance of: " + account.getBalance());
    }
}

