package FundHolders;

import Operators.*;
import java.io.Serializable;

public class PrepaidCredit extends Account implements Serializable {
    private User user;

    public PrepaidCredit (User accountHolder, String accountType){ super(accountHolder, "Prepaid Credit"); }
}
