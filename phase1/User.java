package phase1;

public class User extends Operator {
    private static int numusers = 0;
    private String username;
    private String password;
    private CreditCardAccount cca = null;
    private LineOfCreditAccount loca = null;
    private ChequingAccount ca = null;
    private SavingsAccount sa = null;

    public User(String username, String password) {
        super(username, password);
        this.username = username;
        this.password = password;
    }

    // requestaccountcreation method - must interact with bankmanager to do this
        // calls in BM method: creates new User instance in userdatabase arraylist in operator class
        // adds 1 to static numusers

    // etransfer method
        // input parameters: amount, (to) user, (to) account num/type

    // paybill method
        // input parameters: amount, (to) ?


}