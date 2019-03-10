package phase1.FundTransfers;

import phase1.FundHolders.*;
import java.io.FileNotFoundException;
import java.io.PrintStream;


//ASK about account -- add print statements from here.


public class Transactions {
    protected Account senderAccount;
    private  String cheque;
    private ATM atm;


    public Transactions(Account senderAccount){
        this.senderAccount = senderAccount;
    }


    /**
     * Deposit amount into account
     * @param amount
     */
    public void depositIntoATM(ATM atm, int amount) {
        atm.plus(amount);
    }

    /**
     * Set the atm
     * @param atm
     */
    public void setAtm(ATM atm) {
        this.atm = atm;
    }

    /**
     *Withdraw amount from account
     * @param amount
     */
    public void withdrawFromATM(ATM atm, int amount) {
        atm.minus(amount);
    }

    /**
     *Withdraw amount from account
     * @param amount
     */
    public void withdrawFromAccount(double amount) {

        if(senderAccount instanceof Debit){
            if (senderAccount instanceof ChequingAccount){
                if ((senderAccount.getBalance() - amount) >= -100){
                senderAccount.setBalance(senderAccount.getBalance() - amount);}
            }
            else if (senderAccount instanceof SavingsAccount){
                if ((senderAccount.getBalance() - amount) >= 0)
                    senderAccount.setBalance(senderAccount.getBalance() - amount);
            }
        }
        else if (senderAccount instanceof Credit) {
            if(senderAccount.getAccountType() == "LineOfCredit") {
                senderAccount.setBalance(senderAccount.getBalance() + amount);
            }
        }
//
        System.out.println("Withdrawal successful, Account: " + senderAccount.getAccountNum() +
                " now has a decreased balance of: " + senderAccount.getBalance() + "$CAD");
    }

    /**
     * Deposit amount into account
     * @param amount
     */
    public void depositToAccount(double amount) {

        senderAccount.setBalance(senderAccount.getBalance() + amount);
        //UPDATE ATM
        System.out.println("Deposit successful, Account: " + senderAccount.getAccountNum() +
                " now has an increased balance of: " + senderAccount.getBalance() + "CAD");
    }

    /**
     * Deposit amount into account
     * @param amount
     */
    public void depositChequeToAccount(double amount) {
        depositToAccount(amount);
    }

    /**
     * Transfer funds from sender to receiver
     */
    public void transfer(int amount, Account receiverAccount) {
        withdrawFromAccount(amount);
        Transactions accountReceiver = new Transactions(receiverAccount);
        accountReceiver.depositToAccount(amount);

    }

    public void createCheque(String recipient, double amount){


    }

    /**
     * Pay the bill
     * @param amount

     */
     public void payBill(double amount) {
         withdrawFromAccount(amount);
         try {
             PrintStream originalOut = System.out;
             PrintStream fileOut = new PrintStream("/.outgoing.txt");
             System.setOut(fileOut);

             originalOut.println(senderAccount.getHolderName() + "paid a bill of " + Double.toString(amount));

             System.setOut(originalOut);
         } catch (FileNotFoundException ex) {
             ex.printStackTrace();
         }

     }

}


