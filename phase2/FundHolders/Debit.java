package phase2.FundHolders;

import phase2.Operators.*;

public abstract class Debit extends Account implements java.io.Serializable {

    private User user;
    /**
     * Debit class constructor
     * @param accountHolder Name of holder of the account
     */

    public Debit(User accountHolder, String accountType){
        super(accountHolder, accountType);
    }

    /**
     * Deposit amount into account
     * @param amount Amount of money to be deposited
     */
   public void deposit(double amount){
        setBalance(getBalance() + amount);
   }
}