package practice;// Copyright (c) 2018 Travelex Ltd

public class SpringSeason {
    //takes two ints, m and d
    //print true if d of month m is between march 20 and june 20
    //m = 3, d = 20     m = 6, d = 20

    public static void withinDate(int m, int d){

        boolean result = true;

        if (m == 3 && d < 20){
            result = false;
        }
        else if (m == 6 && d > 20){
            result = false;
        }
        else if (m > 6 || m < 3){
            result = false;
        }

        System.out.println(result);

    }
}
