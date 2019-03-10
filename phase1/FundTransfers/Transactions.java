package phase1.FundTransfers;

import phase1.FundHolders.*;
import java.io.FileNotFoundException;
import java.io.PrintStream;


//ASK about account -- add print statements from here.


public class Transactions {
    protected Account senderAccount;
    private ATM atm;
    private String lastAction;
    private int lastAmount;
    private Account lastreciever;
    private Transactions accountReceiver;





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
     *
     * @return String a sting of the last action
     */
    public String getLastAction(){
        return lastAction;
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
        lastAction = "withdraw";

        System.out.println("Withdrawal successful, Account: " + senderAccount.getAccountNum() +
                " now has a decreased balance of: " + senderAccount.getBalance() + "$CAD");
    }

    /**
     * Deposit amount into account
     * @param amount
     */
    public void depositToAccount(double amount) {

        senderAccount.setBalance(senderAccount.getBalance() + amount);
        lastAction = "deposit";
        System.out.println("Deposit successful, Account: " + senderAccount.getAccountNum() +
                " now has an increased balance of: " + senderAccount.getBalance() + "CAD$");
    }

    /**
     * Deposit amount into account
     * @param amount
     */
    public void depositChequeToAccount(double amount) {
        lastAction = "deposit";
        depositToAccount(amount);
    }

    /**
     * Set the previous transaction
     * @param lastAction the previous transaction performed
     */
    public void setLastAction(String lastAction) {
        this.lastAction = lastAction;
    }

    /**
     * Set the previous transaction
     * @param lastAmount previous transaction performed
     */
    public void setLastAmount(int lastAmount) {
        this.lastAmount = lastAmount;
    }

    /**
     * Get the last amount in the previous transaction
     * @return lastAmount from the previous transaction performed
     */
    public int getLastAmount() {
        return lastAmount;
    }

    /**
     * Transfer funds from sender to receiver
     */
    public void transfer(int amount, Account receiverAccount) {
        withdrawFromAccount(amount);
        accountReceiver = new Transactions(receiverAccount);
        accountReceiver.depositToAccount(amount);
        lastAction = "transfer";
        lastreciever = receiverAccount;
    }

    /**
     *
     * @return Alter the balance of the account of the last receiver
     */
    public void receiverBalanceAlterIncrease(double amount) {
        accountReceiver.withdrawFromAccount(amount);
    }

    /**
     * Pay the bill
     * @param amount

     */
     public void payBill(double amount) {
         withdrawFromAccount(amount);
         lastAction = "bill";
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


