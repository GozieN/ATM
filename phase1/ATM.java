package phase1;

public class ATM {
    private String date = "00:00:0000"; // mm:dd::yyyy
    private String time = "00:00:00"; // hh:mm:ss
    private int num5bills = 0;
    private int num10bills = 0;
    private int num20bills = 0;
    private int num50bills = 0;

    public ATM(String date, String time, int num5bills, int num10bills, int num20bills, int num50bills) {
         this.date = date;
         this.time = time;
         this.num5bills = num5bills;
         this.num10bills = num10bills;
         this.num20bills = num20bills;
         this.num50bills = num50bills;
    }

    // getdate method
    public String getdate() {
        return this.date;
    }

    // gettime method
    public String gettime() {
        return this.time;
    }

    // getnum5bills method
    public int getnum5bills() {
        return this.num5bills;
    }

    // getnum10bills method
    public int getnum10bills() {
        return this.num10bills;
    }

    // getnum20bills method
    public int getnum20bills() {
        return this.num20bills;
    }

    // getnum50bills method
    public int getnum50bills() {
        return this.num50bills;
    }

    // setdate method (BM)
    public void setdate(String date) {
        this.date = date;
    }

    // settime method (BM)
    public void settime(String time) {
        this.time = time;
    }

    // setnum5bills method (BM)
    public void setnum5bills(int num5bills) {
        this.num5bills = num5bills;
    }

    // setnum10bills method (BM)
    public void setnum10bills(int num10bills) {
        this.num10bills = num10bills;
    }

    // setnum20bills method (BM)
    public void setnum20bills(int num20bills) {
        this.num20bills = num20bills;
    }

    // setnum50bills method (BM)
    public void setnum50bills(int num50bills) {
        this.num50bills = num50bills;
    }

    // addbills methods (BM)::
    // consider observer pattern in order to update other files/classes

    // addnum5bills method (BM)
    public void addnum5bills(int num5bills) {
        this.num5bills += num5bills;
    }

    // addnum10bills method (BM)
    public void addnum10bills(int num10bills) {
        this.num10bills += num10bills;
    }

    // addnum20bills method (BM)
    public void addnum20bills(int num20bills) {
        this.num20bills += num20bills;
    }

    // addnum50bills method (BM)
    public void addnum50bills(int num50bills) {
        this.num50bills += num50bills;
    }


}