package phase2.FundHolders;
import com.sun.istack.internal.Nullable;
import phase2.Operators.*;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.*;


public abstract class Account implements Serializable {
    private static ArrayList<Account> accountsDatabase = new ArrayList<>();
    private static int accountNumTotal = 0;
    private int accountNum = accountNumTotal;
    private String holderName;
    private double balance;
    public String accountType;
    public ATM atm;
    private Object[] transactionInfoTempHolder;
    private Stack<Object[]> history;
    private double creditLimit;

    /**
     * Account class constructor
     * @param accountHolder holder of the account
     * @param accountType Type of account: Credit Card, Line of Credit, Chequing, or Savings
     */
    public Account(User accountHolder, String accountType) {
        accountsDatabase.add(this);
        history = new Stack<>();
        this.accountType = accountType;
        this.accountNumTotal++;
        this.holderName = accountHolder.getUsername();
        this.transactionInfoTempHolder = new Object[2];
    }

    /**
     * Get name of account holder
     * @return String of holder's name
     */
    public String getHolderName(){
        return holderName;
    }


    /**
     * Get number of account
     * @return Int for account's number
     */
    public int getAccountNum(){
        return accountNum;
    }

    public void setATM(ATM a){
        this.atm = a;
    }



    /**
     * Get type of account
     * @return String of account type
     */
    public String getAccountType() { return accountType; }

    /**
     * Change actions performed in account history
     */
    public void undoTransaction(){
        Object[] transferInfo = history.pop();
        if (transferInfo[0].equals("bill")){
            history.push(transferInfo);
            System.out.println("Sorry, your last action could not be reversed because you payed a bill last or" +
                    "have never performed a transaction on the class");
    }else{
            if (transferInfo[0].equals("transfer")) {
                ((Account) transferInfo[2]).transfer((double) transferInfo[1], this);
            } else if (transferInfo[0].equals("withdraw")) {
                ((Account) transferInfo[2]).depositToAccount((double) transferInfo[1]);
            }else if (transferInfo[0].equals("deposit") || transferInfo[0].equals("cheque deposit")){
                ((Account) transferInfo[2]).withdrawFromAccount((double) transferInfo[1]);
            }
            System.out.println(getHolderName() + ", The last action that you performed a" + transferInfo[0] + "" +
                    " of amount " + transferInfo[1] + " has been reversed upon your request.");
        }
    }


    /**
     * Set balance of account
     * @param balance Total amount of money in account
     */
    public void setBalance(double balance){
        this.balance = balance;
    }

    /**
     * Get balance of account
     * @return Int for balance of account
     */
    public double getBalance(){
        return this.balance;
    }

    /**
     * Deposit amount into account
     * @param amount Amount of money to deposit
     */
    public boolean depositIntoATM(int amount) {
        if (!validAmountInput(amount)){
            return false;
        }else{
            atm.plus(amount);
            this.depositToAccount(amount);
            return true;
        }
    }

    public void setCreditLimit(double creditLimit) { this.creditLimit = creditLimit;}

    public double getCreditLimit() {return this.creditLimit;}

    /**
     *Withdraw amount from account using ATM
     * @param amount Amount of money to withdraw
     */
    public boolean withdrawFromATM(int amount) {
        if (!validAmountInput(amount)){
            return false;
        }else{
        atm.minus(amount);
        withdrawFromAccount(amount);
        return true;
    }}

    /**
     *Withdraw amount from account
     * @param amount Amount of money to withdraw
     */
    public boolean withdrawFromAccount(double amount) {
        if (!(balance - amount > 0) && !(this instanceof ChequingAccount) ){
            System.out.println("Sorry, you are unable to withdraw this amount from your " +
                    accountType + "try withdrawing a smaller amount or review your account " +
                    "information!");
            return false;
        }
        if(this instanceof Debit){
            if (this instanceof ChequingAccount){
                if ((balance - amount) >= -100){
                    balance -= amount;}
            }
            else if (this instanceof SavingsAccount){
                if ((balance - amount) >= 0){
                    balance -= amount;
                }
            }}
            // withdrawFromAccount for credit accounts means to "pay bills"
            else if (this instanceof Credit) {
                if(this.getAccountType() == "LineOfCredit") {
                        balance -= amount;
                }else {
                    balance -= amount;
                }
            }
            this.updateHistory("withdraw", amount, null);
            System.out.println("Withdrawal successful, Account: " + this.accountNum +
                    " now has a decreased balance of: " + balance + "$CAD");
            return true;}



