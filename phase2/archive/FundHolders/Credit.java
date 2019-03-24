package phase2.FundHolders;

import phase2.Operators.*;
import java.io.Serializable;

public class Credit extends Account implements Serializable {
    private User user;
    boolean isLOC;

    /**
     * Credit class constructor
     * @param accountHolder Name of holder of the account
     * @param isLOC Checks if type of account is a Line of Credit
     */
    public Credit(User accountHolder, boolean isLOC){
        super(accountHolder, "CreditCardAccount");
        this.isLOC = isLOC;
        if(isLOC){
            accountType = "LineOfCredit";
        }
    }
}