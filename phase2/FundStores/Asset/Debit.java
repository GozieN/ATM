package phase2.FundStores.Asset;

import phase2.FundStores.Account;
import phase2.Operators.BankAccountUser.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;

public abstract class Debit extends Account implements Serializable {

    private User user;
    /**
     * Debit class constructor
     * @param accountHolder Name of holder of the account
     */

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


    public Debit(User accountHolder){
        super(accountHolder);
        accountType = "Debit";
    }

    public Debit(User accountHolder, User accountHolder2){
        super(accountHolder);
        accountType = "Debit";
    }

}