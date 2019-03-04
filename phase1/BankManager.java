package phase1;

import java.util.*;

public class BankManager extends Operator {
    private static ArrayList<BankManager> bankmanagerdatabase = new ArrayList<BankManager>();
    private static int numbankmanagers = 0;
    private String username;
    private String password;

    // bankmanager constructor
    public BankManager(String username, String password) {
        super(username, password);
        this.username = username;
        this.password = password;
        numbankmanagers += 1;
        bankmanagerdatabase.add(this);
    }

    // createuser method
    public String createuser(String username, String password) {
        if ( (username.substring(0, 4)).equals("USER") ) {
            new User(username, password);
            return "the user " + username + " has been created";
        } else {
            return "invalid username for a user";
        }
    }
    // creates new user instance in userdatabase arraylist in user class

    // changepassword method
    public String changepassword(String currentpassword, String newpassword) {
        if (currentpassword.equals(this.password)) {
            this.password = newpassword;
            return "your password has been changed";
        } else {
            return "wrong current password. password unchanged";
        }
    }

    // setuserpassword method
    public String setuserpassword(User user, String newpassword) {
        user.setpassword(newpassword);
        return "user " + user + "'s password has been changed";
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
        return "number of $5 bills added to the ATM: " + num5bills + "\n" +
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

    @Override
    public String viewinfo() {

    }
    // BM will need to input user instance in the method parameter

    @Override
    public String viewbalance() {

    }
    // BM will need to input parameters: user instance, account num/type

    @Override
    public String transfer() {

    }
    // BM input parameters: user instance, amount, (from) account num/type, (to) account num/type

    @Override
    public String withdraw() {

    }
    // BM input parameters: user instance, amount, account num/type

    @Override
    public String deposit() {

    }
    // BM input parameters: user instance, amount, account num/type

    // undomostrecenttransaction method
    public String undomostrecenttransaction() {

    }
    // input parameters: user instance, account num/type
}