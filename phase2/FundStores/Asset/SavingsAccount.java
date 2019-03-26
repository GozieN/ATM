package phase2.FundStores.Asset;

import phase2.Operators.BankAccountUser.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;

public class SavingsAccount extends Debit implements java.io.Serializable {

    private User user;
    /**
     * SavingsAccount constructor
     *
     * @param accountHolder Name of holder of the account
     */
    public SavingsAccount(User accountHolder) {
        super(accountHolder, "SavingsAccount");
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