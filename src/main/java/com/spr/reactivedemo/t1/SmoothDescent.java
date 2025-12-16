package com.spr.reactivedemo.t1;

public class SmoothDescent {

    public static long countSmoothDescent(int[] prices) {
        long count = 1; // The first day is always a smooth descent period
        long current = 1; // Length of current smooth descent period

        for (int i = 1; i < prices.length; i++) {
            if (prices[i - 1] - prices[i] == 1) {
                current++;      // Extend current descent period
            } else {
                current = 1;    // Start a new descent period
            }
            count += current;   // Add all subperiods ending at day i
        }

        return count;
    }

    public static void main(String[] args) {
        int[] prices1 = {3, 2, 1, 4};
        int[] prices2 = {8, 6, 7, 7};
        int[] prices3 = {1};

        System.out.println(countSmoothDescent(prices1)); // 7
        System.out.println(countSmoothDescent(prices2)); // 4
        System.out.println(countSmoothDescent(prices3)); // 1
    }
}
