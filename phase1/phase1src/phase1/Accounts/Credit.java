package phase1.Accounts;

public class Credit extends Account {
    boolean isLOC;

    public Credit(int accountNum, String holderName, double balance, boolean isLOC){
        super(accountNum, holderName, balance);
        this.isLOC = isLOC;
    }

    public void withdraw(double amount){
        if(isLOC) {
            setBalance(getBalance() + amount);
        }
    }

    public void deposit(double amount){
        setBalance(getBalance() - amount);
    }
}
