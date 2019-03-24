package phase2.FundStores.Debt;

import phase2.FundStores.Account;
import phase2.Operators.User;

import java.io.Serializable;

public class Credit extends Account implements Serializable {
    private User user;
    boolean isLOC;

    /**
     * Credit class constructor
     *
     * @param accountHolder Name of holder of the account
     * @param isLOC         Checks if type of account is a Line of Credit
     */
    public Credit(User accountHolder, boolean isLOC) {
        super(accountHolder, "CreditCard");
        this.isLOC = isLOC;
        if (isLOC) {
            accountType = "LineOfCredit";
        }
    }

    @Override
    public boolean transfer(double amount, Account receiverAccount) {
        String s = "";
        if (accountType == "CreditCard") {
            System.out.println("Sorry, you are unable to transfer funds out of a Credit Card Account");
            return false;
        } else {
            if (super.transfer(amount, receiverAccount)){
                return true;
            }else{
                return false;
            }
        }
    }

    @Override
    public double getBalance () {
        //+if owes, negative if overpays
        return 1.1;
    }
}