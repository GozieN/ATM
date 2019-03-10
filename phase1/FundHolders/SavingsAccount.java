package phase1.FundHolders;

public class SavingsAccount extends Debit {
    /**
     * SavingsAccount constructor
     * @param accountNum
     * @param holderName
     * @param balance
     */
    public SavingsAccount(int accountNum, String holderName, double balance){
        super(accountNum, holderName, balance, "SavingsAccount");
    }

//    use getdate method for interests
//    store string of month and check if string is same as month in getdate method, if changed then increase interest

    /**
     *
     * @param interest
     */
    public void monthlyInterest(double interest){
        double increaseBy = interest * getBalance();
        setBalance(getBalance() - increaseBy);
    }
}