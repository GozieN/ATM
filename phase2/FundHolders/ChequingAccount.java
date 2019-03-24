package phase2.FundHolders;

import phase2.Operators.User;

import java.util.Stack;

public class ChequingAccount extends Account implements java.io.Serializable {
    private double balance;
    private User user;
    private Stack history;
    private int accNum;
    private String holderName;

    /**
     * ChequingAccount class constructor
     *
     */
    public ChequingAccount(User accountHolder, String accountType) {
        super(accountHolder, accountType);
        history = new Stack<>();
    }
}