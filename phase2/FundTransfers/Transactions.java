package phase2.FundTransfers;

import phase2.FundHolders.*;
import java.io.FileNotFoundException;
import java.io.PrintStream;


//ASK about account -- add print statements from here.


public class Transactions implements java.io.Serializable{
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
     * @param amount Amount of money to deposit
     */
    public boolean depositIntoATM(ATM atm, int amount) {
        if (!(amount % 5 == 0 || amount > 0)){
            System.out.println("Sorry, this machine only gives cash in 5$, 10$, 20$ and 50$ bills. We also do not accept" +
                    " negative inputs." +
                    " At the moment, the most" + "we can give you is" + (amount - amount%5) + " and not" + (amount) +
                    ".Please enter a new value");
            return false;
        }else{
            atm.plus(amount);
            depositToAccount(amount);
            return true;
        }
    }

    /**
     * Get last action of user
     * @return A string of the user's last action
     */
    public String getLastAction(){
        return lastAction;
    }

    /**
     * Set the atm
     * @param atm Instance of ATM machine
     */
    public void setAtm(ATM atm) {
        this.atm = atm;
    }

    /**
     *Withdraw amount from account using ATM
     * @param amount Amount of money to withdraw
     */
    public boolean withdrawFromATM(int amount) {
        if (!(amount%5 ==0) || amount > 0){
            System.out.println("Sorry, this machine only gives cash in 5$, 10$, 20$ and 50$ bills. We also do not accept" +
                    " negative inputs." +
                    " At the moment, the most" + "we can give you is" + (amount - amount%5) + " and not" + (amount) +
                    ".Please enter a new value");
            return false;
        }

        atm.minus(amount);
        withdrawFromAccount(amount);
        return true;
    }

    /**
     *Withdraw amount from account
     * @param amount Amount of money to withdraw
     */
    public boolean withdrawFromAccount(double amount) {
        if (!(senderAccount.getBalance() - amount > 0) && !(senderAccount instanceof ChequingAccount) ){
            System.out.println("Sorry, you are unable to withdraw this amount from your " +
                    senderAccount.getAccountType() + "try withdrawing a smaller amount or review your account " +
                    "information!");
            return false;
        }
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
                senderAccount.setBalance(senderAccount.getBalance() - amount);
            }
        }
        lastAction = "withdraw";

        System.out.println("Withdrawal successful, Account: " + senderAccount.getAccountNum() +
                " now has a decreased balance of: " + senderAccount.getBalance() + "$CAD");
        return true;
    }

    /**
     * Deposit amount into account
     * @param amount Amount of money to deposit
     */
    public boolean depositToAccount(double amount) {
        if (!(amount%5 ==0) || amount > 0){
            System.out.println("Sorry, this machine only gives cash in 5$, 10$, 20$ and 50$ bills. We also do not accept" +
                    " negative inputs." +
                    " At the moment, the most" + "we can give you is" + (amount - amount%5) + " and not" + (amount) +
                    ".Please enter a new value");
            return false;
        }
        else{
            senderAccount.setBalance(senderAccount.getBalance() + amount);
            lastAction = "deposit";
            System.out.println("Deposit successful, Account: " + senderAccount.getAccountNum() +
                    " now has an increased balance of: " + senderAccount.getBalance() + "CAD$");
            return true;
        }}

    /**
     * Deposit amount into account from cheque
     * @param amount Amount of money to deposit
     */
    public boolean depositChequeToAccount(double amount) {
        if (!(amount%5 ==0) || amount > 0){
            System.out.println("Sorry, this machine only gives cash in 5$, 10$, 20$ and 50$ bills. We also do not accept" +
                    " negative inputs." +
                    " At the moment, the most" + "we can give you is" + (amount - amount%5) + " and not" + (amount) +
                    ".Please enter a new value");
            return false;
        }else{
            lastAction = "deposit";
            depositToAccount(amount);
            return true;
        }}

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
     * @param amount Amount of money to be transferred
     * @param receiverAccount Account which money will be transferred to
     */
    public boolean transfer(int amount, Account receiverAccount) {
        if (!(amount%5 ==0) || amount > 0){
            System.out.println("Sorry, this machine only gives cash in 5$, 10$, 20$ and 50$ bills. We also do not accept" +
                    " negative inputs." +
                    " At the moment, the most" + "we can give you is" + (amount - amount%5) + " and not" + (amount) +
                    ".Please enter a new value");
            return false;
        }else{
            withdrawFromAccount(amount);
            accountReceiver = new Transactions(receiverAccount);
            accountReceiver.depositToAccount(amount);
            lastAction = "transfer";
            lastreciever = receiverAccount;
            System.out.println("Your transaction to account number: " + receiverAccount.getAccountNum() + " was successful, your new balance is: " +
                    senderAccount.getBalance());
            return true;
        }}

    /**
     *Change balance for last receiver
     * @return Alter the balance of the account of the last receiver
     */
    public void receiverBalanceAlterIncrease(double amount) {
        accountReceiver.withdrawFromAccount(amount);
    }

    /**
     * Pay the bill
     * @param amount Amount of money to withdraw from account to pay bill
     */
    public boolean payBill(double amount) {
        if (!(amount%5 ==0) || amount > 0){
            System.out.println("Sorry, this machine only gives cash in 5$, 10$, 20$ and 50$ bills. We also do not accept" +
                    " negative inputs." +
                    " At the moment, the most" + "we can give you is" + (amount - amount%5) + " and not" + (amount) +
                    ".Please enter a new value");
            return false;
        }else{
            withdrawFromAccount(amount);
            lastAction = "bill";
            try {
                PrintStream originalOut = System.out;
                PrintStream fileOut = new PrintStream("phase2/outgoing.txt");
                System.setOut(fileOut);

                originalOut.println(senderAccount.getHolderName() + "paid a bill of " + Double.toString(amount));

                System.setOut(originalOut);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } return true;
        }
    }
}