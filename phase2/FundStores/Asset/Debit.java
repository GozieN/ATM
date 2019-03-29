package phase2.FundStores.Asset;

import phase2.FundStores.Account;
import phase2.FundStores.Debt.Credit;
import phase2.Operators.BankAccountUser.PointSystemUser;
import phase2.Operators.BankAccountUser.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;

public abstract class Debit extends Account implements Serializable {

    /**
     * Debit class constructor
     * @param accountHolder Name of holder of the account
     */

    /**
     * Helper function to get last line of date.txt file
     *
     * @return last line on file
     */
    public String getLastLine() {
        String currLine;
        String lastLine = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader("./src/date.txt"));

            while ((currLine = br.readLine()) != null) {
                lastLine = currLine;
            }
        } catch (IOException e) {
        }
        return lastLine;
    }


    public Debit(User accountHolder){
        super(accountHolder);
        accountType = "Debit";
    }

    public Debit(User accountHolder, User accountHolder2){
        super(accountHolder);
        accountType = "Debit";
    }

    /**
     *Withdraw amount from account
     * @param amount Amount of money to withdraw
     */
    public boolean withdrawFromAccount(double amount) {
        if (!(balance - amount > 0) && !(this instanceof ChequingAccount) ){
            System.out.println("Sorry, you are unable to withdraw this amount from your " +
                    accountType + "try withdrawing a smaller amount or review your account " +
                    "information!");
            return false;
        }
        else {
            if (this instanceof ChequingAccount){
                if ((balance - amount) >= -100){
                    balance -= amount;}
            }
            else if (this instanceof SavingsAccount){
                if ((balance - amount) >= 0){
                    balance -= amount;
                }
            }
        }
        this.updateHistory("withdraw", amount, null);
        System.out.println("Withdrawal successful, Account: " + this.accountNum +
                " now has a decreased balance of: " + balance + "$CAD");
        if (accountHolder instanceof PointSystemUser){
            ((PointSystemUser) accountHolder).setNumPointsIncrease();}
        return true;
    }

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