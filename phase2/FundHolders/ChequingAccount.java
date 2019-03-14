package phase2.FundHolders;
public class ChequingAccount extends Debit {
    private boolean isPrimary;

    /**
     * ChequingAccount class constructor
     * @param accountNum Number used to identify a specific account
     * @param holderName Name of holder of the account
     * @param balance Amount of money found in account
     * @param isPrimary If multiple chequing accounts exists, checks if it is the primary/default account
     */
    public ChequingAccount(int accountNum, String holderName, double balance, boolean isPrimary){
        super(accountNum, holderName, balance, "ChequingAccount");
        this.isPrimary = isPrimary;
    }

}

