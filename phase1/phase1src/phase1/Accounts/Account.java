package phase1.Accounts;


public abstract class Account {
    // user can have multiple accounts
    private int accountNum;
    private String holderName;
    private double balance;

    public Account(int accountNum, String holderName, double balance){
        this.accountNum = accountNum;
        this.holderName = holderName;
        this.balance = balance;
    }

    public abstract void deposit(double amount);
    public abstract void withdraw(double amount);

    public int getAccountNum() {
        return accountNum;
    }

    public double getBalance(){
        return this.balance;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }
}
