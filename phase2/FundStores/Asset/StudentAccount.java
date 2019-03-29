package phase2.FundStores.Asset;

import phase2.Operators.BankAccountUser.PointSystemUser;
import phase2.Operators.BankAccountUser.User;
import phase2.FundStores.Asset.Debit;
import phase2.FundStores.Account;
import java.io.Serializable;

public class StudentAccount extends Debit implements Serializable {
    private int notrasfers;
    private double savefor;
    private String savetilldate;

    public StudentAccount(User accountHolder){
        super(accountHolder);
        setBalance(50);
    }

    @Override
    public boolean withdrawFromAccount(double amount){
        if (((getBalance() - amount) >= 0) && (notrasfers < 20)) {
            setBalance(getBalance() - amount);
            notrasfers += 1;
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

    public void updateNotransfers(double interest) {
        if (("01").equals(getLastLine().substring(0, 2))) {
            this.notrasfers = 0;
        }
    }

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
            savefor += amount;
            savetilldate = getLastLine().substring(0, 3) + x + interest;
            System.out.println("A savings for your student account has been opened. Your savings scheme now has : " +
                    this.savefor + "CAD. This money cannot " +
                    "accessed by you unless you break the savings scheme");
        }
        else{
            System.out.println("Your balance is below the amount you want to add to your savings scheme...");
        }
    }

    public void breakSaveFor(){
        String x = getLastLine().substring(0, 8);
        String compareto = savetilldate.substring(4, 8) + savetilldate.substring(2, 4) + savetilldate.substring(0, 2);
        String y = x.substring(4, 8) + x.substring(2, 4) + x.substring(0, 2);
        if(Integer.parseInt(y) >= Integer.parseInt(compareto)){
            setBalance(getBalance() + (savefor + (savefor * Integer.parseInt(savetilldate.substring(8, 9))/100)));
        }
        else{
            setBalance(getBalance() + savefor);
        }
        savefor = 0;
        savetilldate = "";
    }
}
