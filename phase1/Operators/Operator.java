package phase1.Operators;

import phase1.FundHolders.Account;

// highest level Operators interface - everything in this package implements this
public interface Operator {

    public void singleAccountSummary(Account account);

    // viewBalance method
    public void viewBalance(Account account);
    // BM and User will each implement their own versions of this method
        // BM will need to input parameters: user instance, account num/type
        // user input parameters: account num/type

    //person observable

}