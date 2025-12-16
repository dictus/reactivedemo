package com.spr.reactivedemo.t1;

import java.util.Arrays;

public class SortByCase {
    public static void main(String[] args) {
        String[] arr = {"raJan", "Raji", "Alex", "TOM", "aharrY", "berrY"};

        Arrays.sort(arr, (a, b) -> {
            boolean aUpper = Character.isUpperCase(a.charAt(0));
            boolean bUpper = Character.isUpperCase(b.charAt(0));

            // Capital first
            if (aUpper && !bUpper) return -1;
            if (!aUpper && bUpper) return 1;

            // If both same case → sort normally
            return a.compareToIgnoreCase(b);
        });

        System.out.println(Arrays.toString(arr));
    }
}
