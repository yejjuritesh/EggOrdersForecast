package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] orderstring;

        List<Double> givendata = new ArrayList<>();

        System.out.println("please provide data for past two days and third days data till 6 pm");

        for(int i=0;i<3;i++) {

            orderstring = br.readLine().split(" ");

            Double[] dayorders = new Double[orderstring.length];

            for (int j = 0; j < orderstring.length; j++) {
                dayorders[j] = Double.parseDouble(orderstring[j]);
            }

            Collections.addAll(givendata, dayorders);

        }

        System.out.println("please provide % of service level required (recommended 95), available service levels : 85, 95, 99, 99.99");

        double servicelevel = Double.parseDouble(br.readLine());

        while(servicelevel!=85 && servicelevel!=95 && servicelevel!=99&& servicelevel!=99.99){
            System.out.println("please choose from available service levels : 85, 95, 99, 99.99");
            servicelevel = Double.parseDouble(br.readLine());
        }

        System.out.print(day3FinalQuantity(givendata, servicelevel));

    }

    private static int day3FinalQuantity(List<Double> givendata,Double servicelevel) {

        int orderedquantity = 0;

        List<Double> previousdaydata = givendata.subList(0,23);

        double mean = calculateMean(previousdaydata);

        double standarddeviation = calculateSD(previousdaydata,mean);

        double sigmafactor=0;

        //As per industry standards generally 95% service level is chosen for better wastage to service level ratio.

        if (servicelevel == 85) {       //1.04 sigmafactor gives 85% service level as per statistics
            sigmafactor = 1.04;
        } else if (servicelevel == 95) {       //1.65 sigmafactor gives 95% service level as per statistics
            sigmafactor = 1.65;
        } else if (servicelevel == 99) {       //2.33 sigmafactor gives 99% service level as per statistics
            sigmafactor = 2.33;
        } else if (servicelevel == 99.99) {       //4 sigmafactor gives 99.99% service level as per statistics
            sigmafactor = 4;
        } else {
            sigmafactor = 1.04;
        }

        double estimatedquantityfor6to10 = 4 * (mean+(sigmafactor * standarddeviation));

        for(int i=24;i<=31;i++){
            orderedquantity+=givendata.get(i);
        }

        return (int)Math.round(orderedquantity+estimatedquantityfor6to10);

    }

    public static double calculateSD(List<Double> givendata,Double mean){  // standard deviation gives the spread of data

        double standardDeviation = 0.0;

        int length = givendata.size();

        for(double num: givendata) {
            standardDeviation += Math.pow(num - mean, 2);
        }

        return Math.sqrt(standardDeviation/length);
    }

    public static double calculateMean(List<Double> givendata){
        double sum = 0.0;

        int length = givendata.size();

        for(double num : givendata) {
            sum += num;
        }

        return sum/length;

    }
}
