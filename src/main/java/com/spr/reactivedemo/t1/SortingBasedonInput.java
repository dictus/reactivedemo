package com.spr.reactivedemo.t1;


/*Problem: Given an array like ["raJan", "Raji", "Alex", "TOM", "aharrY" "berrY"]
Sort by: Capital letters first, then lowercase letters
["Alex","Raji","TOM","aharrY" "berrY","ranJan"]
 */
public class SortingBasedonInput {
    /*011      1001
    TOM   Alex
     ranJan 010111*/

    public static void main(String[] args) {
        // List<String> given = new String[]{"raJan", "Raji", "Alex", "TOM", "aharrY","berrY"};


        for (char c : "TOM".toCharArray()) {
            System.out.println(c);
        }

        System.out.println("TOM".toCharArray());
    }


}
