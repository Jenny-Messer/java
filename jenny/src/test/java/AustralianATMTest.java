import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

import exceptions.NotEnoughCashException;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class AustralianATMTest {

    @Test
    public void AtmInitialisesWithExpectedContents() {

        Map<Integer, Integer> actualContents = new HashMap<Integer, Integer>();
        actualContents.put(10, 10);
        actualContents.put(20, 20);
        actualContents.put(50, 50);
        actualContents.put(100, 100);

        AustralianATM newAtm = new AustralianATM(actualContents);
        Map<Integer, Integer> contents = newAtm.getContents();

        assertEquals(contents.size(), 4);

        //get contents of atm
        Integer tenDollarContents = contents.get(10);
        Integer twentyDollarContents = contents.get(20);
        Integer fiftyDollarContents = contents.get(50);
        Integer hundredDollarContents = contents.get(100);

        //test contents correct
        assertEquals(tenDollarContents, new Integer(10));
        assertEquals(twentyDollarContents, new Integer(20));
        assertEquals(fiftyDollarContents, new Integer(50));
        assertEquals(hundredDollarContents, new Integer(100));

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

        AustralianATM newAtm = new AustralianATM(actualContents);

        Withdrawal isValidDispense = newAtm.validDispense(20);

        assertNotNull(isValidDispense);

    }

    @Test(expected = NotEnoughCashException.class)
    public void shouldInvalidateDispenseWhenNotEnoughCash() {

        Map<Integer, Integer> actualContents = new HashMap<Integer, Integer>();
        actualContents.put(10, 10);
        actualContents.put(20, 20);
        actualContents.put(50, 50);
        actualContents.put(100, 100);

        AustralianATM newAtm = new AustralianATM(actualContents);

        newAtm.validDispense(678);
    }

    @Test
    public void shouldCreateWithdrawalWithExpectedContents() {

        Map<Integer, Integer> atmContents = new HashMap<Integer, Integer>();
        atmContents.put(10, 10);
        atmContents.put(20, 20);
        atmContents.put(50, 50);
        atmContents.put(100, 100);

        AustralianATM newAtm = new AustralianATM(atmContents);

        Withdrawal withdrawal = newAtm.validDispense(20);

        Map<Integer, Integer> withdrawalContents = withdrawal.getContents();

        assertEquals(withdrawalContents.get(10), new Integer(0));
        assertEquals(withdrawalContents.get(20), new Integer(1));
        assertEquals(withdrawalContents.get(50), new Integer(0));
        assertEquals(withdrawalContents.get(100), new Integer(0));


        Withdrawal secondWithdrawal = newAtm.validDispense(150);

        Map<Integer, Integer> secondWithdrawalContents = secondWithdrawal.getContents();

        assertEquals(secondWithdrawalContents.get(10), new Integer(0));
        assertEquals(secondWithdrawalContents.get(20), new Integer(0));
        assertEquals(secondWithdrawalContents.get(50), new Integer(1));
        assertEquals(secondWithdrawalContents.get(100), new Integer(1));

    }

    @Test
    public void shouldRemoveCorrectAmountOfCashFromAtm() {

        Map<Integer, Integer> atmContents = new HashMap<Integer, Integer>();
        atmContents.put(10, 10);
        atmContents.put(20, 20);
        atmContents.put(50, 50);
        atmContents.put(100, 100);

        AustralianATM newAtm = new AustralianATM(atmContents);

        Withdrawal withdrawal = newAtm.validDispense(20);

        Map<Integer, Integer> withdrawalContents = withdrawal.getContents();

        assertEquals(atmContents.get(10), new Integer(10));
        assertEquals(atmContents.get(20), new Integer(19));
        assertEquals(atmContents.get(50), new Integer(50));
        assertEquals(atmContents.get(100), new Integer(100));


    }

    @Test
    public void shouldAddCorrectAmountOfCashToAtm() {

        Map<Integer, Integer> atmContents = new HashMap<Integer, Integer>();
        atmContents.put(10, 10);
        atmContents.put(20, 20);
        atmContents.put(50, 50);
        atmContents.put(100, 100);

        //refill same size as original so if added immediately should double no. of notes in ATM
        Map<Integer, Integer> atmRefillContents = new HashMap<Integer, Integer>();
        atmRefillContents.put(10, 10);
        atmRefillContents.put(20, 20);
        atmRefillContents.put(50, 50);
        atmRefillContents.put(100, 100);

        AustralianATM newAtm = new AustralianATM(atmContents);

        newAtm.refillAtm(atmRefillContents);

        assertEquals(atmContents.get(10), new Integer(20));
        assertEquals(atmContents.get(20), new Integer(40));
        assertEquals(atmContents.get(50), new Integer(100));
        assertEquals(atmContents.get(100), new Integer(200));


    }


}