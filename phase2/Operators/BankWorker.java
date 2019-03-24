package phase2.Operators;

import phase2.FundStores.Account;

import java.io.Serializable;
import java.util.ArrayList;

public class BankWorker extends Operator implements Serializable {

    private ArrayList<BankWorker> bankWorkerDatabase = new ArrayList<>();
    private int numBankWorkers = 0;
    private ArrayList<Account> AccountsCreated = new ArrayList<Account>();
    private String username;
    private String password;

    public BankWorker(String username, String password){
        super(username, password);

    }
    /**
     * Print a summary of a single account.
     * @param account Instance of account
     */
    public void singleAccountSummary(Account account) {
        System.out.println("Account holder: " + account.getHolderName() + " "
                + "DATE AND TIME " +
                "" + "Account summary:" + account.accountType +"Account Number: "
                + account.getAccountNum() + " contains: " + account.getBalance() + "CAD$");}

    /**
     * Print the balance of the account.
     * @param account Instance of account
     */
    public void viewBalance(Account account) {
        System.out.println("Account: " + account.getAccountNum() + " has a balance of: " + account.getBalance());
    }
}