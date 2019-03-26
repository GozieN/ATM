package phase2.Test;
import phase2.FundStores.Asset.*;
import phase2.FundStores.Debt.*;


import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import phase2.Operators.BankAccountUser.User;

import static junit.framework.TestCase.assertEquals;

public class AccountTest {
    private SavingsAccount savingsAccount;
    private ChequingAccount chequingAccount;
    private ChequingAccount primaryAccount;
    private Credit creditAccount;
    private Credit lineOfCreditAccount;
    private User user;


    @Before
    public void SetUp(){
        user = new User("TestUser", "TestPassword");
        savingsAccount = new SavingsAccount(user);
        chequingAccount = new ChequingAccount(user,"Chequing", true);
        creditAccount = new Credit(user, false);
        lineOfCreditAccount = new Credit(user, true);
    }

    @After
    public void TearDown(){
        savingsAccount = null;
        chequingAccount = null;
        primaryAccount = null;
        creditAccount = null;
        lineOfCreditAccount = null;}

    @Test
    public void testAccountAccountNumber(){
        assertEquals(1, savingsAccount);
        assertEquals(2, chequingAccount);
        assertEquals(3, primaryAccount);
        assertEquals(4, creditAccount);
        assertEquals(5, lineOfCreditAccount);
    }

    @Test
    public void testAccountAccountType(){
        assertEquals("savingsAccount", savingsAccount);
        assertEquals("chequingAccount", chequingAccount);
        assertEquals("primaryAccount", primaryAccount);
        assertEquals("creditAccount", creditAccount);
        assertEquals("lineOfCreditAccount", lineOfCreditAccount);
    }

    @Test
    public void testAccountHistoryRecord() {
        //view the record
    }

    @Test
    public void testAccountHistoryAltercation() {
        //user transaction methods and see what the history holds
    }


}