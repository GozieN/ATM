package phase2.FundHolders;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;


public class SavingsAccount extends Debit implements java.io.Serializable {
    /**
     * SavingsAccount constructor
     *
     * @param accountNum Number used to identify a specific account
     * @param holderName Name of holder of the account
     * @param balance    Amount of money found in account
     */
    public SavingsAccount(int accountNum, String holderName, double balance) {
        super(accountNum, holderName, balance, "SavingsAccount");
    }

    /**
     * Helper function to get last line of date.txt file
     *
     * @return last line on file
     */
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
        return lastLine;
    }

    /**
     * Add monthly interest for savings account
     *
     * @param interest Percentage amount of extra money (interest) received as a return for keeping a balance in the
     *                 user's savings account
     */
    public void monthlyInterest(double interest) {
        LocalDateTime currdate = LocalDateTime.now();
        if ((currdate.toString().substring(5, 7)).equals(getLastLine().substring(2, 4))) {
            setBalance(getBalance());
        } else {
            double increaseBy = interest * getBalance();
            setBalance(getBalance() - increaseBy);
        }
    }
}
