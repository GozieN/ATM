package phase2.FundStores.Asset;

import phase2.Operators.BankAccountUser.User;

import java.util.Stack;

public class ChequingAccount extends Debit implements java.io.Serializable {
    public boolean isPrimary;

    /**
     * ChequingAccount class constructor
     *
     */
    public ChequingAccount(User accountHolder, boolean isPrimary) {
        super(accountHolder);
        this.isPrimary = isPrimary;
        accountType  = "chequing";
        history = new Stack<>();
    }

    @Override
    public boolean addToBill() {
        return false;
    }
}