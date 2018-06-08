// Copyright (c) 2018 Travelex Ltd

public class UseThree {
    public static void main(String[] args){
        //print for all names in args in reverse order

        for (int i = 0; i < args.length; i++){
            int index = args.length - 1 - i;
            System.out.print("Hi, ");
            System.out.print(args[index]);
            System.out.println(". How are you?");
        }

    }
}
