package phase2.FundStores.Debt;

import phase2.FundStores.*;
import phase2.FundStores.Asset.ChequingAccount;
import phase2.FundStores.Asset.Debit;
import phase2.FundStores.Asset.SavingsAccount;
import phase2.Operators.BankAccountUser.PointSystemUser;
import phase2.Operators.BankAccountUser.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

public class LineOfCredit extends Credit implements Transferable, ATMWIthdrawable {

    public LineOfCredit(User accountHolder){
        super(accountHolder);
        accountType = "LineOfCredit";
        accountsDatabase.add(this);
        this.accountUsers.add(accountHolder);
        history = new Stack<>();
        this.accountHolder = accountHolder;
        this.accountNumTotal++;
        this.holderName = accountHolder.getUsername();
        this.transactionInfoTempHolder = new Object[2];
        }

    /**
     * Pay the bill
     * @param amount Amount of money to withdraw from account to pay bill
     */
    public boolean payBill(double amount) {
        withdrawFromAccount(amount);
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("phase2/txtfiles/Outgoing.txt"));
            out.write(holderName + "paid a bill of " + Double.toString(amount));
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        this.updateHistory("bill", amount, null);
        ((PointSystemUser) accountHolder).setNumPointsIncrease();
        return true;}

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
        ((PointSystemUser) accountHolder).setNumPointsIncrease();
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
