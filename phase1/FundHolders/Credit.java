package phase1.FundHolders;

public class Credit extends Account {
    boolean isLOC;

    public Credit(int accountNum, String holderName, double balance, boolean isLOC){
        super(accountNum, holderName, balance, "CreditCardAccount");
        this.isLOC = isLOC;
        if(isLOC){
            accountType = "LineOfCredit";
        }
    }
    //this is assumed to be a cash. The transfer function serves the function online transfers etc.
    public void withdraw(double amount){
        if(isLOC) {
            setBalance(getBalance() + amount);
            transaction abc = new transaction(amount, "withdrawn");
            updateHistory(abc);//updates the transaction history of the account
        }
    }

    public void deposit(double amount){
        setBalance(getBalance() - amount);
    }
}
