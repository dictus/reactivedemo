package com.spr.reactivedemo.services;

public class stockbuty {

    public static void main(String[] args) {

        int[] prices = {3, 7, 9, 6, 4};

        int minPrice = Integer.MAX_VALUE;  // minimum price seen so far
        int maxProfit = 0;                 // maximum profit

        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price;          // update min price
            }
            int profit = price - minPrice; // potential profit if sold today
            if (profit > maxProfit) {
                maxProfit = profit;        // update max profit
            }
        }

        System.out.println("Maximum Profit: " + maxProfit);
    }
}



