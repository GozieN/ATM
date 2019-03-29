package phase2.FundStores.Asset;

import phase2.Operators.BankAccountUser.PointSystemUser;
import phase2.Operators.BankAccountUser.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;

public class SavingsAccount extends Debit implements java.io.Serializable {

    private User accountHolder;

    /**
     * SavingsAccount constructor
     *
     * @param accountHolder Name of holder of the account
     */
    public SavingsAccount(User accountHolder, User accountHolder2) {
        super(accountHolder, accountHolder2);
        this.accountType  = "savings";
    }

    public SavingsAccount(User accountHolder) {
        super(accountHolder);
        this.accountType  = "savings";
    }


    /**
     * Add monthly interest for savings account
     *
     */
    public void monthlyInterest() {
        double interest = 1.5;
        if (this.accountHolder instanceof PointSystemUser){
            ((PointSystemUser) accountHolder).getIsGoldMember();
            interest = 3.5;
        }
        if (("01").equals(getLastLine().substring(0, 2))) {
            double increaseBy = interest * getBalance();
            setBalance(getBalance() - increaseBy);
        }
    }
}