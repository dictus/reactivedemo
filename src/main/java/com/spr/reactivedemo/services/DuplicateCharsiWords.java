/*
package com.spr.reactivedemo.services;
*/
/*
* Given a string array words, return an array of all characters that show up in all strings within the words (including duplicates). You may return the answer in any order.



Example 1:

Input: words = ["bella","label","roller"]
Output: ["e","l","l"]
Example 2:

Input: words = ["cool","lock","cook"]
Output: ["c","o"]


Constraints:

1 <= words.length <= 100
1 <= words[i].length <= 100
words[i] consists of lowercase English letters.*//*


 */
/*
* Balanced strings are those that have an equal quantity of 'L' and 'R' characters.

Given a balanced string s, split it into some number of substrings such that:

Each substring is balanced.
Return the maximum number of balanced strings you can obtain.



Example 1:

Input: s = "RLRRLLRLRL"
Output: 4
Explanation: s can be split into "RL", "RRLL", "RL", "RL", each substring contains same number of 'L' and 'R'.
Example 2:

Input: s = "RLRRRLLRLL"
Output: 2
Explanation: s can be split into "RL", "RRRLLRLL", each substring contains same number of 'L' and 'R'.
Note that s cannot be split into "RL", "RR", "RL", "LR", "LL", because the 2nd and 5th substrings are not balanced.
Example 3:

Input: s = "LLLLRRRR"
Output: 1
Explanation: s can be split into "LLLLRRRR".


Constraints:

2 <= s.length <= 1000
s[i] is either 'L' or 'R'.
s is a balanced string.*//*

import java.util.*;

public class DuplicateCharsiWords {

    public static void main(String[] args) {
        List<String> input = List.of("bella","label","roller");

    }

    public List<String> commonChars(String[] words){
        Map<Character, Integer> globalMap = new HashMap<>();

        for (char c : words[0].toCharArray()){
            globalMap.put(c, globalMap.getOrDefault(c,0)+1);
        }
        for (int i = 1; i < words.length; i++) {
            Map<Character, Integer> currMap = new HashMap<>();

        }

        return res;


    }
}
*/
