package phase2.FundHolders;

import phase2.Operators.*;

public class studentacc extends Debit implements java.io.Serializable {

    public studentacc(User accountHolder){super(accountHolder, "StudentAccount"); }
}