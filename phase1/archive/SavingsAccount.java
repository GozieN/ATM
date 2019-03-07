package phase1.archive;

public class SavingsAccount extends Debit {

    public SavingsAccount(int accountNum, String holderName, double balance){
        super(accountNum, holderName, balance);
    }

    public void withdraw(double amount){
        if ((getBalance() - amount) >= 0)
            setBalance(getBalance() - amount);
    }

//    use getdate method for interests
//    store string of month and check if string is same as month in getdate method, if changed then increase interest

    public void monthly_interest(double interest){
        double increaseby = interest * getBalance();
        setBalance(getBalance() - increaseby);
    }
}
