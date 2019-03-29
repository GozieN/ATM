package phase2.FundStores.Asset;

import phase2.FundStores.Asset.Debit;
import phase2.Operators.BankAccountUser.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;


// PrepaidCredit account works like a prepaid gift card (only able to reload, balance never goes under 0, can only spend
// up to limit given + incurs $3 deduction every month)

public class PrepaidCredit extends Debit implements Serializable {


    /**
     * PrepaidCredit constructor
     * @param accountHolder
     */
    public PrepaidCredit (User accountHolder){
                //MUST SET INITIAL AMOUNT.
        super(accountHolder);
        accountType = "Prepaid Credit";
        if (balance > 0 ){
            this.balance = balance; }
    }

    public void monthlyFees() {
        double fee = 3;
        if (("01").equals(getLastLine().substring(0, 2))) {
            if (balance > 3) {
                setBalance(getBalance() - fee);
            } else {
                addToBill(fee);
            }
        }
    }
