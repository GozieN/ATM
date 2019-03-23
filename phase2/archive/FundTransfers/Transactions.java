//package phase2.FundTransfers;
//
//import phase2.FundHolders.*;
//import java.io.FileNotFoundException;
//import java.io.PrintStream;
//
//
////ASK about account -- add print statements from here.
//
//
//public class Transactions implements java.io.Serializable {
//    protected Account senderAccount;
//    private ATM atm;
//    private String lastAction;
//    private int lastAmount;
//    private Account lastreciever;
//    private Transactions accountReceiver;
//
//
//    public Transactions(Account senderAccount) {
//        this.senderAccount = senderAccount;
//    }
//
//
//    /**
//     * Deposit amount into account
//     *
//     * @param amount Amount of money to deposit
//     */
//    public boolean depositIntoATM(int amount) {
//        if (!(amount % 5 == 0 || amount > 0)) {
//            System.out.println("Sorry, this machine only deposits cash in 5$, 10$, 20$ and 50$ bills. We also do not accept" +
//                    " negative inputs." +
//                    " At the moment, the most" + "we can deposit is" + (amount - amount % 5) + " and not" + (amount) +
//                    ".Please enter a new value");
//            return false;
//        } else {
//            atm.plus(amount);
//            depositToAccount(amount);
//            return true;
//        }
//    }
//
//    /**
//     * Get last action of user
//     *
//     * @return A string of the user's last action
//     */
//    public String getLastAction() {
//        return lastAction;
//    }
//
//    /**
//     * Set the atm
//     *
//     * @param atm Instance of ATM machine
//     */
//    public void setAtm(ATM atm) {
//        this.atm = atm;
//    }
//
//    /**
//     * Withdraw amount from account using ATM
//     *
//     * @param amount Amount of money to withdraw
//     */
//    public boolean withdrawFromATM(int amount) {
//        if (!(amount % 5 == 0) || amount > 0) {
//            System.out.println("Sorry, this machine only gives cash in 5$, 10$, 20$ and 50$ bills. We also do not accept" +
//                    " negative inputs." +
//                    " At the moment, the most" + "we can give you is" + (amount - amount % 5) + " and not" + (amount) +
//                    ".Please enter a new value");
//            return false;
//        }
//
//        atm.minus(amount);
//        withdrawFromAccount(amount);
//        return true;
//    }
//
//    /**
//     * Withdraw amount from account
//     *
//     * @param amount Amount of money to withdraw
//     */
//    public boolean withdrawFromAccount(double amount) {
//        if (!(senderAccount.getBalance() - amount > 0) && !(senderAccount instanceof ChequingAccount)) {
//            System.out.println("Sorry, you are unable to withdraw this amount from your " +
//                    senderAccount.getAccountType() + "try withdrawing a smaller amount or review your account " +
//                    "information!");
//            return false;
//        }
//        if (senderAccount instanceof Debit) {
//            if (senderAccount instanceof ChequingAccount) {
//                if ((senderAccount.getBalance() - amount) >= -100) {
//                    senderAccount.setBalance(senderAccount.getBalance() - amount);
//                }
//            } else if (senderAccount instanceof SavingsAccount) {
//                if ((senderAccount.getBalance() - amount) >= 0)
//                    senderAccount.setBalance(senderAccount.getBalance() - amount);
//            }
//        } else if (senderAccount instanceof Credit) {
//            if (senderAccount.getAccountType() == "LineOfCredit") {
//                senderAccount.setBalance(senderAccount.getBalance() - amount);
//            }
//        }
//        lastAction = "withdraw";
//
//        System.out.println("Withdrawal successful, Account: " + senderAccount.getAccountNum() +
//                " now has a decreased balance of: " + senderAccount.getBalance() + "$CAD");
//        return true;
//    }
//
//    /**
//     * Deposit amount into account
//     *
//     * @param amount Amount of money to deposit
//     */
//    public boolean depositToAccount(double amount) {
//        if (!(amount % 5 == 0) || amount > 0) {
//            System.out.println("Sorry, this machine deposits cash in 5$, 10$, 20$ and 50$ bills. We also do not accept" +
//                    " negative inputs." +
//                    " At the moment, the most" + "we can deposit is" + (amount - amount % 5) + " and not" + (amount) +
//                    ".Please enter a new value");
//            return false;
//        } else {
//            senderAccount.setBalance(senderAccount.getBalance() + amount);
//            lastAction = "deposit";
//            System.out.println("Deposit successful, Account: " + senderAccount.getAccountNum() +
//                    " now has an increased balance of: " + senderAccount.getBalance() + "CAD$");
//            return true;
//        }
//    }
//
//    /**
//     * Deposit amount into account from cheque
//     *
//     * @param amount Amount of money to deposit
//     */
//    public boolean depositChequeToAccount(double amount) {
//        if (!(amount % 5 == 0) || amount > 0) {
//            System.out.println("Sorry, this machine only deposits in 5$, 10$, 20$ and 50$ bills. We also do not accept" +
//                    " negative inputs." +
//                    " At the moment, the most" + "we can deposit is" + (amount - amount % 5) + " and not" + (amount) +
//                    ".Please enter a new value");
//            return false;
//        } else {
//            lastAction = "deposit";
//            depositToAccount(amount);
//            return true;
//        }
//    }
//
//    /**
//     * Set the previous transaction
//     *
//     * @param lastAction the previous transaction performed
//     */
//    public void setLastAction(String lastAction) {
//        this.lastAction = lastAction;
//    }
//
//    /**
//     * Set the previous transaction
//     *
//     * @param lastAmount previous transaction performed
//     */
//    public void setLastAmount(int lastAmount) {
//        this.lastAmount = lastAmount;
//    }
//
//    /**
//     * Get the last amount in the previous transaction
//     *
//     * @return lastAmount from the previous transaction performed
//     */
//    public int getLastAmount() {
//        return lastAmount;
//    }
//
//    /**
//     * Transfer funds from sender to receiver
//     *
//     * @param amount          Amount of money to be transferred
//     * @param receiverAccount Account which money will be transferred to
//     */
//    public boolean transfer(int amount, Account receiverAccount) {
//        withdrawFromAccount(amount);
//        accountReceiver = new Transactions(receiverAccount);
//        accountReceiver.depositToAccount(amount);
//        lastAction = "transfer";
//        lastreciever = receiverAccount;
//        System.out.println("Your transaction to account number: " + receiverAccount.getAccountNum() + " was successful, your new balance is: " +
//                senderAccount.getBalance());
//        return true;
//    }
//
//    /**
//     * Change balance for last receiver
//     *
//     * @return Alter the balance of the account of the last receiver
//     */
//    public void receiverBalanceAlterIncrease(double amount) {
//        accountReceiver.withdrawFromAccount(amount);
//    }
//
//    /**
//     * Pay the bill
//     *
//     * @param amount Amount of money to withdraw from account to pay bill
//     */
//    public boolean payBill(double amount) {
//        withdrawFromAccount(amount);
//        lastAction = "bill";
//        try {
//            PrintStream originalOut = System.out;
//            PrintStream fileOut = new PrintStream("phase2/outgoing.txt");
//            System.setOut(fileOut);
//
//            originalOut.println(senderAccount.getHolderName() + "paid a bill of " + Double.toString(amount));
//
//            System.setOut(originalOut);
//        } catch (FileNotFoundException ex) {
//            ex.printStackTrace();
//        }
//        return true;
//    }
//}


