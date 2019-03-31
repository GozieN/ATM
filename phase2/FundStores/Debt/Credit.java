package phase2.FundStores.Debt;

import phase2.FundStores.ATM;
import phase2.FundStores.Account;
import phase2.Operators.BankAccountUser.PointSystemUser;
import phase2.Operators.BankAccountUser.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

public abstract class Credit extends Account implements Serializable {
    private double creditLimit;


    /**
     * Credit class constructor
     *
     * @param accountHolder Name of holder of the account
     */
    public Credit(User accountHolder) {
        super(accountHolder);
        accountType = "credit";
    }

    @Override
    public boolean addToBill() {
        return false;
    }
}