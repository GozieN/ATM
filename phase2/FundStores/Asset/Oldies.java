package phase2.FundStores.Asset;

import phase2.Operators.BankAccountUser.User;

public class Oldies extends SavingsAccount implements java.io.Serializable {

        public Oldies(User accountHolder, User nominee){
            super(accountHolder, nominee);
        }
}
