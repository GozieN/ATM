package phase1.archive;

public abstract class Debit extends Account {

    public Debit(int accountNum, String holderName, double balance){
        super(accountNum, holderName, balance);
    }

    public void deposit(double amount){
        setBalance(getBalance() + amount);
    }
}

