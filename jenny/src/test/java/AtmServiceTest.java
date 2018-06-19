import atm.model.AustralianATM;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
// Copyright (c) 2018 Travelex Ltd

public class AtmServiceTest {

    @Test
    public void sdfgdsfg() {
        AustralianATM atm = new AustralianATM(new HashMap<>());
        //atm.service.AtmService atmService = new atm.service.AtmService(atm);

        //atmService.
    }

    @Test
    public void shouldCorrectlyAddNewNoteTypeToAtm(){

        Map<Integer, Integer> actualContents = new HashMap<Integer, Integer>();
        actualContents.put(10, 10);
        actualContents.put(20, 20);
        actualContents.put(50, 50);
        actualContents.put(100, 100);

        AustralianATM newAtm = new AustralianATM(actualContents);



    }
}