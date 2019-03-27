package phase2.FundStores.Asset;

import phase2.Operators.BankAccountUser.User;
import phase2.FundStores.Asset.Debit;
import phase2.FundStores.Account;
import java.io.Serializable;

public class StudentAccount extends Debit implements Serializable {

    public StudentAccount(User accountHolder){super(accountHolder, "StudentAccount"); }
}