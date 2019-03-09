package phase1.Operators;

import phase1.FundHolders.Account;
import phase1.FundHolders.Debit;

public class BankWorker implements Operator{

    public void singleAccountSummary(Account account) {
        System.out.println("Account holder: " + account.getHolderName() + " "
                + "DATE AND TIME " +
                "" + "Account summary:" + account.getAccountType() +"Account Number: "
                + account.getAccountNum() + " contains: " + account.getBalance() + "currency");}


    public void viewBalance(Account account) {
        System.out.println("Account: " + account.getAccountNum() + " has a balance of: " + account.getBalance());
    }
}
