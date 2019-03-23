package phase2.FundHolders;

import phase2.Operators.*;

public class ChequingAccount extends Debit implements java.io.Serializable {
    private boolean isPrimary;
    private User user;

    /**
     * ChequingAccount class constructor
     * @param accountHolder Name of holder of the account
     * @param isPrimary If multiple chequing accounts exists, checks if it is the primary/default account
     */
    public ChequingAccount(User accountHolder, boolean isPrimary){
        super(accountHolder, "ChequingAccount");
        this.isPrimary = isPrimary;
    }
}