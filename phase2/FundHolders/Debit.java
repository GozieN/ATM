package phase2.FundHolders;

public abstract class Debit extends Account {
    /**
     * Debit class constructor
     * @param accountNum Number used to identify a specific account
     * @param holderName Name of holder of the account
     * @param balance Amount of money found in account
     * @param accountType Type of account: Chequing or Savings
     */
    public Debit(int accountNum, String holderName, double balance, String accountType){
        super(accountNum, holderName, balance, accountType);
    }
    /**
     * Deposit amount into account
     * @param amount Amount of money to be deposited
     */
   public void deposit(double amount){
        setBalance(getBalance() + amount);
    }
}

