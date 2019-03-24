package phase2.FundStores.Asset;

import phase2.FundStores.Asset.Debit;
import phase2.Operators.User;

public class StudentAccount extends Debit implements java.io.Serializable {

    public StudentAccount(User accountHolder){super(accountHolder, "StudentAccount"); }
}