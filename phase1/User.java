package phase1;

import java.util.*;

public class User extends Operator {
    private static ArrayList<User> userdatabase = new ArrayList<User>();
    private static int numusers = 0;
    private String username;
    private String password;
    private CreditCardAccount cca = null;
    private LineOfCreditAccount loca = null;
    private ChequingAccount ca = null;
    private SavingsAccount sa = null;

    // user constructor (BM use ONLY in console)
    public User(String username, String password) {
        super(username, password);
        this.username = username;
        this.password = password;
        numusers += 1;
        userdatabase.add(this);
    }

    // setpassword method (BM use ONLY in console)
    public void setpassword(String newpassword) {
        this.password = newpassword;
    }

    // changepassword method
    public String changepassword(String currentpassword, String newpassword) {
        if (currentpassword.equals(this.password)) {
            this.password = newpassword;
            return "your password has successfully been changed";
        } else {
            return "you have entered the wrong current password. unable to change password";
        }
    }

    // requestaccountcreation method
        // must interact with bankmanager to do this
        // how to implement this? maybe::
            // requestnotifier setter method in BM class, user method calls it
            // user method passes username information and account type being requested to BM setter ?
            // this information would go in output.txt and the accounts
            // would be created the next time the program is launched (the next time input.txt is read)
                // requires information going from output.txt to input.txt

    @Override
    public String viewinfo() {

    }
    // user will not have to input any parameters (direct call)

    @Override
    public String viewbalance() {

    }
    // user input parameters: account num/type

    @Override
    public String transfer() {

    }
    // user input parameters: amount, (from) account num/type, (to) account num/type

    @Override
    public String withdraw() {

    }
    // user input parameters: amount, account num/type

    @Override
    public String deposit() {

    }
    // user input parameters: amount, account num/type

    // etransfer method
        // input parameters: amount, (to) user, (to) account num/type

    // paybill method
        // input parameters: amount, (to) ?
}