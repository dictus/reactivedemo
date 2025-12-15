package com.spr.reactivedemo.services;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/*Explanation:

current keeps track of the length of the current smooth descent period.

If prices[i-1] - prices[i] == 1, extend the period by incrementing current.

Otherwise, start a new period (current = 1).

count += current adds all subperiods ending at day i.

Why this works:

For a descent of length k, there are k + (k-1) + ... + 1 subperiods ending at each day, but by incrementally adding current, we account for all subperiods efficiently in O(n).*/
public class DuplicateCharsiWords {


    public static void main(String[] args) {
        String[] input = {"bella", "label", "roller"};
        DuplicateCharsiWords dc = new DuplicateCharsiWords();
        System.out.println(dc.commonChars(input)); // Output: [e, l, l]
    }

    public List<String> commonChars(String[] words) {
        // Initialize frequency map with first word
        int[] globalFreq = new int[26];
        Arrays.fill(globalFreq, Integer.MAX_VALUE); // Start with max so we can take min

        for (String word : words) {
            int[] currFreq = new int[26];
            for (char c : word.toCharArray()) {
                currFreq[c - 'a']++;
            }
            // Update globalFreq to keep min frequency
            for (int i = 0; i < 26; i++) {
                globalFreq[i] = Math.min(globalFreq[i], currFreq[i]);
            }
        }

        // Build result list
        List<String> result = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < globalFreq[i]; j++) {
                result.add(String.valueOf((char) (i + 'a')));
            }
        }

        return result;
    }
}
