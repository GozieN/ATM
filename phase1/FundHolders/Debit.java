package phase1.FundHolders;

public abstract class Debit extends Account {
    /**
     * Debit class constructor
     * @param accountNum
     * @param holderName
     * @param balance
     * @param accountType
     */
    public Debit(int accountNum, String holderName, double balance, String accountType){
        super(accountNum, holderName, balance, accountType);
    }
    /**
     * Deposit amount into account
     * @param amount
     */
   public void deposit(double amount){
        setBalance(getBalance() + amount);
    }
}

