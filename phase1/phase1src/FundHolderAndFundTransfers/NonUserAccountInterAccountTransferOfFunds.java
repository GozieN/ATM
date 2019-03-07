package FundHolderAndFundTransfers;

import Accounts.Account;

public class NonUserAccountInterAccountTransferOfFunds extends InterAccountTransferOfFunds {

    public NonUserAccountInterAccountTransferOfFunds(double amount, Account senderAccount, Account receiverAccount){
        super(amount, senderAccount, receiverAccount);

    }
    public void payBill() {
        withdraw(amount, senderAccount);
        deposit(amount, receiverAccount);
        //CHECK specs &coordinate with Angel for bill constructor
    }
}
