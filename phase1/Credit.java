package phase1;

public class Credit extends Account {
    private double balance;
    boolean isLOC;

    public Credit(int accountNum, String holderName, double balance, boolean isLOC){
        super(accountNum, holderName);
        this.balance = balance;
        this.isLOC = isLOC;
    }

    public void withdraw(double amount){
        if(isLOC) {
            setBalance(getBalance() + amount);
        }
    }

    public double getBalance(){
        return balance;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

    public void pay(double amount){
        balance -= amount;
    }
}
