package phase1.Operators.Users;

import phase1.FundHolders.Account;

public interface UserOperator {

    // changepassword method

    public String changePassword(String currentpassword, String newpassword);
        // BM and User will each implement their own versions of this method

    void singleAccountSummary(Account account);

    // viewinfo method
    public String viewInfo();
        // BM and User will each implement their own versions of this method
            // BM will need to input user instance in the method parameter
            // but user will override and not have to input any parameters (direct call)
        // returns (for print)::
            // a summary of all of their account balances
            // the most recent transaction on any account
            // the date of creation of one of their accounts
            // their net total
//
//    // viewBalance method
//    public void viewBalance(Account account);
//        // BM and User will each implement their own versions of this method
//            // BM will need to input parameters: user instance, account num/type
//            // user input parameters: account num/type
//
//    // transfer method
//    public void transfer(int amount, Account from, Account to);
//        // BM and User will each implement their own versions of this method
//            // BM input parameters: user instance, amount, (from) account num/type, (to) account num/type
//            // user input parameters: amount, (from) account num/type, (to) account num/type
//        // returns a string to ensure completion of transfer
//
//    // withdraw method
//    public void withdraw(int amount, Account from);
//        // BM and User will each implement their own versions of this method
//            // BM input parameters: user instance, amount, account num/type
//            // user input parameters: amount, account num/type
//        // returns a string to ensure completion of withdrawal
//
//    // deposit method
//    public void deposit(int amount, Account to);
//        // BM and User will each implement their own versions of this method
//            // BM input parameters: user instance, amount, account num/type
//            // user input parameters: amount, account num/type
//        // returns a string to ensure completion of deposit
}