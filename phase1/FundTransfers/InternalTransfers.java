package phase1.FundTransfers;

import phase1.FundHolders.*;
import phase1.FundTransfers.*;
import phase1.Operators.*;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class InternalTransfers extends TransferOfFunds {
    private Account receiverAccount;
    private Account senderAccount;


    public InternalTransfers(double amount, Account senderAccount, Account receiverAccount){
        super(amount, senderAccount);
        this.receiverAccount = receiverAccount;
        this.senderAccount = senderAccount;

    }

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

    }
    public void eTransfer(int amount, Account from, Account to) {
        senderAccount.withdraw(amount);
        receiverAccount.deposit(amount);}

}

