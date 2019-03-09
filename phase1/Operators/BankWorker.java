package phase1.Operators;

import phase1.FundHolders.Account;

public class BankWorker extends Operator {
    /**
     * Print a summary of a single account.
     * @param account
     */
    public void singleAccountSummary(Account account) {
        System.out.println("Account holder: " + account.getHolderName() + " "
                + "DATE AND TIME " +
                "" + "Account summary:" + account.accountType +"Account Number: "
                + account.getAccountNum() + " contains: " + account.getBalance() + "currency");}

    /**
     * Print the balance of the account.
     * @param account
     */
    public void viewBalance(Account account) {
        System.out.println("Account: " + account.getAccountNum() + " has a balance of: " + account.getBalance());
    }
}
