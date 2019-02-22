package phase1.src;

public class User extends Operator {
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

    // requestaccountcreation method


}