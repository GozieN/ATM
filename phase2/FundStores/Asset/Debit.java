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
     * Change actions performed in account history
     */
    public void undoTransaction(){
        Object[] transferInfo = history.pop();
        if (transferInfo == null){
            System.out.println("Sorry, your last action could not be reversed because you " +
                    "have yet to make a transaction");
        }
        if (transferInfo[0].equals("bill")){
            history.push(transferInfo);
            System.out.println("Sorry, your last action could not be reversed because you payed a bill.");
        }else{
            if (transferInfo[0].equals("transfer")) {
                ((Debit) transferInfo[2]).transfer((double) transferInfo[1], this);
            } else if (transferInfo[0].equals("withdraw")) {
                ((Account) transferInfo[2]).depositToAccount((double) transferInfo[1]);
            }else if (transferInfo[0].equals("deposit") || transferInfo[0].equals("cheque deposit")){
                ((Account) transferInfo[2]).withdrawFromAccount((double) transferInfo[1]);
            }
            System.out.println(getHolderName() + ", The last action that you performed was a" + transferInfo[0] + "" +
                    " of amount " + transferInfo[1] + " has been reversed upon your request.");
        }
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