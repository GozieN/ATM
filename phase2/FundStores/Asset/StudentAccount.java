package phase2.FundStores.Asset;

import phase2.Operators.BankAccountUser.PointSystemUser;
import phase2.Operators.BankAccountUser.User;
import phase2.FundStores.Asset.Debit;
import phase2.FundStores.Account;
import java.io.Serializable;

public class StudentAccount extends Debit implements Serializable {
    private int notrasfers;
    private double savefor;

    public StudentAccount(User accountHolder){
        super(accountHolder);
        setBalance(50);
    }

    @Override
    public boolean withdrawFromAccount(double amount){
        if (((getBalance() - amount) >= 0) && (notrasfers < 20)) {
            setBalance(getBalance() - amount);
            notrasfers += 1;
            this.updateHistory("withdraw", amount, null);
            System.out.println("Withdrawal successful, Account: " + this.getAccountNum() +
                    " now has a decreased balance of: " + this.getBalance() + "$CAD");
            ((PointSystemUser) getAccountHolder()).increasePoints();
            return true;
        }
        else{
            System.out.println("Withdrawal unsuccessful monthly withdrawal limit reached, Account: " +
                    this.getAccountNum() + " now has a balance of: " + this.getBalance() + "$CAD");
            return false;
        }
    }

    public void updateNotransfers(double interest) {
        if (("01").equals(getLastLine().substring(0, 2))) {
            this.notrasfers = 0;
        }
    }

    public void startSaving(double amount){
        if(amount < getBalance()){
            setBalance(getBalance() - amount);
            savefor += amount;
            System.out.println("A savings for your student account has been opened. This money won't be able to " +
                    "accessed by you unless you break the savings scheme");
        }
        else{
            System.out.println("Your balance is below the amount you want to add to your savings scheme...");
        }
    }
}
