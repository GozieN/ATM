package phase1.FundTransfers;

import phase1.FundHolders.*;

/**
 * Pay the bill using a cheque
 */
public class ExternalTransfers extends TransferOfFunds {
    private Account receiverAccount;
    private Account senderAccount;

    /**
     * External Transfers class constructor
     * @param amount
     * @param senderAccount
     * @param receiverAccount
     */
    public ExternalTransfers(double amount, Account senderAccount, Account receiverAccount){
        super(amount, senderAccount);
        this.receiverAccount = receiverAccount;
        this.senderAccount = senderAccount;
    }
    //USE METHOD OVERLOADING

//    /**
//     * Pay the bill using a cheque
//     * @param amount
//     * @param from
//     * @param to
//     */
//     public void payBill(int amount, Account from, Account to) {
//         from.setBalance(from.getBalance() - amount);
//         to.setBalance(to.getBalance() + amount);}
//         //CHECK specs
//
//    /**
//     * Pay the bill using cash
//     * @param cheque
//     * @param from
//     * @param to
//     */
//         public void payBill (String cheque, Account from, Account to){
//             from.setBalance(from.getBalance() - amount);
//             to.setBalance(to.getBalance() + amount);
//             //CHECK specs
//         }
     }