package phase2.Operators.BankWorker;

import phase2.Operators.BankAccountUser.User;

public class BankTeller extends BankEmployee {

    /**
     * constructor for bank teller instance
     * @param username username of the bank teller
     * @param password pass of the bank teller
     */
    public BankTeller(String username, String password){
        super(username, password);
    }

    /**
     * initialize deposit by user
     * @param user the user making the deposit
     * @param amount the amount of money being deposited
     */
    public void InitialUserInputDeposit(User user, double amount){

    }

    /**
     * getter for the history of user bills
     * @param user the user whose bill we need to get
     */
    public void getUserBillHistory(User user){
        //READ OUTGOING.TXT AND PRINT USER HISTORY
    }
}


// read from file
// if empty{
// create objects and write to file }
//else{
//
//        }

