package phase1.FundTransfers;

import phase1.FundHolders.*;
import phase1.FundTransfers.*;
import phase1.Operators.*;

import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * Transfer funds from sender to revciever
 */
public class InternalTransfers extends TransferOfFunds {
    private Account receiverAccount;
    private Account senderAccount;

    /**
     * InternalTransfers class constructor
     * @param amount
     * @param senderAccount
     * @param receiverAccount
     */
    public InternalTransfers(double amount, Account senderAccount, Account receiverAccount){
        super(amount, senderAccount);
        this.receiverAccount = receiverAccount;
        this.senderAccount = senderAccount;

    }

    /**
     * Transfer funds from sender to receiver
     */
    public void transfer() {
        senderAccount.withdraw(amount);
        receiverAccount.deposit(amount);

        //[Angela]
        try {
            PrintStream originalOut = System.out;
            PrintStream fileOut = new PrintStream("/.Outgoing.txt");
            System.setOut(fileOut);
            originalOut.println("[Amount] transferred to [Receiver Account]");
            System.setOut(originalOut);
        } catch (FileNotFoundException ex) {ex.printStackTrace();}

        /**
         * Transfer funds from sender to receiver
         */
    }
    public void eTransfer(int amount, Account from, Account to) {
        senderAccount.withdraw(amount);
        receiverAccount.deposit(amount);}

}

