package phase2.FundStores.Debt;

import phase2.FundStores.ATM;
import phase2.FundStores.Account;
import phase2.Operators.BankAccountUser.PointSystemUser;
import phase2.Operators.BankAccountUser.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

public class Credit extends Account implements Serializable {
    private double creditLimit;


    /**
     * Credit class constructor
     *
     * @param accountHolder Name of holder of the account
     */
    public Credit(User accountHolder) {
        super(accountHolder);
        accountType = "CreditCard";
        accountsDatabase.add(this);
        this.accountUsers.add(accountHolder);
        history = new Stack<>();
        this.accountHolder = accountHolder;
        this.accountNumTotal++;
        this.holderName = accountHolder.getUsername();
        this.transactionInfoTempHolder = new Object[2];
    }

    /*
     * Set credit limit of account
     * @param creditLimit Limit of which a user can spend in their credit account
     */
    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
   }

    /**
     * Get credit limit of account
     * @return Double for amount of money user can spend on credit account
     */
    public double getCreditLimit() {return this.creditLimit;}

    @Override
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
        return true;
    }

    public boolean addToBill(double amount) {

            if (this.getAccountType() == "LineOfCredit") {
                if ((balance + amount) > getCreditLimit()) {
                    System.out.println("Sorry, you are unable to complete your transaction to" + accountType +
                            "as you have reached your credit limit");
                } else if ((balance + amount) < getCreditLimit()) {
                    depositToAccount(amount);
                }
            } else if ((balance + amount) > getCreditLimit()) {
                System.out.println("Sorry, you are unable to complete your transaction to" + accountType +
                        "as you have reached your credit limit");
            } else if ((balance + amount) < getCreditLimit()) {
                depositToAccount(amount);
            }
//        this.updateHistory(""); - FIGURE OUT BILL UNDOS - maybe BM treats as special case!
//        System.out.println("Transaction completed, the balance in " + accountType + "is now: " + balance);
//        return true; }
        return true;
    }

    @Override
    public boolean addToBill() {
        return false;
    }
}