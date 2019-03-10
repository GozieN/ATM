package phase1.Operators;

import phase1.FundHolders.Account;


public class Operator {
    private String username;
    private String password;

    /**
     * User constructor
     * @param username
     * @param password
     */
    public Operator(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Return the user's username
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Return the user's password
     * @return String - the user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the username
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Return a summary of the accounts
     * @param account
     */
    public void singleAccountSummary(Account account) {
        System.out.println("Account holder: " + account.getHolderName() +
                "" + "Account summary:" + account.getAccountType() +"Account Number: "
                + account.getAccountNum() + " contains: " + account.getBalance() + "currency");}
}

