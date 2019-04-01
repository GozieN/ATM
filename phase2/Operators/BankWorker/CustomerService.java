package phase2.Operators.BankWorker;

import phase2.Operators.BankAccountUser.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class CustomerService extends BankEmployee{
    public CustomerService(String username, String password){
        super(username, password);
    }

    /**
     * Display the features that this user has.
     * @return String - the features this user has!
     */
    public String viewCapabilities(){
        String s = "";
        s = "As a User Consultant for the Bank, you are able to do the following: \n" +
                "- Advise users on account creation.\n" +
                "- Message Bank Manager about complaints.\n" +
                "- Change your password at any time. \n" +
                "- Create a new account at any time. \n" +
                "- View a summary of a single account. \n" +
                "- View a summary of all your existing accounts";

        return s;
    }

    /**
     * Display the features that this user has.
     * @return String - the features this user has!
     */
    public String viewContract(){
        String s;
        s = "As a User Consultant for the Bank, " +
                "you agree not to engage in fraudulent behavior, " +
                "and agree not to abuse the Bank Manager messaging system. Click back or exit//.";
        return s;
    }
}