//package phase2.FundHolders;
//
//import phase2.FundTransfers.*;
//import phase2.Operators.*;
//import java.io.FileNotFoundException;
//import java.io.PrintStream;
//import java.io.Serializable;
//import java.util.*;
//
//
//public abstract class Account implements Transaction, ExternalTransactions, InternalTransactions, Serializable {
//    private static ArrayList<Account> accountsDatabase = new ArrayList<>();
//    private static int accountNumTotal = 0;
//    private int accountNum = accountNumTotal;
//    private String holderName;
//    private double balance;
//    public String accountType;
//    public ATM atm;
//    private Object[] transactionInfoTempHolder;
//    private Stack<Object[]> history;
//
//    /**
//     * Account class constructor
//     * @param accountHolder holder of the account
//     * @param accountType Type of account: Credit Card, Line of Credit, Chequing, or Savings
//     */
//    public Account(User accountHolder, String accountType) {
//        accountsDatabase.add(this);
//        history = new Stack<>();
//        this.accountType = accountType;
//        this.accountNumTotal++;
//        this.holderName = accountHolder.getUsername();
//        this.transactionInfoTempHolder = new Object[2];
//    }
//
//    /**
//     * Get name of account holder
//     * @return String of holder's name
//     */
//    public String getHolderName(){
//        return holderName;
//    }
//
//
//    /**
//     * Get number of account
//     * @return Int for account's number
//     */
//    public int getAccountNum(){
//        return accountNum;
//    }
//
//    public void setATM(ATM a){
//        this.atm = a;
//    }
//
//
//
//    /**
//     * Get type of account
//     * @return String of account type
//     */
//    public String getAccountType() { return accountType; }
//
//    /**
//     * Change actions performed in account history
//     */
//    public void undoTransaction(){
//        Object[] transferInfo = history.pop();
//        if (transferInfo[0].equals("bill")){
//            history.add(transferInfo);
//            System.out.println("Sorry, your last action could not be reversed because you payed a bill last or" +
//                    "have never performed a transaction on the class");
//    }else{
//            if (transferInfo[0].equals("transfer")) {
//                ((Account) transferInfo[2]).transfer((double) transferInfo[1], this);
//            } else if (transferInfo[0].equals("withdraw")) {
//                ((Account) transferInfo[2]).depositToAccount((double) transferInfo[1]);
//            }else if (transferInfo[0].equals("deposit") || transferInfo[0].equals("cheque deposit")){
//                ((Account) transferInfo[2]).withdrawFromAccount((double) transferInfo[1]);
//            }
//            System.out.println(getHolderName() + ", The last action that you performed a" + transferInfo[0] + "" +
//                    " of amount " + transferInfo[1] + " has been reversed upon your request.");
//        }
//    }
//
//
//    /**
//     * Set balance of account
//     * @param balance Total amount of money in account
//     */
//    public void setBalance(double balance){
//        this.balance = balance;
//    }
//
//    /**
//     * Get balance of account
//     * @return Int for balance of account
//     */
//    public double getBalance(){
//        return this.balance;
//    }
//
//    /**
//     * Deposit amount into account
//     * @param amount Amount of money to deposit
//     */
//    public boolean depositIntoATM(int amount) {
//        if (!validAmountInput(amount)){
//            return false;
//        }else{
//            atm.plus(amount);
//            this.depositToAccount(amount);
//            return true;
//        }
//    }
//
//    /**
//     *Withdraw amount from account using ATM
//     * @param amount Amount of money to withdraw
//     */
//    public boolean withdrawFromATM(int amount) {
//        if (!validAmountInput(amount)){
//            return false;
//        }else{
//        atm.minus(amount);
//        withdrawFromAccount(amount);
//        return true;
//    }}
//
//    /**
//     *Withdraw amount from account
//     * @param amount Amount of money to withdraw
//     */
//    public boolean withdrawFromAccount(double amount) {
//        if (!(balance - amount > 0) && !(this instanceof ChequingAccount) ){
//            System.out.println("Sorry, you are unable to withdraw this amount from your " +
//                    accountType + "try withdrawing a smaller amount or review your account " +
//                    "information!");
//            return false;
//        }
//        if(this instanceof Debit){
//            if (this instanceof ChequingAccount){
//                if ((balance - amount) >= -100){
//                    balance -= amount;}
//            }
//            else if (this instanceof SavingsAccount){
//                if ((balance - amount) >= 0){
//                    balance -= amount;
//                }
//            }}
//            else if (this instanceof Credit) {
//                if(this.getAccountType() == "LineOfCredit") {
//                    if ((balance - amount) >= 0){
//                        balance -= amount;
//                    }
//                }
//            }
//            this.updateHistory("withdraw", amount, null);
//            System.out.println("Withdrawal successful, Account: " + this.accountNum +
//                    " now has a decreased balance of: " + balance + "$CAD");
//            return true;}
//
//
//    /**
//     * Set the transaction holder
//     */
//    public void updateHistory(String action, double amount, Account receiver){
//        transactionInfoTempHolder[0] = action;
//        transactionInfoTempHolder[1] = amount;
//        transactionInfoTempHolder[2] = receiver;
//        history.add(transactionInfoTempHolder);
//        clearTransactionTempHolder();
//    }
//
//    /**
//     * Clear the transaction holder
//     */
//     public void clearTransactionTempHolder(){
//         for( int i = 0; i <3; i++){
//             transactionInfoTempHolder[i] = null;
//         }
//         transactionInfoTempHolder = null;
//     }
//
//    /**
//     * Deposit amount into account
//         * @param amount Amount of money to deposit
//         */
//        public boolean depositToAccount(double amount) {
//                this.balance =+amount;
//                System.out.println("Deposit successful, Account: " + this.accountNum +
//                        " now has an increased balance of: " + balance + "CAD$");
//                this.updateHistory("deposit", amount, null);
//
//                return true;
//            }
//
//        /**
//         * Deposit amount into account from cheque
//         * @param amount Amount of money to deposit
//         */
//        public boolean depositChequeToAccount(double amount, Account senderAccount) {
//                depositToAccount(amount);
//                this.updateHistory("cheque", amount, null);
//                return true;
//            }
//
//
//        /**
//         * Transfer funds from sender to receiver
//         * @param amount Amount of money to be transferred
//         * @param receiverAccount Account which money will be transferred to
//         */
//        public boolean transfer(double amount, Account receiverAccount) {
//                withdrawFromAccount(amount);
//                receiverAccount.depositToAccount(amount);
//                receiverAccount.updateHistory("transfer", amount, this);
//                System.out.println("Your transaction to account number: " + receiverAccount.getAccountNum() + " was successful, your new balance is: " +
//                        receiverAccount.getBalance() + "$CAD");
//                return true;
//            }
//
//
//    /**
//         * Pay the bill
//         * @param amount Amount of money to withdraw from account to pay bill
//         */
//        public boolean payBill(double amount) {
//
//                withdrawFromAccount(amount);
//                try {
//                    PrintStream originalOut = System.out;
//                    PrintStream fileOut = new PrintStream("phase2/outgoing.txt");
//                    System.setOut(fileOut);
//
//                    originalOut.println(holderName + "paid a bill of " + Double.toString(amount));
//
//                    System.setOut(originalOut);
//                } catch (FileNotFoundException ex) {
//                    ex.printStackTrace();
//                }
//                this.updateHistory("bill", amount, null);
//                return true;}
//
//            /**
//             * check if amount is usable by atm
//             * @param amount - the amount of funds being checked
//             */
//            public boolean validAmountInput(double amount){
//                return amount%5 ==0 || amount < 0;
//
//            }
//        }