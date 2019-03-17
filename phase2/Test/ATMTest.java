package phase2.Test;
import phase2.FundTransfers.*;
import phase2.FundHolders.*;
import phase2.Operators.*;

import java.io.FileNotFoundException;
import java.util.Random;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;


public class ATMTest {

    private ATM atm;

    @Before
    public void setUp() {atm = new ATM();}

    @Test
    public void testPlus() {

        boolean amountAfterPlus = false;

    }

    @Test
    public void testMinus(){

    }

    @Test
    public void testRestock() throws FileNotFoundException {

        boolean result = false;

        atm = new ATM(4, 100, 100, 100);


        for (int i = 0; i < 10; i++) {
            atm.restock();
            if (atm.getNum5Bills() >= 104) {
                result = true;
                break;
            }
        }

        assertTrue(result);

    }

}
