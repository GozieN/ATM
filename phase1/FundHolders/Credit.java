package phase1.FundHolders;

/**
 *
 */
public class Credit extends Account {
    boolean isLOC;

    /**
     * Credit class constructor
     * @param accountNum
     * @param holderName
     * @param balance
     * @param isLOC
     */
    public Credit(int accountNum, String holderName, double balance, boolean isLOC){
        super(accountNum, holderName, balance, "CreditCardAccount");
        this.isLOC = isLOC;
        if(isLOC){
            accountType = "LineOfCredit";
        }
    }

    /**
     * withdraw amount from account
     * @param amount
     */
    public void withdraw(double amount){
        if(isLOC) {
            setBalance(getBalance() + amount);
            transaction abc = new transaction(amount, "withdrawn");
            updateHistory(abc);//updates the transaction history of the account
        }
    }

    /**
     *
     * @param amount
     */
    public void deposit(double amount){
        setBalance(getBalance() - amount);
    }
}
