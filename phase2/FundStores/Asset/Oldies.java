package phase2.FundStores.Asset;

import phase2.Operators.BankAccountUser.PointSystemUser;
import phase2.Operators.BankAccountUser.User;

public class Oldies extends SavingsAccount implements java.io.Serializable {
    private User nominee;

    /**
     *
     * @param accountHolder Name of holder of account
     * @param nominee Nominee for oldies account
     */
    public Oldies(User accountHolder, User nominee)
        {
            super(accountHolder, nominee);
            this.nominee = nominee;
        }

    /**
     * Add monthly interest for savings account
     *
     */
    @Override
    public void monthlyInterest() {
        double interest = 0.05;
        if (this.accountHolder instanceof PointSystemUser){
            ((PointSystemUser) accountHolder).getIsGoldMember();
            interest = 0.040;
        }
        if (("01").equals(getLastLine().substring(0, 2))) {
            double increaseBy = interest * getBalance();
            setBalance(getBalance() + increaseBy);
        }
    }

}
