
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;

import exceptions.NotEnoughCashException;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ATMTest {

    @Test
    public void AtmInitialisesWithExpectedContents() {

        Map<Integer, Integer> actualContents = new HashMap<Integer, Integer>();
        actualContents.put(10, 10);
        actualContents.put(20, 20);
        actualContents.put(50, 50);
        actualContents.put(100, 100);

        ATM newAtm = new ATM(actualContents);
        Map<Integer, Integer> contents = newAtm.getContents();

        assertEquals(contents.size(), 4);

        //get contents of atm
        Integer tenDollarContents = contents.get(10);
        Integer twentyDollarContents = contents.get(20);
        Integer fiftyDollarContents = contents.get(50);
        Integer hundredDollarContents = contents.get(100);

        //test contents correct
        assertEquals(tenDollarContents, new Integer(10));
        assertEquals(twentyDollarContents, new Integer(5));
        assertEquals(fiftyDollarContents, new Integer(7));
        assertEquals(hundredDollarContents, new Integer(9));

//        Integer tenDollarNotesCount = contents.get(10);
//        assertEquals, 2);
//        assertEquals(contents.size(), 2);

    }

    @Test
    public void shouldValidateDispenseWhenEnoughCash() {

        Map<Integer, Integer> actualContents = new HashMap<Integer, Integer>();
        actualContents.put(10, 10);
        actualContents.put(20, 20);
        actualContents.put(50, 50);
        actualContents.put(100, 100);

        ATM newAtm = new ATM(actualContents);

        boolean isValidDispense = newAtm.validDispense(20);

        assertTrue(isValidDispense);

    }

    @Test(expected = NotEnoughCashException.class)
    public void shouldInvalidateDispenseWhenNotEnoughCash() {

        Map<Integer, Integer> actualContents = new HashMap<Integer, Integer>();
        actualContents.put(10, 10);
        actualContents.put(20, 20);
        actualContents.put(50, 50);
        actualContents.put(100, 100);

        ATM newAtm = new ATM(actualContents);

        newAtm.validDispense(678);
    }


}