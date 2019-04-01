package phase2.FundStores.Asset;

import phase2.Operators.BankAccountUser.PointSystemUser;
import phase2.Operators.BankAccountUser.User;
import phase2.FundStores.Asset.Debit;
import phase2.FundStores.Account;
import java.io.Serializable;

public class StudentAccount extends Debit implements Serializable {
    private int numTransfers;
    private double saveFor;
    private String saveUntil;

    /**
     * StudentAccount constructor
     * @param accountHolder Name of holder of account
     */
    public StudentAccount(User accountHolder){
        super(accountHolder);
        setBalance(50);
    }

    /**
     * Withdraw money from account
     * @param amount Amount of money to be withdrawn from account
     * @return Boolean if withdrawal is successful or not
     */
    @Override
    public boolean withdrawFromAccount(double amount){
        if (((getBalance() - amount) >= 0) && (numTransfers < 20)) {
            setBalance(getBalance() - amount);
            numTransfers += 1;
            this.updateHistory("withdraw", amount, null);
            System.out.println("Withdrawal successful, Account: " + this.getAccountNum() +
                    " now has a decreased balance of: " + this.getBalance() + "$CAD");
            ((PointSystemUser) getAccountHolder()).setNumPointsIncrease();
            return true;
        }
        else{
            System.out.println("Withdrawal unsuccessful monthly withdrawal limit reached, Account: " +
                    this.getAccountNum() + " now has a balance of: " + this.getBalance() + "$CAD");
            return false;
        }
    }

    /**
     * Update the number of transfers made in account
     */
    public void updateNumofTransfers() {
        if (("01").equals(getLastLine().substring(0, 2))) {
            this.numTransfers = 0;
        }
    }

    /**
     * Determine interest for start of savings
     * @param amount Amount of money in savings
     * @param year Number of years
     */
    public void startSaving(double amount, int year){
        if(amount < getBalance()){
            setBalance(getBalance() - amount);
            int interest;
            if (year == 1){
                interest = 6;
            }
            else if((year > 1) && (year <= 8)){
                interest = 8;
            }
            else{
                interest = 9;
            }
            int x = Integer.parseInt(getLastLine().substring(4, 8)) + year;
            saveFor += amount;
            saveUntil = getLastLine().substring(0, 3) + x + interest;
            System.out.println("A savings for your student account has been opened. Your savings scheme now has : " +
                    this.saveFor + "CAD. This money cannot " +
                    "accessed by you unless you break the savings scheme");
        }
        else{
            System.out.println("Your balance is below the amount you want to add to your savings scheme...");
        }
    }

    /**
     * Allow student to opt out of plan
     */
    public void breakSaveFor(){
        String x = getLastLine().substring(0, 8);
        String compareto = saveUntil.substring(4, 8) + saveUntil.substring(2, 4) + saveUntil.substring(0, 2);
        String y = x.substring(4, 8) + x.substring(2, 4) + x.substring(0, 2);
        if(Integer.parseInt(y) >= Integer.parseInt(compareto)){
            setBalance(getBalance() + (saveFor + (saveFor * Integer.parseInt(saveUntil.substring(8, 9))/100)));
        }
        else{
            setBalance(getBalance() + saveFor);
        }
        saveFor = 0;
        saveUntil = "";
    }

    /**
     * Reading from external file
     * @return Boolean to check if transaction is added to bill
     */
    @Override
    public boolean addToBill() {
        return false;
    }
}
