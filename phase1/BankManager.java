package phase1;

public class BankManager extends Operator {
    private static int numbankmanagers = 0;
    private String username;
    private String password;

    public BankManager(String username, String password) {
        super(username, password);
        this.username = username;
        this.password = password;
    }

    @Override
    public String viewinfo() {

    }
    // BM will need to input user instance in the method parameter

    @Override
    public String viewbalance() {

    }
    // BM will need to input parameters: user instance, account num/type

    @Override
    public String transfer() {

    }
    // BM input parameters: user instance, amount, (from) account num/type, (to) account num/type

    @Override
    public String withdraw() {

    }
    // BM input parameters: user instance, amount, account num/type

    @Override
    public String deposit() {

    }
    // BM input parameters: user instance, amount, account num/type

    // createuser method
        // creates new User instance in userdatabase arraylist in operator class

    // undomostrecenttransaction method
        // input parameters: user instance, account num/type

    // ATM setters methods

    // ATM adders methods


}