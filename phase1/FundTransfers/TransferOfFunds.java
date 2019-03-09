package phase1.FundTransfers;

import phase1.FundHolders.*;
import phase1.FundTransfers.*;
import phase1.Operators.*;

//ASK about account -- add print statements from here.


public class TransferOfFunds {
    protected double amount;
    protected Account account;

    public TransferOfFunds(double amount, Account account){
        this.account = account;
        this.amount = amount;
    }

    /**
     *
     * @param amount
     * @param account
     */
    public void withdrawFromAccount(double amount, Account account) {
        account.withdraw(amount);

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


    public void depositToAccount(double amount, Account account) {
        //calls on amount withdraw method
            account.deposit(amount);
//        //deposit money into their account by entering a cheque
//        // or cash into the machine (This will be simulated by individual lines
//        // in an input file called deposits.txt. You can decide the format of the file.
//        // This will increase their balance.)
//            account.setBalance(account.getBalance() + amount);
//        //UPDATE ATM
//        System.out.println("Deposit successful, Account: " + account.getAccountNum() +
//                " now has an increased balance of: " + account.getBalance() + "CAD");
//        }
}}