    /**
     * Set the transaction holder
     */
    public void updateHistory(String action, double amount, @Nullable Account receiver){
        transactionInfoTempHolder[0] = action;
        transactionInfoTempHolder[1] = amount;
        transactionInfoTempHolder[2] = receiver;
        history.add(transactionInfoTempHolder);
        clearTransactionTempHolder();
    }

    /**
     * Clear the transaction holder
     */
     public void clearTransactionTempHolder(){
         for( int i = 0; i <3; i++){
             transactionInfoTempHolder[i] = null;
         }
         transactionInfoTempHolder = null;
     }

    /**
     * Deposit amount into account
         * @param amount Amount of money to deposit
         */
        public boolean depositToAccount(double amount) {
                this.balance =+amount;
                System.out.println("Deposit successful, Account: " + this.accountNum +
                        " now has an increased balance of: " + balance + "CAD$");
                this.updateHistory("deposit", amount, null);
                return true;
            }

        /**
         * Deposit amount into account from cheque
         * @param amount Amount of money to deposit
         */
        public boolean depositChequeToAccount(double amount) {
                depositToAccount(amount);
                this.updateHistory("cheque", amount, null);
                return true;
            }


        /**
         * Transfer funds from sender to receiver
         * @param amount Amount of money to be transferred
         * @param receiverAccount Account which money will be transferred to
         */
        public boolean transfer(double amount, Account receiverAccount) {
                withdrawFromAccount(amount);
                receiverAccount.depositToAccount(amount);
                receiverAccount.updateHistory("transfer", amount, this);
                System.out.println("Your transaction to account number: " + receiverAccount.getAccountNum() + " was successful, your new balance is: " +
                        receiverAccount.getBalance() + "$CAD");
                return true;
            }

        /**
         * View the last action performed in this account/
         */
        public String viewLastAction() {
            Object[] lastActionInfo = history.pop();
            history.push(lastActionInfo);
            if (lastActionInfo[2] == null){
            String s = "Your most recent action fell under the category: " + lastActionInfo[0] + "\n with " +
                    "an amount of: " + lastActionInfo[1];
            return s;}
            else{
                String s ="Your most recent action fell under the category: " + lastActionInfo[0] + "\n with " +
                        "an amount of: " + lastActionInfo[1] + "\n " +
                        "To account number: " + (((Account) lastActionInfo[2]).getAccountNum());
            return s;}
            }


    /**
         * Pay the bill
         * @param amount Amount of money to withdraw from account to pay bill
         */
        public boolean payBill(double amount) {

                withdrawFromAccount(amount);
                try {
                    PrintStream originalOut = System.out;
                    PrintStream fileOut = new PrintStream("phase2/outgoing.txt");
                    System.setOut(fileOut);

                    originalOut.println(holderName + "paid a bill of " + Double.toString(amount));

                    System.setOut(originalOut);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                this.updateHistory("bill", amount, null);
                return true;}

            /**
             * check if amount is usable by atm
             * @param amount - the amount of funds being checked
             */
            public boolean validAmountInput(double amount){
                return amount%5 ==0 || amount < 0;

            }


        public boolean addToBill(double amount) {

            if (this instanceof Credit) {
                if (this.getAccountType() == "LineOfCredit") {
                    if ((balance + amount) > getCreditLimit()) {
                        System.out.println("Sorry, you are unable to complete your transaction to" + accountType +
                                "as you have reached your credit limit");
                    } else if ((balance + amount) < getCreditLimit()) {
                        depositToAccount(amount);
                    } else {
                        if ((balance + amount) > getCreditLimit()) {
                            System.out.println("Sorry, you are unable to complete your transaction to" + accountType +
                                    "as you have reached your credit limit");
                        } else if ((balance + amount) < getCreditLimit()) {
                            depositToAccount(amount);
                        }
                    }
                }
            }
//            this.updateHistory("");
            System.out.println("Transaction completed, the balance in " + accountType + "is now: " + balance);
            return true; }

}