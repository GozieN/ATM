package phase2.FundStores.Asset;

import phase2.FundStores.Account;
import phase2.Operators.BankAccountUser.User;
import java.io.Serializable;

public abstract class Debit extends Account implements Serializable {

    private User user;
    /**
     * Debit class constructor
     * @param accountHolder Name of holder of the account
     */

    public Debit(User accountHolder, String accountType){
        super(accountHolder, accountType);
    }

}