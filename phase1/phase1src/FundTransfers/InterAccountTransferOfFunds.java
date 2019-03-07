package FundTransfers;

import FundHolders.Account;

public class InterAccountTransferOfFunds extends TransferOfFunds {
    protected Account senderAccount;
    protected Account receiverAccount;

    public InterAccountTransferOfFunds(double amount, Account senderAccount, Account receiverAccount){
        super(amount, senderAccount);
        this.amount = amount;
        this.senderAccount = this.account;
        this.receiverAccount = receiverAccount;
    }


    public double getAmount() {
        return amount;
    }

    public Account getReceiverAccount() {
        return receiverAccount;
    }

    public Account getSenderAccount() {
        return senderAccount;
    }

}
