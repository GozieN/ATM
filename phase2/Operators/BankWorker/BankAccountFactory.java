package phase2.Operators.BankWorker;

import phase2.FundStores.Account;
import phase2.FundStores.Asset.*;
import phase2.FundStores.Debt.CreditCard;
import phase2.FundStores.Debt.LineOfCredit;
import phase2.FundStores.Debt.LineOfCredit;
import phase2.Operators.BankAccountUser.*;
import phase2.Operators.BankAccountUser.BankUserFactory;

public class BankAccountFactory {
    private static final String LINE_OF_CREDIT =  "lineofcredit";
    private static final String CREDIT = "creditcard";
    private static final String PREPAID = "prepaid";
    private static final String SAVINGS = "savings";
    private static final String CHEQUING = "chequing";
    private String accountType;

    /**
     * BankAccountFactory constructor
     * @param accountType Type of account
     */
    BankAccountFactory(String accountType){
        this.accountType = accountType;
    }

    /**
     * Determine a new bank account from user's special request
     * @param psu Instance of a PointSystemUser
     * @param designatedUser User to receive new bank account
     */
    public void determineNewBankAccountFromSpecialRequest(PointSystemUser psu, User designatedUser) {
        Account newAccount = null;
        if (psu instanceof StudentPointSystemUser) {
            newAccount = new StudentAccount(psu);
            newAccount.setBalance(60);
        } else if (psu instanceof RetiredPointSystemUser) {
            TODO: //BankTeller/consultant ===> assign designated user
            newAccount = new Oldies(psu, designatedUser);
        } psu.addToAccountsCreated(newAccount);
    }

    /**
     * Determine type of bank accounts from user requests
     * @param starting Starting balance for prepaid credit
     * @param user User of bank account
     * @return Creation of new account
     */
    public Account determineBankAccountsFromRequest(double starting, User user) {
        Account newAccount = null;
        switch (accountType){
        case LINE_OF_CREDIT:
            newAccount = new LineOfCredit(user);
            break;
        case CREDIT:
            newAccount = new CreditCard(user);
            break;
        case SAVINGS:
            newAccount = new SavingsAccount(user);
            break;
        case PREPAID:
            newAccount = new PrepaidCredit(user);
            newAccount.setBalance(starting);

            break;
        case CHEQUING:
            user.setNumChequingAccounts();
            if (user.getNumChequingAccounts() == 0) {
                user.setNumChequingAccounts();
                newAccount = new ChequingAccount(user, true);
            } else { newAccount = new ChequingAccount(user, false); }}
        user.addToAccountsCreated(newAccount);
        return newAccount; }

    public void updateBankAccountDatabase(User oldUser, User newUser) {
        for (Account account: oldUser.getAccountsCreated()){
            newUser.addToAccountsCreated(account); }
        oldUser.setAccountsCreated(null);
        BankUserFactory buf = new BankUserFactory(oldUser.getUserType());
    }
}