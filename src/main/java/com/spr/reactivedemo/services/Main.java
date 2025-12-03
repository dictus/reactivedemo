package com.spr.reactivedemo.services;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += i;
        }
        //Stream.of(1,3,4).forEach(Collectors::summingInt);
        System.out.printf(String.valueOf(sum));


        List<Integer> as = Arrays.asList(1, 3, 4, 5, 55, 42, 66);


        System.out.println(nLargeteleemt(2, as));

    }

    static Integer nLargeteleemt(int pos, List<Integer> list) {

        Stack<Integer> asST = new Stack<>();

        LinkedList<Integer> asLt = new LinkedList<>();


        int size = list.size();
        int highest = 0;
        for (int i = 0; i < size; i++) {
            for (int j = size; j > i; j--) {

                if (list.get(i) > list.get(j)) {
                    highest = j;
                }

            }

        }
        ;

        return 0;
    }


}
