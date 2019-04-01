package phase2.FundStores.Asset;

import phase2.Operators.BankAccountUser.PointSystemUser;
import phase2.Operators.BankAccountUser.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;

public class SavingsAccount extends Debit implements java.io.Serializable {


    /**
     * SavingsAccount constructor for two users
     * @param accountHolder Name of holder of the account
     * @param accountHolder2 Name of second holder of account
     */
    public SavingsAccount(User accountHolder, User accountHolder2) {
        super(accountHolder, accountHolder2);
        this.accountType  = "savings";
    }

    /**
     * SavingsAccount constructor
     * @param accountHolder Name of holder of the account
     */
    public SavingsAccount(User accountHolder) {
        super(accountHolder);
        this.accountType  = "savings";
    }


    /**
     * Add monthly interest for savings account
     *
     */
    public void monthlyInterest() {
        double interest = 0.001;
        if (this.accountHolder instanceof PointSystemUser){
            ((PointSystemUser) accountHolder).getIsGoldMember();
            interest = 0.035;
        }
        if (("01").equals(getLastLine().substring(0, 2))) {
            double increaseBy = interest * getBalance();
            setBalance(getBalance() + increaseBy);
        }
    }
}