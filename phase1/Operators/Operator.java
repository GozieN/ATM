package phase1.Operators;

import phase1.FundHolders.Account;

// highest level Operators interface - everything in this package implements this
public interface Operator {

    public void singleAccountSummary(Account account);

    public void viewBalance(Account account);

    //person observable
}