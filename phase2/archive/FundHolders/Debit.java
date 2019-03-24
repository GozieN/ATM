package phase2.FundHolders;

import phase2.FundHolders.Account;
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

}