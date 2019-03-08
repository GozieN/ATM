package phase1.FundHolders;

import java.util.*;
import java.io.FileNotFoundException;
import java.io.PrintStream;


public class ATM {
    private int num5bills = 100;
    private int num10bills = 100;
    private int num20bills = 100;
    private int num50bills = 100;
    private Calendar cal = new GregorianCalendar();
    private Manager BM;

    // ATM constructor
    public ATM(int num5bills, int num10bills, int num20bills, int num50bills) {
         this.num5bills = num5bills;
         this.num10bills = num10bills;
         this.num20bills = num20bills;
         this.num50bills = num50bills;
         this.cal.setTimeZone(TimeZone.getTimeZone("EST"));
         this.BM = new Manager();
    }

    // setdate method (BM use ONLY in console)
    public void setDate(int day, int month, int year) { // format dd:mm:yyyy
        this.cal.set(Calendar.DATE, day);
        this.cal.set(Calendar.MONTH, month);
        this.cal.set(Calendar.YEAR, year);
    }

    // getdate method (universal use in console)
    public String getDate() {
        return "the date is " + this.cal.getTime().toString().substring(4, 10) + ',' +
                cal.getTime().toString().substring(24, 28);
    }

    // settime method (BM use ONLY in console)
    public void setTime(int hour, int minute, int second) { // format hh:mm:ss
        this.cal.set(Calendar.HOUR, hour);
        this.cal.set(Calendar.MINUTE, minute);
        this.cal.set(Calendar.SECOND, second);
        this.cal.set(Calendar.MILLISECOND, 00);
    }

    // gettime method (universal use in console)
    public String getTime() {
        return "the 24h-time is " + this.cal.getTime().toString().substring(11, 19);
    }

    // setnum5bills method (BM use ONLY in console)
    public void setNum5Bills(int num5bills) {
        this.num5bills = num5bills;
    }

    // getnum5bills method (BM use ONLY in console)
    public int getNum5Bills() {
        return this.num5bills;
    }

    // addnum5bills method (BM use ONLY in console)
    public void addNum5Bills(int num5bills) {
        this.num5bills += num5bills;
    }

    // setnum10bills method (BM use ONLY in console)
    public void setNum10Bills(int num10bills) {
        this.num10bills = num10bills;
    }

    // getnum10bills method (BM use ONLY in console)
    public int getNum10Bills() {
        return this.num10bills;
    }

    // addnum10bills method (BM use ONLY in console)
    public void addNum10Bills(int num10bills) {
        this.num10bills += num10bills;
    }

    // setnum20bills method (BM use ONLY in console)
    public void setNum20Bills(int num20bills) {
        this.num20bills = num20bills;
    }

    // getnum20bills method (BM use ONLY in console)
    public int getNum20Bills() {
        return this.num20bills;
    }

    // addnum20bills method (BM use ONLY in console)
    public void addNum20Bills(int num20bills) {
        this.num20bills += num20bills;
    }

    // setnum50bills method (BM use ONLY in console)
    public void setNum50Bills(int num50bills) {
        this.num50bills = num50bills;
    }

    // getnum50bills method (BM use ONLY in console)
    public int getNum50Bills() {
        return this.num50bills;
    }

    // addnum50bills method (BM use ONLY in console)
    public void addNum50Bills(int num50bills) {
        this.num50bills += num50bills;
    }

    public void calculateDenoms(double amt){
        while (amt => 5) {
            if (amt => 50){
                amt=amt-50;
                this.setNum50Bills(num50bills--);
            }
            else if (amt => 20){
                amt=amt-20;
                this.setNum20Bills(num20bills--);
            }
            else if (amt => 10){
                amt=amt-10;
                this.setNum10bills(num10bills--);
            }
            else if (amt => 5){
                amt=amt-5;
                this.setNum5Bills(num5bills--);
            }
        }
    }

    // add bills method (deposit numbills to ATM)

    // minus bills method(s ?) for notifier: -> output.txt low amount of bills for BM to restock
        // each and any other method in package that takes out bills needs to add this method in body

    // consider observer pattern in order to update other files/classes


    // [Angela]
    public void main (String[] args) {

        try{
            PrintStream originalOut = System.out;

            PrintStream fileOut = new PrintStream("/.alerts.txt");

            System.setOut(fileOut);

            if (this.num5bills < 4){originalOut.println("Five dollar bills low in stock!"); BM.addnum5bills(100);}
            else if (this.num10bills < 2) {originalOut.println("Ten dollar bills low in stock!"); BM.addnum10bills(100);}
            else if (this.num20bills < 1) {originalOut.println("Twenty dollar bills low in stock!"); BM.addnum20bills(100);}
            else if (this.num50bills < 1) {originalOut.println ("Fifty dollar bills low in stock!"); BM.addnum50bills(100);}

            System.setOut(originalOut);

            }catch (FileNotFoundException ex)
            { ex.printStackTrace();}


        }
    }