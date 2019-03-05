package phase1;

import java.util.*;
import java.io.FileNotFoundException;
import java.io.PrintStream;


public class ATM {
    private int num5bills = 0;
    private int num10bills = 0;
    private int num20bills = 0;
    private int num50bills = 0;
    private Calendar cal = new GregorianCalendar();

    // ATM constructor
    public ATM(int num5bills, int num10bills, int num20bills, int num50bills) {
         this.num5bills = num5bills;
         this.num10bills = num10bills;
         this.num20bills = num20bills;
         this.num50bills = num50bills;
         this.cal.setTimeZone(TimeZone.getTimeZone("EST"));
    }

    // setdate method (BM use ONLY in console)
    public void setdate(int day, int month, int year) { // format dd:mm:yyyy
        this.cal.set(Calendar.DATE, day);
        this.cal.set(Calendar.MONTH, month);
        this.cal.set(Calendar.YEAR, year);
    }

    // getdate method (universal use in console)
    public String getdate() {
        return "the date is " + this.cal.getTime().toString().substring(4, 10) + ',' +
                cal.getTime().toString().substring(24, 28);
    }

    // settime method (BM use ONLY in console)
    public void settime(int hour, int minute, int second) { // format hh:mm:ss
        this.cal.set(Calendar.HOUR, hour);
        this.cal.set(Calendar.MINUTE, minute);
        this.cal.set(Calendar.SECOND, second);
        this.cal.set(Calendar.MILLISECOND, 00);
    }

    // gettime method (universal use in console)
    public String gettime() {
        return "the 24h-time is " + this.cal.getTime().toString().substring(11, 19);
    }

    // setnum5bills method (BM use ONLY in console)
    public void setnum5bills(int num5bills) {
        this.num5bills = num5bills;
    }

    // getnum5bills method (BM use ONLY in console)
    public int getnum5bills() {
        return this.num5bills;
    }

    // addnum5bills method (BM use ONLY in console)
    public void addnum5bills(int num5bills) {
        this.num5bills += num5bills;
    }

    // setnum10bills method (BM use ONLY in console)
    public void setnum10bills(int num10bills) {
        this.num10bills = num10bills;
    }

    // getnum10bills method (BM use ONLY in console)
    public int getnum10bills() {
        return this.num10bills;
    }

    // addnum10bills method (BM use ONLY in console)
    public void addnum10bills(int num10bills) {
        this.num10bills += num10bills;
    }

    // setnum20bills method (BM use ONLY in console)
    public void setnum20bills(int num20bills) {
        this.num20bills = num20bills;
    }

    // getnum20bills method (BM use ONLY in console)
    public int getnum20bills() {
        return this.num20bills;
    }

    // addnum20bills method (BM use ONLY in console)
    public void addnum20bills(int num20bills) {
        this.num20bills += num20bills;
    }

    // setnum50bills method (BM use ONLY in console)
    public void setnum50bills(int num50bills) {
        this.num50bills = num50bills;
    }

    // getnum50bills method (BM use ONLY in console)
    public int getnum50bills() {
        return this.num50bills;
    }

    // addnum50bills method (BM use ONLY in console)
    public void addnum50bills(int num50bills) {
        this.num50bills += num50bills;
    }

    // minus bills method(s ?) for notifier: -> output.txt low amount of bills for BM to restock
        // each and any other method in package that takes out bills needs to add this method in body

    // consider observer pattern in order to update other files/classes


    // [Angela]
    public void main (String[] args) {

        try{
            PrintStream originalOut = System.out;

            PrintStream fileOut = new PrintStream("/.alerts.txt");

            System.setOut(fileOut);

            if (this.num50bills < 4){originalOut.println("Five dollar bills low in stock!");}
            else if (this.num10bills < 2) {originalOut.println("Ten dollar bills low in stock!");}
            else if (this.num20bills < 1) {originalOut.println("Twenty dollar bills low in stock!");}
            else if (this.num50bills < 1) {originalOut.println ("Fifty dollar bills low in stock!");}

            System.setOut(originalOut);

            }catch (FileNotFoundException ex)
            { ex.printStackTrace();}


        }
    }