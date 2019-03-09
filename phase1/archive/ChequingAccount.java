package phase1.archive;

public class ChequingAccount extends Debit {
    private boolean isPrimary;


    public ChequingAccount(int accountNum, String holderName, double balance, boolean isPrimary){
        super(accountNum, holderName, balance);
        this.isPrimary = isPrimary;
    }

    public void withdraw(double amount){
        if ((getBalance() - amount) >= -100)
            setBalance(getBalance() - amount);
    }

    public static class TestClass {

        public static void main(String[] args) {



        }
    }
}