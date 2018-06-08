// Copyright (c) 2018 Travelex Ltd

public class WindChill {
    public static void main(String[] args){
        //takes two double command-line arguments t and v and prints the wind chill
    }

    public static void calculate(double t, double v){
        double w = 35.74 + 0.6215*t + (0.4275*t - 35.75) * Math.pow(v,0.16);
        System.out.println(w);
    }
}
