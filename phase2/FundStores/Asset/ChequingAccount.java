package phase2.FundStores.Asset;

import phase2.Operators.BankAccountUser.User;

import java.util.Stack;

public class ChequingAccount extends Debit implements java.io.Serializable {

    /**
     * ChequingAccount class constructor
     *
     */
    public ChequingAccount(User accountHolder, boolean isPrimary) {
        super(accountHolder);
        accountType  = "chequing";
        history = new Stack<>();
    }
}