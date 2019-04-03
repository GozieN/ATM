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
    private static final long serialVersionUID = 1L;

    private double creditLimit;


    /**
     * Credit class constructor
     * @param accountHolder Name of holder of the account
     */
    public Credit(User accountHolder) {
        super(accountHolder);
        accountType = "credit";
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