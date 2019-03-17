package phase2.FundHolders;

import phase2.Operators.*;

import java.util.*;
import java.io.*;
import java.io.PrintStream;
import java.time.format.*;
import java.time.*;

public class ATM implements java.io.Serializable {
    private int num5bills = 100;
    private int num10bills = 100;
    private int num20bills = 100;
    private int num50bills = 100;
    private Calendar cal = new GregorianCalendar();
    private BankManager BM;

    /**
     * ATM class constructor
     * @param num5bills The number of $5 bills in ATM
     * @param num10bills The number of $10 bills in ATM
     * @param num20bills The number of $20 bills in ATM
     * @param num50bills The number of $50 bills in ATM
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

    public void setDate(int day, int month, int year) throws IOException {

        DateTimeFormatter d = DateTimeFormatter.ofPattern("ddmmyyyy HH:mm:ss");

        BufferedReader input = new BufferedReader(new FileReader("./src/date.txt"));
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

    }


    /**
     * Get the date
     * @return A string indicating current date
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
    public void setTime(int hour, int minute, int second) throws IOException{ // format hh:mm:ss
        DateTimeFormatter d = DateTimeFormatter.ofPattern("ddmmyyyy HH:mm:ss");

        BufferedReader input = new BufferedReader(new FileReader("./src/date.txt"));
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
    }

    /**
     * Get the time
     * @return String indicating the current time on a 24-hour clock
     */
    public String getTime() {
        return "the 24h-time is " + this.cal.getTime().toString().substring(11, 19);
    }

    /**
     * Set the number of $5 bills
     * @param num5bills Number of $5 bills
     */
    public void setNum5Bills(int num5bills) {
        this.num5bills = num5bills;
    }

    /**
     * Get the number of 5$ bills
     * @return Int of $5 bills in ATM machine
     */
    public int getNum5Bills() {
        return this.num5bills;
    }

    /**
     * Set the number of 10$ bills
     * @param num10bills Number of $10 bills
     */
    public void setNum10Bills(int num10bills) {
        this.num10bills = num10bills;
    }

    /**
     * Get the number of $10 bills
     * @return Int of $10 bills in ATM machine
     */
    public int getNum10Bills() {
        return this.num10bills;
    }

    /**
     * Set the number of 20$ bills
     * @param num20bills Number of $20 bills
     */
    public void setNum20Bills(int num20bills) {
        this.num20bills = num20bills;
    }

    /**
     * Get the number of 20$ bills
     * @return Int of $20 bills in ATM machine
     */
    public int getNum20Bills() {
        return this.num20bills;
    }

    /**
     * Set the number of 50$ bills
     * @param num50bills Number of $50 bills
     */
    public void setNum50Bills(int num50bills) {
        this.num50bills = num50bills;
    }

    /**
     * Get the number of 50$ bills
     * @return Int of $50 bills in ATM machine
     */
    public int getNum50Bills() {
        return this.num50bills;
    }

     /**
     * Increase the number of bills in the ATM
     * @param dollarAmount The amount of money added to ATM machine
     */
    // plus bills into ATM method (from deposit methods)
    public void plus(int dollarAmount) {
        ArrayList<Integer> numberStore = new ArrayList<Integer>();
        numberStore.add(dollarAmount % 10); // for fives
        dollarAmount -= dollarAmount % 10;
        while (dollarAmount > 0) {
            numberStore.add(dollarAmount);
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
     * @param dollarAmount The amount of money deducted from ATM machine
     */
    // minus bills into ATM method (from withdraw methods)
    public void minus(int dollarAmount) {
        ArrayList<Integer> numberStore = new ArrayList<Integer>();
        numberStore.add(dollarAmount % 10); // for fives
        dollarAmount -= dollarAmount % 10;
        while (dollarAmount > 0) {
            numberStore.add(dollarAmount);
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

    /**
     * Alert to show need for restock if number of bills are low
     */
    public void restock() {
        try {
            PrintStream originalOut = System.out;

            PrintStream fileOut = new PrintStream("phase2/alerts.txt");

            System.setOut(fileOut);

            if (this.num5bills < 20) {
                originalOut.println("Five dollar bills low in stock!");
                BM.restockFromFile(this);
            } else if (this.num10bills < 20) {
                originalOut.println("Ten dollar bills low in stock!");
                BM.restockFromFile(this);
            } else if (this.num20bills < 20) {
                originalOut.println("Twenty dollar bills low in stock!");
                BM.restockFromFile(this);
            } else if (this.num50bills < 20) {
                originalOut.println("Fifty dollar bills low in stock!");
                BM.restockFromFile(this);
            }

            System.setOut(originalOut);

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

}