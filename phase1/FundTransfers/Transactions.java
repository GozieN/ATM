package phase1.FundTransfers;

import phase1.FundHolders.*;
import phase1.FundTransfers.*;
import phase1.Operators.*;

import java.io.FileNotFoundException;
import java.io.PrintStream;

//ASK about account -- add print statements from here.


public class Transactions {
    protected Account senderAccount;


    public Transactions(Account senderAccount){
        this.senderAccount = senderAccount;
    }

    /**
     *Withdraw amount from account
     * @param amount
     * @param senderAccount
     */
    public void withdrawFromAccount(double amount, Account senderAccount) {

//        if(account instanceof Debit){
//            if (account instanceof ChequingAccount && (account.getBalance() - amount) >= -100){
//                account.setBalance(account.getBalance() - amount);
//                //UPDATE ATM
//            }
//
//        }else if (account instanceof Credit) {
//            if(((Credit) account).isLOC) {
//                account.setBalance(account.getBalance() + amount);
//                //UPDATE ATM
//            }
//            }
//
//        System.out.println("Withdrawal successful, Account: " + account.getAccountNum() +
//                " now has a decreased balance of: " + account.getBalance() + "CAD");
        }

    /**
     * Deposit amount into account
     * @param amount
     */
    public void depositToAccount(double amount, Account senderAccount) {
        //calls on amount withdraw method
//        //deposit money into their account by entering a cheque
//        // or cash into the machine (This will be simulated by individual lines
//        // in an input file called deposits.txt. You can decide the format of the file.
//        // This will increase their balance.)
//            account.setBalance(account.getBalance() + amount);
//        //UPDATE ATM
//        System.out.println("Deposit successful, Account: " + account.getAccountNum() +
//                " now has an increased balance of: " + account.getBalance() + "CAD");
//        }
}
    /**
     * Transfer funds from sender to receiver
     */
    public void transfer(int amount, Account senderAccount, Account receiverAccount) {
        senderAccount.withdraw(amount);
        receiverAccount.deposit(amount);

        //[Angela]
        try {
            PrintStream originalOut = System.out;
            PrintStream fileOut = new PrintStream("/.Outgoing.txt");
            System.setOut(fileOut);
            originalOut.println("[Amount] transferred to [Receiver Account]");
            System.setOut(originalOut);
        } catch (FileNotFoundException ex) {ex.printStackTrace();}}


    //    /**
//     * Pay the bill using a cheque
//     * @param amount
//     * @param from
//     * @param to
//     */
//     public void payBill(int amount, Account from, Account to) {
//         from.setBalance(from.getBalance() - amount);
//         to.setBalance(to.getBalance() + amount);}
//         //CHECK specs
//
//    /**
//     * Pay the bill using cash
//     * @param cheque
//     * @param from
//     * @param to
//     */
//         public void payBill (String cheque, Account from, Account to){
//             from.setBalance(from.getBalance() - amount);
//             to.setBalance(to.getBalance() + amount);
//             //CHECK specs
//         }
}


