package phase1.Operators.Workers;

import phase1.FundHolders.ATM;
import phase1.FundHolders.Account;
import phase1.Operators.Operator;
import phase1.Operators.Users.User;

import java.util.*;


public class Manager implements Observer, Operator {
    private static ArrayList<Manager> bankmanagerdatabase = new ArrayList<>();
    private static int numbankmanagers = 0;
//    private String username;
//    private String password;
    public ArrayList<User> users = new ArrayList<>();

    // bankmanager constructor
    public Manager() {
//        this.username = username;
//        this.password = password;
        numbankmanagers += 1;
        bankmanagerdatabase.add(this);
    }

//GOZIE -IMPLEMENT OBSERVER PATTERN FOR BM AND ATM!
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Account){
            ((User) o).addToAccountsCreated((Account) arg);
             System.out.println(((User) o).getUsername() +
                     ", the following account:" +
                     ((Account) arg).getAccountType((Account) arg) + "with account Number: "
                     + ((Account) arg).getAccountNum() +  "was created upon your request");
            }
        else if(arg instanceof String){
            System.out.println("Your initial password has been set. You are able to change it later.");
        }
        else if(arg instanceof User){
            users.add((User) arg);
            createUser(((User) arg).getUsername(), ((User) arg).getPassword());
        }
    }

    // createUser method
    public void createUser(String username, String password) {
        if ( (username.substring(0, 4)).equals("USER") ) {
            new User(username, password);
            System.out.println("The User with name: " + username + " has been created");
        } else {
            System.out.println("Invalid username for a user");
        }
    }

    // creates new user instance in userdatabase arraylist in user class

//    // changePassword method
//    public String changePassword(String currentpassword, String newpassword) {
//        if (currentpassword.equals(this.password)) {
//            this.password = newpassword;
//            return "your password has been changed";
//        } else {
//            return "wrong current password. password unchanged";
//        }
//    }

    // setuserpassword method
    public String setUserPassword(User user, String newpassword) {
        user.setInitialPassword(newpassword);
        return "User " + user.getUsername() + "'s password has been changed";
    }

    // ATMsetdate method
    public String ATMsetdate(ATM atm, int day, int month, int year) { // format dd:mm:yy
       atm.setdate(day, month, year);
       return "the date has been set to " + day + ':' + month + ':' + year;
    }
    // setter has string return type to act like a real ATM
    // and provide confirmation to the BM in the console

    // ATMsettime method
    public String ATMsettime(ATM atm, int hour, int minute, int second) { // format hh:mm:ss
        atm.settime(hour, minute, second);
        return "the time has been set to " + hour + ':' + minute + ':' + second;
    }
    // setter has string return type to act like a real ATM
    // and provide confirmation to the BM in the console

    // ATMsetnum5bills method
    public String ATMsetnum5bills(ATM atm, int num5bills) {
        atm.setnum5bills(num5bills);
        return "the number of $5 bills in the ATM is now: " + num5bills;
    }
    // setter has string return type to act like a real ATM
    // and provide confirmation to the BM in the console

    // ATMgetnum5bills method
    public String ATMgetnum5bills(ATM atm) {
        return "the number of $5 bills in the ATM is: " + atm.getnum5bills();
    }

    // ATMaddnum5bills method
    public String ATMaddnum5bills(ATM atm, int num5bills) {
        atm.addnum5bills(num5bills);
        return "number odcxzf $5 bills added to the ATM: " + num5bills + "\n" +
                "the number of $5 bills in the ATM is now : " + atm.getnum5bills();
    }

    // ATMsetnum10bills method
    public String ATMsetnum10bills(ATM atm, int num10bills) {
        atm.setnum10bills(num10bills);
        return "the number of $10 bills in the ATM is now: " + num10bills;
    }
    // setter has string return type to act like a real ATM
    // and provide confirmation to the BM in the console

    // ATMgetnum10bills method
    public String ATMgetnum10bills(ATM atm) {
        return "the number of $10 bills in the ATM is: " + atm.getnum10bills();
    }

    // ATMaddnum10bills method
    public String ATMaddnum10bills(ATM atm, int num10bills) {
        atm.addnum10bills(num10bills);
        return "number of $10 bills added to the ATM: " + num10bills + "\n" +
                "the number of $10 bills in the ATM is now : " + atm.getnum10bills();
    }

    // ATMsetnum20bills method
    public String ATMsetnum20bills(ATM atm, int num20bills) {
        atm.setnum20bills(num20bills);
        return "the number of $20 bills in the ATM is now: " + num20bills;
    }
    // setter has string return type to act like a real ATM
    // and provide confirmation to the BM in the console

    // ATMgetnum20bills method
    public String ATMgetnum20bills(ATM atm) {
        return "the number of $20 bills in the ATM is: " + atm.getnum20bills();
    }

    // ATMaddnum20bills method
    public String ATMaddnum20bills(ATM atm, int num20bills) {
        atm.addnum20bills(num20bills);
        return "number of $20 bills added to the ATM: " + num20bills + "\n" +
                "the number of $20 bills in the ATM is now : " + atm.getnum20bills();
    }

    // ATMsetnum50bills method
    public String ATMsetnum50bills(ATM atm, int num50bills) {
        atm.setnum5bills(num50bills);
        return "the number of $50 bills in the ATM is now: " + num50bills;
    }
    // setter has string return type to act like a real ATM
    // and provide confirmation to the BM in the console

    // ATMgetnum50bills method
    public String ATMgetnum50bills(ATM atm) {
        return "the number of $50 bills in the ATM is: " + atm.getnum50bills();
    }

    // ATMaddnum50bills method
    public String ATMaddnum50bills(ATM atm, int num50bills) {
        atm.addnum50bills(num50bills);
        return "number of $50 bills added to the ATM: " + num50bills + "\n" +
                "the number of $50 bills in the ATM is now : " + atm.getnum50bills();
    }

    //to implement the operator class within the same parameters as the signature, input account. loop over list of users,
    //within each user loop over account and check for a matching instance using equal methods.

//    @Override
//    public String viewInfo(User user) {
//        return user.viewInfo();
//    }
//    // BM will need to input user instance in the method parameter
//
//    @Override
//    public void viewBalance(User user) {
//        user.viewBalance();
//
//    }
//    // BM will need to input parameters: user instance, account num/type
//
//    @Override
//    public void transfer(User user, int amount, Account from, Account to) {
//        user.transfer(amount, from, to);
//    }
//    // BM input parameters: user instance, amount, (from) account num/type, (to) account num/type
//
//    @Override
//    public void withdraw(User user, int amount, Account from, Account to) {
//        user.withdraw();
//    }
//    // BM input parameters: user instance, amount, account num/type
//
//    @Override
//    public void deposit(User user, int amount, Account from, Account to) {
//        user.deposit();
//    }
//    // BM input parameters: user instance, amount, account num/type

    //ANGEL
//    public void undoMostRecentTransaction(User user, Account account) {
//    String msg=transactions.get(-1); // Gain access to last element (popping)
//    if (transactions.size()>0){
//        transactions.remove(transactions.size()-1);
//    }
//    if (){ // "deposit 20" then if deposit in msg you go here, consider ALL cases that involve gaining money
//
//    }else if(){ // "withdraw 20" then if withdraw go here, consider ALL cases that involve losing money
//
//    }
//    }
//    // input parameters: user instance, account num/type
}