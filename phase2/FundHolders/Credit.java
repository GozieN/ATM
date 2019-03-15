package phase2.FundHolders;

/**
 *
 */
public class Credit extends Account implements java.io.Serializable{
    boolean isLOC;

    /**
     * Credit class constructor
     * @param accountNum Number used to identify a specific account
     * @param holderName Name of holder of the account
     * @param balance Amount of money found in account
     * @param isLOC Checks if type of account is a Line of Credit
     */
    public Credit(int accountNum, String holderName, double balance, boolean isLOC){
        super(accountNum, holderName, balance, "CreditCardAccount");
        this.isLOC = isLOC;
        if(isLOC){
            accountType = "LineOfCredit";
        }
    }
}
