package phase2.FundStores.Asset;

import phase2.FundStores.*;
import phase2.FundStores.Debt.Credit;
import phase2.Operators.BankAccountUser.PointSystemUser;
import phase2.Operators.BankAccountUser.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;

public abstract class Debit extends Account implements Serializable, Transferable {

    /**
     * Debit class constructor
     * @param accountHolder Name of holder of the account
     */


    public Debit(User accountHolder){
        super(accountHolder);
        accountType = "Debit";
    }

    public Debit(User accountHolder, User accountHolder2){
        super(accountHolder);
        accountType = "Debit";
    }

    /**
     * Transfer funds from sender to receiver
     * @param amount Amount of money to be transferred
     * @param receiverAccount Account which money will be transferred to
     */
    public boolean transfer(double amount, Account receiverAccount) {
        withdrawFromAccount(amount);
        receiverAccount.depositToAccount(amount);
        receiverAccount.updateHistory("transfer", amount, this);
        System.out.println("Your transaction to account number: " + receiverAccount.getAccountNum() + " was successful, your new balance is: " +
                receiverAccount.getBalance() + "$CAD");
        if (accountHolder instanceof PointSystemUser){
            ((PointSystemUser) accountHolder).setNumPointsIncrease();}
        return true;
    }

    /**
     *Withdraw amount from account
     * @param amount Amount of money to withdraw
     */

    /**
     *Withdraw amount from account using ATM
     * @param amount Amount of money to withdraw
     */
    public boolean withdrawFromATM(int amount) {
        if (!validAmountInput(amount)){
            return false;
        }else{
            atm.minus(amount);
            withdrawFromAccount(amount);
            if (accountHolder instanceof PointSystemUser){
                ((PointSystemUser) accountHolder).setNumPointsIncrease();}
            return true;
        }}



}