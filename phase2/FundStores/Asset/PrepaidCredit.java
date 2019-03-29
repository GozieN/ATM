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

public class PrepaidCredit extends Credit implements Serializable {


    /**
     * PrepaidCredit constructor
     * @param accountHolder
     */
    public PrepaidCredit (User accountHolder, double initialAmount){
        super(accountHolder);
        accountType = "Prepaid Credit";
        if (initialAmount > 0 ){
            this.initialAmount = initialAmount; }
    }

    public void monthlyFees(double fee) {
        LocalDateTime currdate = LocalDateTime.now();
        if ((currdate.toString().substring(5, 7)).equals(getLastLine().substring(2, 4))) {
            setBalance(getBalance());
        } else {
            setBalance(getBalance() - fee);
        }
    }

    public String getLastLine() {
        String currLine;
        String lastLine = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader("phase2/txtfiles/date.txt"));

            while ((currLine = br.readLine()) != null) {
                lastLine = currLine;
            }
        } catch (IOException e) {
        }
        return lastLine;}
}


