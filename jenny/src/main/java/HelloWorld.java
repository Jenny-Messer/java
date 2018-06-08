// Copyright (c) 2018 Travelex Ltd

public class HelloWorld {
    public static void main(String[] args){
        //print hello world * 10
        for (int i = 0; i<10; i++){
            System.out.println("Hello, World!");
        }

        String names[] = {"Alice", "Carol", "Bob"};

        UseThree.main(names);

        //skipped initials.java bc it's a waste of time

        //gets distance between (5,6) and (0,0)
        String coords[] = {"5","6"};
        Distance.main(coords);

    }
}
