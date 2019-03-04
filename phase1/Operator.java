package phase1;

import java.util.*;

public abstract class Operator {
    String username;
    String password;

    // operator constructor
    public Operator(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // changepassword method
    public abstract String changepassword(String currentpassword, String newpassword);
        // BM and User will each override this method

    // viewinfo method
    public abstract String viewinfo();
        // BM and User will each override this method
            // BM will need to input user instance in the method parameter
            // but user will not have to input any parameters (direct call)
        // returns (for print)::
            // a summary of all of their account balances
            // the most recent transaction on any account
            // the date of creation of one of their accounts
            // their net total

    // viewbalance method
    public abstract String viewbalance();
    // BM and User will each override this method
        // BM will need to input parameters: user instance, account num/type
        // user input parameters: account num/type

    // transfer method
    public abstract String transfer();
    // BM and User will each override this method
        // BM input parameters: user instance, amount, (from) account num/type, (to) account num/type
        // user input parameters: amount, (from) account num/type, (to) account num/type
    // returns a string to ensure completion of transfer

    // withdraw method
    public abstract String withdraw();
    // BM and User will each override this method
        // BM input parameters: user instance, amount, account num/type
        // user input parameters: amount, account num/type
    // returns a string to ensure completion of withdrawal

    // deposit method
    public abstract String deposit();
    // BM and User will each override this method
        // BM input parameters: user instance, amount, account num/type
        // user input parameters: amount, account num/type
    // returns a string to ensure completion of deposit
}