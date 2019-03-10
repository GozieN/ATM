package phase1.FundHolders;

public class SavingsAccount extends Debit {
    /**
     * SavingsAccount constructor
     * @param accountNum Number used to identify a specific account
     * @param holderName Name of holder of the account
     * @param balance Amount of money found in account
     */
    public SavingsAccount(int accountNum, String holderName, double balance){
        super(accountNum, holderName, balance, "SavingsAccount");
    }

//    use getdate method for interests
//    store string of month and check if string is same as month in getdate method, if changed then increase interest

    /**
     *
     * @param interest Percentage amount of extra money (interest) received as a return for keeping a balance in the
     *                 user's savings account
     */
    public void monthlyInterest(double interest){
        double increaseBy = interest * getBalance();
        setBalance(getBalance() - increaseBy);
    }
}