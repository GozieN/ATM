package phase1.FundHolders;

public abstract class Debit extends Account {

    public Debit(int accountNum, String holderName, double balance, String accountType){
        super(accountNum, holderName, balance, accountType);
    }

   public void deposit(double amount){
        setBalance(getBalance() + amount);
    }
}

