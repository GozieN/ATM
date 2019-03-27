package phase2.FundStores.Asset;

import phase2.Operators.BankAccountUser.User;

public class Oldies extends SavingsAccount implements java.io.Serializable {
    private User nominee;
        public Oldies(User accountHolder, User nominee){
            super(accountHolder, nominee);
            this.nominee = nominee;
        }
}
