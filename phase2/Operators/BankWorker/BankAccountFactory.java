package phase2.Operators.BankWorker;

import phase2.FundStores.Account;
import phase2.FundStores.Asset.ChequingAccount;
import phase2.FundStores.Asset.Oldies;
import phase2.FundStores.Asset.SavingsAccount;
import phase2.FundStores.Asset.StudentAccount;
import phase2.FundStores.Debt.Credit;
import phase2.FundStores.Debt.CreditCard;
import phase2.FundStores.Debt.LineOfCredit;
import phase2.Operators.BankAccountUser.*;
import phase2.Operators.BankAccountUser.BankUserFactory;

public class BankAccountFactory {
    private static final String LINEOFCREDIT =  "LineOfCredit";
    private static final String CREDIT = "credit";
    private static final String PREPAID = "prepaid";
    private static final String SAVINGS = "savings";
    private static final String CHEQUING = "chequing";
    private String accountType;

    BankAccountFactory(String accountType){
        this.accountType = accountType;
    }

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


    public Account determineBankAccountsFromRequest(User user) {
        Account newAccount = null;
        switch (accountType){
        case LINEOFCREDIT:
            newAccount = new LineOfCredit(user);
            break;
        case CREDIT:
            newAccount = new CreditCard(user);
            break;
        case SAVINGS:
            newAccount = new SavingsAccount(user);
            break;
        case PREPAID:
            newAccount = new SavingsAccount(user);
            break;
        case CHEQUING:
            user.setNumChequingAccounts();
            if (user.getNumChequingAccounts() == 1) {
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