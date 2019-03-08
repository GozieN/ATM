package phase1.Operators.Workers;

import phase1.FundHolders.ATM;
import phase1.FundHolders.Account;
import phase1.Operators.Operator;
import phase1.Operators.Users.User;

import java.util.*;


public class Manager implements Observer, OperatorWorker {
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
    public void setUserPassword(User user, String newpassword) {
        user.setInitialPassword(newpassword);
        System.out.println("User " + user.getUsername() + "'s password has been changed");
    }

    // ATMsetdate method
    public void ATMsetdate(ATM atm, int day, int month, int year) { // format dd:mm:yy
       atm.setDate(day, month, year);
        System.out.println("the date has been set to " + day + ':' + month + ':' + year);
    }

    // ATMsettime method
    public void ATMsettime(ATM atm, int hour, int minute, int second) { // format hh:mm:ss
        atm.setTime(hour, minute, second);
        System.out.println("the time has been set to " + hour + ':' + minute + ':' + second);
    }

    // ATMsetnum5bills method
    public void ATMsetnum5bills(ATM atm, int num5bills) {
        atm.setNum5Bills(num5bills);
        System.out.println("the number of $5 bills in the ATM is now: " + num5bills);
    }

    // ATMgetnum5bills method
    public void ATMgetnum5bills(ATM atm) {
        System.out.println("the number of $5 bills in the ATM is: " + atm.getNum5Bills());
    }

    // ATMaddnum5bills method
    public void ATMaddnum5bills(ATM atm, int num5bills) {
        atm.addNum5Bills(num5bills);
        System.out.println("number of $5 bills added to the ATM: " + num5bills + "\n" +
                "the number of $5 bills in the ATM is now : " + atm.getNum5Bills());
    }

    // ATMsetnum10bills method
    public void ATMsetnum10bills(ATM atm, int num10bills) {
        atm.setNum10Bills(num10bills);
        System.out.println("the number of $10 bills in the ATM is now: " + num10bills);
    }

    // ATMgetnum10bills method
    public void ATMgetnum10bills(ATM atm) {
        System.out.println("the number of $10 bills in the ATM is: " + atm.getNum10Bills());
    }

    // ATMaddnum10bills method
    public void ATMaddnum10bills(ATM atm, int num10bills) {
        atm.addNum10Bills(num10bills);
        System.out.println("number of $10 bills added to the ATM: " + num10bills + "\n" +
                "the number of $10 bills in the ATM is now : " + atm.getNum10Bills());
    }

    // ATMsetnum20bills method
    public void ATMsetnum20bills(ATM atm, int num20bills) {
        atm.setNum20Bills(num20bills);
        System.out.println("the number of $20 bills in the ATM is now: " + num20bills);
    }

    // ATMgetnum20bills method
    public void ATMgetnum20bills(ATM atm) {
        System.out.println("the number of $20 bills in the ATM is: " + atm.getNum20Bills());
    }

    // ATMaddnum20bills method
    public void ATMaddnum20bills(ATM atm, int num20bills) {
        atm.addNum20Bills(num20bills);
        System.out.println("number of $20 bills added to the ATM: " + num20bills + "\n" +
                "the number of $20 bills in the ATM is now : " + atm.getNum20Bills());
    }

    // ATMsetnum50bills method
    public void ATMsetnum50bills(ATM atm, int num50bills) {
        atm.setNum5Bills(num50bills);
        System.out.println("the number of $50 bills in the ATM is now: " + num50bills);
    }

    // ATMgetnum50bills method
    public void ATMgetnum50bills(ATM atm) {
        System.out.println("the number of $50 bills in the ATM is: " + atm.getNum50Bills());
    }

    // ATMaddnum50bills method
    public void ATMaddnum50bills(ATM atm, int num50bills) {
        atm.addNum50Bills(num50bills);
        System.out.println("number of $50 bills added to the ATM: " + num50bills + "\n" +
                "the number of $50 bills in the ATM is now : " + atm.getNum50Bills());
    }

    public void singleAccountSummary(Account account) {

    }

    public void viewInfo() {

    }

    public void viewBalance(Account account) {

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