package phase1.FundHolders;
public class ChequingAccount extends Debit {
    private boolean isPrimary;

    /**
     * ChequingAccount class constructor
     * @param accountNum
     * @param holderName
     * @param balance
     * @param isPrimary
     */
    public ChequingAccount(int accountNum, String holderName, double balance, boolean isPrimary){
        super(accountNum, holderName, balance, "ChequingAccount");
        this.isPrimary = isPrimary;
    }

    /**
     *Withdraw amount from the account
     * @param amount
     */
    public void withdraw(double amount){
        if ((getBalance() - amount) >= -100)
            setBalance(getBalance() - amount);
    }

}

