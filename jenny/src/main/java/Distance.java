// Copyright (c) 2018 Travelex Ltd

public class Distance {
    public static void main(int[] args){ //?
        //takes 2 ints x and y, prints euclidean dist from (x,y) to origin (0,0)
        int x = args[0];
        int y = args[1];

        //should be (x-0)*(x-0) to follow proper formula, but in this case it's not needed
        double dist = Math.sqrt( x*x + y*y );

        System.out.println(dist);

    }
}
