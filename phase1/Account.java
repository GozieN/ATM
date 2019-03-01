package phase1;

public abstract class Account {
    //user can have multiple accounts
    private int accountNum;
    private String holderName;

    public Account(int accountNum, String holderName){
        this.accountNum = accountNum;
        this.holderName = holderName;
    }

    public abstract void deposit(double amount);
    public abstract void withdraw(double amount);
}
