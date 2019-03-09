package phase1;

import java.util.*;
import phase1.FundHolders.*;
import phase1.Operators.*;
import phase1.FundTransfers.*;

public class Model {
    private BankManager BM = new BankManager("BM12345", "BMpassword");
    private ATM atm = new ATM();
    private ArrayList<String> userUsernames = new ArrayList<>();

    // model constructor
    public Model() {}

    // method menuoperatorselect
    public static void menuOperatorSelect() {
        System.out.println("enter 1 for bankmanager and 2 for normal user. enter 3 to exit");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String s = scanner.next();
            if (s.equals("1")) {
                // BM username, password check
                System.out.println("enter your username");
                Scanner usernameScan = new Scanner(System.in);
                String username = usernameScan.next();
                if (username.equals(this.BM.getUsername())) {
                    System.out.println("enter your password");
                    Scanner passwordScan = new Scanner(System.in);
                    String password = passwordScan.next();
                    if (password.equals(this.BM.getPassword())) {
                        // TODO: send to next BM menu
                    } else {
                        System.out.println("wrong password. enter your password");
                    }
                } else {
                    System.out.println("wrong username. enter your username");
                }
            } else if (s.equals("2")) {
                // user username, password check
                Scanner usernameScan = new Scanner(System.in);
                String username = usernameScan.next();
                if (this.userUsernames.contains(username)) {
                    System.out.println("enter your password");
                    Scanner passwordScan = new Scanner(System.in);
                    String password = passwordScan.next();
                    if (password.equals() {
                        // TODO: send to next user menu
                    } else {
                        System.out.println("wrong password. enter your password");
                    }
                } else {
                    System.out.println("wrong username. enter your username");
                }
            } else if (s.equals("exit")) {
                // returns to previous screen
                    // deeper menus should "log off" and return to first menu (menuoperatorselect)
                menuOperatorSelect();
            }
        }
    }

    // method menuuserexistingnew

    // method ___

    // scanner: reads user inputs and translates it into method calls
    // methods would deal with what is called

    public static void main(String[] args) {

        // Upon starting the program, read a file which contains the only instance of Bank Manager. (Everything can be
        // accessed from here
        // try
        // catch
        // BankManger BM = the one read from the file

        // menus
        // menu options

        Model model = new Model();
        model.menuOperatorSelect();
    }
}