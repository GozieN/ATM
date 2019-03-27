package phase2.FundStores.Asset;

import phase2.Operators.BankAccountUser.User;

import java.util.Stack;

public class ChequingAccount extends Debit implements java.io.Serializable {
    private double balance;
    private User user;
    private Stack history;
    private int accNum;
    private String holderName;
    private String accountType;

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