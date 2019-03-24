package FundHolders;

import phase2.Operators.User;

import java.util.Stack;

public class ChequingAccount implements java.io.Serializable {
    private double balance;
    private User user;
    private Stack history;
    private int accNum;
    private String holderName;

    /**
     * ChequingAccount class constructor
     *
     */
    public ChequingAccount(int accNum, String holder, double balance) {
        this.balance=balance;
        history = new Stack<>();
        this.accNum=accNum;
        this.holderName = holder;
    }
}