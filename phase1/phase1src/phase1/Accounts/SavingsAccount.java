package phase1.Accounts;


import phase1.Accounts.Debit;

public class SavingsAccount extends Debit {

    public SavingsAccount(int accountNum, String holderName, double balance){
        super(accountNum, holderName, balance);
    }

    public void withdraw(double amount){
        if ((getBalance() - amount) >= 0)
            setBalance(getBalance() - amount);
    }
}
