package phase2.FundStores.Debt;

import phase2.FundStores.Asset.Debit;
import phase2.Operators.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;


// PrepaidCredit account works like a prepaid gift card (only able to reload, balance never goes under 0, can only spend
// up to limit given + incurs 3% deduction every month)

public class PrepaidCredit extends Debit implements Serializable {
    private User user;

    /**
     * PrepaidCredit constructor
     * @param accountHolder
     * @param accountType
     */
    public PrepaidCredit (User accountHolder, String accountType){ super(accountHolder, "Prepaid Credit"); }

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
            BufferedReader br = new BufferedReader(new FileReader("./src/date.txt"));

            while ((currLine = br.readLine()) != null) {
                lastLine = currLine;
            }
        } catch (IOException e) {
        }
        return lastLine;}
}


