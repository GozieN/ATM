package phase1;

import phase1.FundHolders.Account;

// highest level Operators interface - everything in this package implements this
public interface Operator {

    public void singleAccountSummary(Account account);

    // viewinfo method
    public void viewInfo();
    // BM and User will each implement their own versions of this method
    // BM will need to input user instance in the method parameter
    // but user will override and not have to input any parameters (direct call)
    // prints::
        // a summary of all of their account balances
        // the most recent transaction on any account
        // the date of creation of one of their accounts
        // their net total

    // viewBalance method
    public void viewBalance(Account account);
    // BM and User will each implement their own versions of this method
        // BM will need to input parameters: user instance, account num/type
        // user input parameters: account num/type
}