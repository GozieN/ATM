package phase2.FundStores;

import phase2.Operators.BankWorker.ATMMaintainer;
import phase2.Operators.BankWorker.BankManager;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class ATM implements Serializable {
    private static final long serialVersionUID = 1L;

    private static int num5bills = 100;
    private static int num10bills = 100;
    private static int num20bills = 100;
    private static int num50bills = 100;
    private Calendar cal = new GregorianCalendar();
    private BankManager BM =  new BankManager("", "");


    /**
     * ATM class constructor
     * @param fiveBills The number of $5 bills in ATM
     * @param tenBills The number of $10 bills in ATM
     * @param twentyBills The number of $20 bills in ATM
     * @param fiftyBills The number of $50 bills in ATM
     */
    public ATM(int fiveBills, int tenBills, int twentyBills, int fiftyBills) {
//         this.num5bills = num5bills;
//         this.num10bills = num10bills;
//         this.num20bills = num20bills;
//         this.num50bills = num50bills;
        num5bills = fiveBills;
        num10bills = tenBills;
        num20bills = twentyBills;
        num50bills = fiftyBills;
        this.cal.setTimeZone(TimeZone.getTimeZone("EST"));
         //this.BM = new BankManagerMenus();
    }

    /**
     * ATM class constructor
     */
    public ATM() {
        this.cal.setTimeZone(TimeZone.getTimeZone("EST"));
        //this.BM = new BankManagerMenus();
    }

    /**
     * Set the date. Format dd:mm:yyyy
     * @param day Day of year
     * @param month Month of year
     * @param year Year
     */
    public String setDate(int day, int month, int year) throws IOException {
        DateTimeFormatter d = DateTimeFormatter.ofPattern("ddmmyyyy HH:mm:ss");

        BufferedReader input = new BufferedReader(new FileReader("phase2/txtfiles/date.txt"));
        String last, line;
        last = "";
        line = input.readLine();

        if (line == null) {
            this.cal.set(Calendar.DATE, day);
            this.cal.set(Calendar.MONTH, month);
            this.cal.set(Calendar.YEAR, year);

        } else {
            while ((line = input.readLine()) != null) {
                last = line;
            }

            LocalDateTime yesterday = LocalDateTime.of(Integer.parseInt(last.substring(4, 8)),
                    Integer.parseInt(last.substring(2, 4)), Integer.parseInt(last.substring(0, 2)),
                    Integer.parseInt(last.substring(9, 11)), Integer.parseInt(last.substring(12, 14)));


            LocalDateTime resetToday = LocalDateTime.now();
            resetToday = yesterday.plusDays(1);

            String today = d.format(resetToday);

            this.cal.set(Calendar.DATE, Integer.parseInt(today.substring(0, 2)));
            this.cal.set(Calendar.MONTH, Integer.parseInt(today.substring(2, 4)));
            this.cal.set(Calendar.YEAR, Integer.parseInt(today.substring(4, 8)));

        }

        return this.cal.toString();
    }

    /**
     * Get the date
     * @return A string indicating current date
     */
    public String getDate() throws IOException{
        return this.cal.getTime().toString().substring(4, 10) + ", " + cal.getTime().toString().substring(24, 28);
//        return this.setDate(01, 01, 2019);
    }

    /**
     * Set the time
     * @param hour Hour of day
     * @param minute Minute of day
     * @param second Second of day
     */
    public String setTime(int hour, int minute, int second) throws IOException{ // format hh:mm:ss

        DateTimeFormatter d = DateTimeFormatter.ofPattern("ddmmyyyy HH:mm:ss");

        BufferedReader input = new BufferedReader(new FileReader("phase2/txtfiles/date.txt"));
        String last, line;
        last = "";
        line = input.readLine();

        if (line == null) {
            this.cal.set(Calendar.HOUR, hour);
            this.cal.set(Calendar.MINUTE, minute);
            this.cal.set(Calendar.SECOND, second);

        } else {
            while ((line = input.readLine()) != null) {
                last = line;
            }

            LocalDateTime yesterday = LocalDateTime.of(Integer.parseInt(last.substring(4, 8)),
                    Integer.parseInt(last.substring(2, 4)), Integer.parseInt(last.substring(0, 2)),
                    Integer.parseInt(last.substring(9, 11)), Integer.parseInt(last.substring(12, 14)));


            LocalDateTime resetToday = LocalDateTime.now();
            resetToday = yesterday.plusDays(1);

            String today = d.format(resetToday);

            this.cal.set(Calendar.HOUR, Integer.parseInt(today.substring(9, 11)));
            this.cal.set(Calendar.MINUTE, Integer.parseInt(today.substring(12, 14)));
            this.cal.set(Calendar.SECOND, Integer.parseInt(today.substring(15) + today.substring(16)));
        }

        return this.cal.toString();
    }

    /**
     * Get the time
     * @return String indicating the current time on a 24-hour clock
     */
    public String getTime() throws IOException{
        return " " + this.cal.getTime().toString().substring(11, 19);
//        return this.setTime(00,00,00);

    }

    /**
     * Set the number of $5 bills
     * @param numBills Number of $5 bills
     */
    public void setNum5Bills(int numBills) {
        num5bills = numBills;
    }

    /**
     * Get the number of 5$ bills
     * @return Int of $5 bills in ATM machine
     */
    public int getNum5Bills() {
        return num5bills;
    }

    /**
     * Set the number of 10$ bills
     * @param numBills Number of $10 bills
     */
    public void setNum10Bills(int numBills) {
        num10bills = numBills;
    }

    /**
     * Get the number of $10 bills
     * @return Int of $10 bills in ATM machine
     */
    public int getNum10Bills() {
        return num10bills;
    }

    /**
     * Set the number of 20$ bills
     * @param numBills Number of $20 bills
     */
    public void setNum20Bills(int numBills) {
        num20bills = numBills;
    }

    /**
     * Get the number of 20$ bills
     * @return Int of $20 bills in ATM machine
     */
    public int getNum20Bills() {
        return num20bills;
    }

    /**
     * Set the number of 50$ bills
     * @param numBills Number of $50 bills
     */
    public void setNum50Bills(int numBills) {
        num50bills = numBills;
    }

    /**
     * Get the number of 50$ bills
     * @return Int of $50 bills in ATM machine
     */
    public int getNum50Bills() {
        return num50bills;
    }

     /**
     * Increase the number of bills in the ATM
     * @param dollarAmount The amount of money added to ATM machine
     */
    // plus bills into ATM method (from deposit methods)
     public void plus(int dollarAmount) {
         ATMMaintainer am = new ATMMaintainer();
         am.setBm(BM);
         am.FeedInATMBills(dollarAmount, this);
     }

    /**
     * Decrease the number of bills in the ATM
     * @param dollarAmount The amount of money deducted from ATM machine
     */
    // minus bills into ATM method (from withdraw methods)
    public void minus(int dollarAmount) {
        ATMMaintainer am = new ATMMaintainer();
        am.EmptyOutATMBills(dollarAmount, this);
        am.setBm(BM);
    }

    /**
     * Validate that user is able to withdraw x amount of money from ATM
     * @param amount Amount of money to be withdrawn
     * @return Boolean if user is able to withdraw successfully from ATM
     */
    public boolean validWithdraw(double amount){
        if ((amount < 1000) && (amount < checkATMAmount())){
            return true;
        }
            System.out.println("Sorry, you are unable to make a withdrawal at the moment, please input" +
                    "a lower amount and try again.");
            return false;
    }

    /**
     * Add total amount of money in ATM
     * @return Double of total amount of money in ATM
     */
    public double checkATMAmount() {
        double totalInATM = 0;
        totalInATM += (getNum5Bills() * 5);
        totalInATM += (getNum10Bills() * 10);
        totalInATM += (getNum20Bills() * 20);
        totalInATM += (getNum50Bills() * 50);
        return totalInATM;
    }

    /**
     * Alert to show need for restock if number of bills are low
     */
    public void restock () {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("phase2/txtfiles/alerts.txt"));
            if (num5bills < 20) {
                writer.write("Five dollar bills low in stock!\n");
            }

            if (num10bills < 20) {
                writer.write("Ten dollar bills low in stock!\n");
            }

            if (num20bills < 20) {
                writer.write("Twenty dollar bills low in stock!\n");
            }

            if (num50bills < 20) {
                writer.write("Fifty dollar bills low in stock!\n");
            }

            writer.close();
            BM.restockFromFile(this);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}