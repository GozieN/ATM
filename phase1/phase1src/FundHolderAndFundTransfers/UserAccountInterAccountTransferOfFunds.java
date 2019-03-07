package FundHolderAndFundTransfers;

import Accounts.Account;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class UserAccountInterAccountTransferOfFunds extends InterAccountTransferOfFunds {

    public UserAccountInterAccountTransferOfFunds(double amount, Account senderAccount, Account receiverAccount){
        super(amount, senderAccount, receiverAccount);
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

    //CONSIDER DATA CLUMPING CODE SMELL - AVOID
    public void eTransfer(int amount, Account from, Account to) {
        senderAccount.withdraw(amount);
        receiverAccount.deposit(amount);}

}

