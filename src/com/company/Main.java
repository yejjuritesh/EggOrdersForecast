package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] orderstring;
        for(int i=0;i<3;i++) {
            orderstring = br.readLine().split(" ");
            Integer[] dayorders = new Integer[orderstring.length];
            for (int j = 0; j < orderstring.length; j++) {
                dayorders[j] = Integer.parseInt(orderstring[j]);
            }
            switch (i) {
                case 0 -> {
                    ArrayList<Integer> day1 = new ArrayList<>();
                    Collections.addAll(day1, dayorders);
                }
                case 1 -> {
                    ArrayList<Integer> day2;
                    day2 = new ArrayList<>();
                    Collections.addAll(day2, dayorders);
                }
                case 2 -> {
                    ArrayList<Integer> day3;
                    day3 = new ArrayList<>();
                    Collections.addAll(day3, dayorders);
                }
                default -> throw new IllegalStateException("Unexpected value: " + i);
            }
        }
    }
}
