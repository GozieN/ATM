package phase1.FundTransfers;

import phase1.FundHolders.*;


public class ExternalTransfers extends TransferOfFunds {
    private Account receiverAccount;
    private Account senderAccount;

    ExternalTransfers(double amount, Account senderAccount, Account receiverAccount){
        super(amount, senderAccount);
        this.receiverAccount = receiverAccount;
        this.senderAccount = senderAccount;
    }
    //USE METHOD OVERLOADING!
    //    public void payBill(int amount, Account from, Account to) {
//        from.setBalance(from.getBalance() - amount);
//        to.setBalance(to.getBalance() + amount);
//        //CHECK specs

    //    public void payBill(String cheque, Account from, Account to) {
//        from.setBalance(from.getBalance() - amount);
//        to.setBalance(to.getBalance() + amount);
//        //CHECK specs
}
