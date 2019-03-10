package phase1.FundHolders;

import java.util.Calendar;

public class SavingsAccount extends Debit {
    /**
     * SavingsAccount constructor
     * @param accountNum Number used to identify a specific account
     * @param holderName Name of holder of the account
     * @param balance Amount of money found in account
     */
    public SavingsAccount(int accountNum, String holderName, double balance){
        super(accountNum, holderName, balance, "SavingsAccount");
    }

//    use getdate method for interests
//    store string of month and check if string is same as month in getdate method, if changed then increase interest

    /**
     * Add monthly interest for savings account
     * @param interest Percentage amount of extra money (interest) received as a return for keeping a balance in the
     *                 user's savings account
     */
    public void monthlyInterest(double interest){
//        String currMonth = getDate().substring(12,15)
////        if (currMonth.equals(month in date)){
//                setBalance(getBalance());
//          } else {
//            double increaseBy = interest * getBalance();
//            setBalance(getBalance() - increaseBy);
    }
}