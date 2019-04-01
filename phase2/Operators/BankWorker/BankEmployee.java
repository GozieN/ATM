package phase2.Operators.BankWorker;

import phase2.FundStores.Account;
import phase2.Operators.Operator;

import java.io.Serializable;
import java.util.ArrayList;

public class BankEmployee extends Operator implements Serializable {

    private ArrayList<BankEmployee> bankEmployeeDatabase = new ArrayList<>();
    private int numBankWorkers = 0;
    private ArrayList<Account> AccountsCreated = new ArrayList<Account>();
    private String username;
    private String password;

    /**
     * BankEmployee constructor
     * @param username Username for login
     * @param password Password for login
     */
    public BankEmployee(String username, String password){
        super(username, password);
    }

    /**
     * Print a summary of a single account.
     * @param account Instance of account
     */
    public String singleAccountSummary(Account account) {
        String s = "Account holder: " + account.getHolderName() + " "
                + "DATE AND TIME " +
                "" + "Account summary:" + account.accountType +"Account Number: "
                + account.getAccountNum() + " contains: " + account.getBalance() + "CAD$";
        return s;
    }

    /**
     * Print the balance of the account.
     * @param account Instance of account
     */
    public void viewBalance(Account account) {
        System.out.println("Account: " + account.getAccountNum() + " has a balance of: " + account.getBalance());
    }
}