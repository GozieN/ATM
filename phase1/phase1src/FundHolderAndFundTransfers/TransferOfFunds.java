package FundHolderAndFundTransfers;

import Accounts.Account;
import Accounts.ChequingAccount;
import Accounts.Debit;
import Accounts.Credit;


public class TransferOfFunds {
    protected double amount;
    protected Account account;
    public TransferOfFunds(double amount, Account account){
        this.account = account;
        this.amount = amount;
    }

    public void withdraw(double amount, Account account) {
        if(account instanceof Debit){
            if (account instanceof ChequingAccount && (account.getBalance() - amount) >= -100){
                account.setBalance(account.getBalance() - amount);
                //UPDATE ATM
            }

        }else if (account instanceof Credit) {
            if(((Credit) account).isLineOfCreditAccount()) {
                account.setBalance(account.getBalance() + amount);
                //UPDATE ATM

            }
            }

        System.out.println("Withdrawal successful, Account: " + account.getAccountNum() +
                " now has a decreased balance of: " + account.getBalance() + "CAD");
        }


    public void deposit(double amount, Account account) {
        //deposit money into their account by entering a cheque
        // or cash into the machine (This will be simulated by individual lines
        // in an input file called deposits.txt. You can decide the format of the file.
        // This will increase their balance.)
            account.setBalance(account.getBalance() + amount);
        //UPDATE ATM
        System.out.println("Deposit successful, Account: " + account.getAccountNum() +
                " now has an increased balance of: " + account.getBalance() + "CAD");
        }
}
