package phase1.FundHolders;

import phase1.FundHolders.*;
import phase1.FundTransfers.*;
import phase1.Operators.*;

import java.util.*;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class ATM {
    private int num5bills = 100;
    private int num10bills = 100;
    private int num20bills = 100;
    private int num50bills = 100;
    private Calendar cal = new GregorianCalendar();
    //private BankManager BM;

    /**
     * ATM class constructor
     * @param num5bills
     * @param num10bills
     * @param num20bills
     * @param num50bills
     */
    public ATM(int num5bills, int num10bills, int num20bills, int num50bills) {
         this.num5bills = num5bills;
         this.num10bills = num10bills;
         this.num20bills = num20bills;
         this.num50bills = num50bills;
         this.cal.setTimeZone(TimeZone.getTimeZone("EST"));
         //this.BM = new BankManager();
    }

    /**
     * ATM class constructor (empty parameters)
     */
    public ATM() {
        this.cal.setTimeZone(TimeZone.getTimeZone("EST"));
        //this.BM = new BankManager();
    }

    /**
     * Set the date. Format dd:mm:yyyy
     * @param day
     * @param month
     * @param year
     */
    public void setDate(int day, int month, int year) {
        this.cal.set(Calendar.DATE, day);
        this.cal.set(Calendar.MONTH, month);
        this.cal.set(Calendar.YEAR, year);
    }

    /**
     * Get the date
     * @return
     */
    public String getDate() {
        return "the date is " + this.cal.getTime().toString().substring(4, 10) + ',' +
                cal.getTime().toString().substring(24, 28);
    }

    /**
     * Set the time
     * @param hour
     * @param minute
     * @param second
     */
    public void setTime(int hour, int minute, int second) { // format hh:mm:ss
        this.cal.set(Calendar.HOUR, hour);
        this.cal.set(Calendar.MINUTE, minute);
        this.cal.set(Calendar.SECOND, second);
        this.cal.set(Calendar.MILLISECOND, 00);
    }

    /**
     * Get the time
     * @return
     */
    public String getTime() {
        return "the 24h-time is " + this.cal.getTime().toString().substring(11, 19);
    }

    /**
     * Set the number of 5$ bills
     * @param num5bills
     */
    public void setNum5Bills(int num5bills) {
        this.num5bills = num5bills;
    }

    /**
     * Get the number of 5$ bills
     * @return
     */
    public int getNum5Bills() {
        return this.num5bills;
    }

    /**
     * Add number of 5$ bills
     * @param num5bills
     */
    public void addNum5Bills(int num5bills) {
        this.num5bills += num5bills;
    }

    /**
     * Set the number of 10$ bills
     * @param num10bills
     */
    public void setNum10Bills(int num10bills) {
        this.num10bills = num10bills;
    }

    /**
     * Get the number of 5$ bills
     * @return
     */
    public int getNum10Bills() {
        return this.num10bills;
    }

    /**
     * Add number of 10$ bills
     * @param num10bills
     */
    public void addNum10Bills(int num10bills) {
        this.num10bills += num10bills;
    }

    /**
     * Set the number of 20$ bills
     * @param num20bills
     */
    public void setNum20Bills(int num20bills) {
        this.num20bills = num20bills;
    }

    /**
     * Get the number of 20$ bills
     * @return
     */
    public int getNum20Bills() {
        return this.num20bills;
    }

    /**
     *     Add number of 20$ bills
     * @param num20bills
     */
    public void addNum20Bills(int num20bills) {
        this.num20bills += num20bills;
    }

    /**
     * Set the number of 50$ bills
     * @param num50bills
     */
    public void setNum50Bills(int num50bills) {
        this.num50bills = num50bills;
    }

    /**
     * Get the number of 50$ bills
     * @return
     */
    public int getNum50Bills() {
        return this.num50bills;
    }

    /**
     *     Add number of 50$ bills
     * @param num50bills
     */
    public void addNum50Bills(int num50bills) {
        this.num50bills += num50bills;
    }

     /* *
     * Increase the number of bills in the ATM
     * @param dollarAmount
     */
    // plus bills into ATM method (from deposit methods)
    public void plus(int dollarAmount) {
        ArrayList<Integer> numberStore = new ArrayList<Integer>();
        while (dollarAmount > 0) {
            numberStore.add(dollarAmount % 10); // for fives
            dollarAmount = dollarAmount / 10;
        }
        for (int number : numberStore) {
            if (number % 50 == 0) {
                this.num50bills += number / 50;
            } else if (number % 20 == 0) {
                this.num20bills += number / 20;
            } else if (number % 10 == 0) {
                this.num10bills += number / 10;
            } else if (number % 5 == 0) { // could have done else statement here, but else if is more clear
                this.num5bills += number / 5;
            }
        }
        restock();
    }

    /**
     * Decrease the number of bills in the ATM
     * @param dollarAmount
     */
    // minus bills into ATM method (from withdraw methods)
    public void minus(int dollarAmount) {
        ArrayList<Integer> numberStore = new ArrayList<Integer>();
        while (dollarAmount > 0) {
            numberStore.add(dollarAmount % 10); // for fives
            dollarAmount = dollarAmount / 10;
        }
        for (int number : numberStore) {
            if (number % 50 == 0) {
                this.num50bills -= number / 50;
            } else if (number % 20 == 0) {
                this.num20bills -= number / 20;
            } else if (number % 10 == 0) {
                this.num10bills -= number / 10;
            } else if (number % 5 == 0) { // could have done else statement here, but else if is more clear
                this.num5bills -= number / 5;
            }
        }
        restock();
    }

    public void restock() {
//        /REMOVAL OF E.G.BM.addnum50bills(100); BECAUSE BM REAds all the files for restocking (BY SPECS)

        try {
            PrintStream originalOut = System.out;

            PrintStream fileOut = new PrintStream("/.alerts.txt");

            System.setOut(fileOut);

            if (this.num5bills < 20) {
                originalOut.println("Five dollar bills low in stock!");
            } else if (this.num10bills < 20) {
                originalOut.println("Ten dollar bills low in stock!");
            } else if (this.num20bills < 20) {
                originalOut.println("Twenty dollar bills low in stock!");
            } else if (this.num50bills < 20) {
                originalOut.println("Fifty dollar bills low in stock!");
            }

            System.setOut(originalOut);

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

    }
}