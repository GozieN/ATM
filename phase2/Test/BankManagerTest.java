package phase2.Test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;


import phase2.Operators.BankAccountUser.User;
import phase2.Operators.BankWorker.BankManager;

import java.io.FileNotFoundException;

public class BankManagerTest {
    private BankManager BM;


    @Before
    public void setUp() {
        BM = new BankManager("BM", "BM");
        BM.createUser("a", "a");
        BM.createUser("b", "b");
    }

    @After
    public void tearDown() {
        BM = null;
    }

    @Test
    public void testCreateUser() {
        tearDown();
        BM = new BankManager("BM", "BM");
        assertTrue(null, BM.getUsers().isEmpty());
        BM.createUser("a", "a");
        BM.createUser("b", "b");
        assertSame(BM.getUsers().size(), 2);
    }

    @Test
    public void testDelete(){
        BM.deleteUser(BM.getUsers().get(0));
        assertSame(1, BM.getUsers().size());
    }

    @Test
    public void testRestock() throws FileNotFoundException {

    }
}
