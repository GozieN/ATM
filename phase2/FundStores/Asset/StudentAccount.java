package phase2.FundStores.Asset;

import phase2.Operators.BankAccountUser.PointSystemUser;
import phase2.Operators.BankAccountUser.User;
import phase2.FundStores.Asset.Debit;
import phase2.FundStores.Account;
import java.io.Serializable;

public class StudentAccount extends Debit implements Serializable {
    private int notrasfers;

    public StudentAccount(User accountHolder){
        super(accountHolder, "StudentAccount");
        setBalance(50);
    }

    @Override
    public boolean withdrawFromAccount(double amount){
        if (((getBalance() - amount) >= 0) && (notrasfers < 20)){
            setBalance(getBalance() - amount);
            notrasfers += 1;
        }
        this.updateHistory("withdraw", amount, null);
        System.out.println("Withdrawal successful, Account: " + this.getAccountNum() +
                " now has a decreased balance of: " + this.getBalance() + "$CAD");
        ((PointSystemUser) getAccountHolder()).increasePoints();
        return true;
    }

    public void monthlyInterest(double interest) {
        if (("01").equals(getLastLine().substring(0, 2))) {
            this.notrasfers = 0;
        }
    }
}
