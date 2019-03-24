package FundHolders;

import Operators.*;
import java.io.Serializable;


// PrepaidCredit account works like a prepaid gift card (only able to reload, balance never goes under 0, can only spend
// up to limit given + incurs 3% deduction every month)

public class PrepaidCredit extends Credit implements java.io.Serializable {
    private User user;

    public PrepaidCredit (User accountHolder, String accountType){ super(accountHolder, "Prepaid Credit"); }
}
