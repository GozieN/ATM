package phase2.FundStores.Asset;

import phase2.Operators.BankAccountUser.User;

import java.util.Stack;

public class ChequingAccount extends Debit implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    public boolean isPrimary;

    /**
     * ChequingAccount class constructor
     * @param accountHolder holder of the account
     * @param isPrimary check if chequing is primary account
     */
    public ChequingAccount(User accountHolder, boolean isPrimary) {
        super(accountHolder);
        this.isPrimary = isPrimary;
        accountType  = "chequing";
        history = new Stack<>();
    }

    /**
     * Reading from external file
     * @return Boolean to check if transaction is added to bill
     */
    @Override
    public boolean addToBill() {
        return false;
    }
}