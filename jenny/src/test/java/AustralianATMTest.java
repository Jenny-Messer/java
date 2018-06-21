import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

import atm.exceptions.NotEnoughCashInAccountException;
import atm.exceptions.NotEnoughCashInAtmException;
import atm.model.Account;
import atm.model.AustralianATM;
import atm.model.Customer;
import atm.model.Withdrawal;
import org.junit.Test;
import atm.service.ExchangeServiceImpl;
import atm.service.CustomerDataAccessImpl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/*
Tests TODO:
get/set contents
valid dispense
        remove notes, make and populate withdrawal object, update customer balance
remove notes from contents
get best combo of notes to remove
get notes in ascending order
get user

init objects with expected contents (customer, employee)
 */

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

    }

    @Test //split?
    public void shouldValidateDispenseWhenEnoughCashInAtmAndUsersAccount() {

        Map<Integer, Integer> actualContents = new HashMap<Integer, Integer>();
        actualContents.put(10, 10);
        actualContents.put(20, 20);
        actualContents.put(50, 50);
        actualContents.put(100, 100);

        AustralianATM newAtm = new AustralianATM(actualContents);

        UUID accountId = UUID.randomUUID();
        UUID customerId = UUID.randomUUID();

        Account account = new Account(new BigDecimal(100), "AUD", accountId);

        Customer customer1 = new Customer(1234, customerId);
        customer1.addAccount(account);

        Withdrawal isValidDispense = newAtm.validDispense(new BigDecimal(20), customer1);

        assertNotNull(isValidDispense);

    }

    @Test(expected = NotEnoughCashInAtmException.class)
    public void shouldInvalidateDispenseWhenNotEnoughCashInAtm() {

        Map<Integer, Integer> actualContents = new HashMap<Integer, Integer>();
        actualContents.put(10, 0);
        actualContents.put(20, 0);
        actualContents.put(50, 0);
        actualContents.put(100, 0);

        AustralianATM newAtm = new AustralianATM(actualContents);

        UUID accountId = UUID.randomUUID();
        UUID customerId = UUID.randomUUID();

        Account account = new Account(new BigDecimal(100), "AUD", accountId);

        Customer customer1 = new Customer(1234, customerId);
        customer1.addAccount(account);

        newAtm.validDispense(new BigDecimal(50), customer1);
    }

    @Test(expected = NotEnoughCashInAccountException.class)
    public void shouldInvalidateDispenseWhenNotEnoughCashInUsersAccount() {

        Map<Integer, Integer> actualContents = new HashMap<Integer, Integer>();
        actualContents.put(10, 10);
        actualContents.put(20, 20);
        actualContents.put(50, 50);
        actualContents.put(100, 100);

        AustralianATM newAtm = new AustralianATM(actualContents);

        UUID accountId = UUID.randomUUID();
        UUID customerId = UUID.randomUUID();

        Account account = new Account(new BigDecimal(0), "AUD", accountId);

        Customer customer1 = new Customer(1234, customerId);
        customer1.addAccount(account);

        newAtm.validDispense(new BigDecimal(50), customer1);
    }

    @Test
    public void shouldCreateWithdrawalWithExpectedContents() {

        Map<Integer, Integer> atmContents = new HashMap<Integer, Integer>();
        atmContents.put(10, 10);
        atmContents.put(20, 20);
        atmContents.put(50, 50);
        atmContents.put(100, 100);

        AustralianATM newAtm = new AustralianATM(atmContents);

        UUID accountId = UUID.randomUUID();
        UUID customerId = UUID.randomUUID();

        Account account = new Account(new BigDecimal(100), "AUD", accountId);

        Customer customer1 = new Customer(1234, customerId);
        customer1.addAccount(account);

        Withdrawal withdrawal = newAtm.validDispense(new BigDecimal(20), customer1);

        Map<Integer, Integer> withdrawalContents = withdrawal.getContents();

        assertEquals(withdrawalContents.get(10), new Integer(0));
        assertEquals(withdrawalContents.get(20), new Integer(1));
        assertEquals(withdrawalContents.get(50), new Integer(0));
        assertEquals(withdrawalContents.get(100), new Integer(0));


        Withdrawal secondWithdrawal = newAtm.validDispense(new BigDecimal(50), customer1);

        Map<Integer, Integer> secondWithdrawalContents = secondWithdrawal.getContents();

        assertEquals(secondWithdrawalContents.get(10), new Integer(0));
        assertEquals(secondWithdrawalContents.get(20), new Integer(0));
        assertEquals(secondWithdrawalContents.get(50), new Integer(1));
        assertEquals(secondWithdrawalContents.get(100), new Integer(0));

    }

    @Test
    public void shouldRemoveCorrectAmountOfCashFromAtm() {

        Map<Integer, Integer> atmContents = new HashMap<Integer, Integer>();
        atmContents.put(10, 10);
        atmContents.put(20, 20);
        atmContents.put(50, 50);
        atmContents.put(100, 100);

        AustralianATM newAtm = new AustralianATM(atmContents);

        UUID accountId = UUID.randomUUID();
        UUID customerId = UUID.randomUUID();

        Account account = new Account(new BigDecimal(100), "AUD", accountId);

        Customer customer1 = new Customer(1234, customerId);
        customer1.addAccount(account);

        Withdrawal withdrawal = newAtm.validDispense(new BigDecimal(20), customer1);

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

        //refill same size as original so if added immediately should double no. of notes in atm.model.ATM
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

    @Test
    public void shouldCorrectlyUpdateUsersBalanceAfterWithdrawal() {

        Map<Integer, Integer> actualContents = new HashMap<Integer, Integer>();
        actualContents.put(10, 10);
        actualContents.put(20, 20);
        actualContents.put(50, 50);
        actualContents.put(100, 100);

        AustralianATM newAtm = new AustralianATM(actualContents);

        UUID accountId = UUID.randomUUID();
        UUID customerId = UUID.randomUUID();

        Account account = new Account(new BigDecimal(100), "AUD", accountId);

        Customer customer1 = new Customer(1234, customerId);
        customer1.addAccount(account);


        //atm.model.Withdrawal isValidDispense = newAtm.validDispense(20, customer1);

        Map<Integer, Integer> removedNotes = new HashMap<Integer, Integer>();
        removedNotes.put(10, 0);
        removedNotes.put(20, 1);
        removedNotes.put(50, 0);
        removedNotes.put(100, 0);

//        CustomerDataAccessImpl userDataAccess = new CustomerDataAccessImpl();
        ExchangeServiceImpl exchangeService = new ExchangeServiceImpl(); //userDataAccess

        exchangeService.getValueToRemoveFromBalanceInCorrectCurrency(newAtm.getCurrency(), account,removedNotes );

        assertEquals(account.getBalance(), new BigDecimal(80));

        removedNotes.put(10, 0);
        removedNotes.put(20, 0);
        removedNotes.put(50, 0);
        removedNotes.put(100, -10);

        exchangeService.getValueToRemoveFromBalanceInCorrectCurrency(newAtm.getCurrency(), account,removedNotes );


        assertEquals(account.getBalance(), new BigDecimal(1080));


    }

    @Test
    public void shouldInvalidateWithdrawalWhenNegativeWithdrawalFromUser() {

    }
}