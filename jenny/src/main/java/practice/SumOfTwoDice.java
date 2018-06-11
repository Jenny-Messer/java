package practice;// Copyright (c) 2018 Travelex Ltd

public class SumOfTwoDice {
    public static void main(String[] args){


    }

    public static void roll(){
        int sides = 6;

        int Dice1 = 1 + (int) (Math.random() * sides);
        int Dice2 = 1 + (int) (Math.random() * sides);

        int sum = Dice1 + Dice2;

        System.out.println(sum);
    }
}
