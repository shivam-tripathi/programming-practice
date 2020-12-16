package com.leet;

import java.util.*;
import java.io.*;

class LongestSubstringWithoutRepeatingCharacters {
    public static int lengthOfLongestSubstring(String s) {
        int size = s.length();
        HashMap<Character, Integer> map = new HashMap<>();
        int l = 0, r = 0, maxSize = 0;
        for (; r < size; r++) {
            Integer lastSeen = map.get(s.charAt(r));
            if (lastSeen != null && lastSeen.compareTo(l) >= 0) {
                maxSize = Math.max(r - l, maxSize);
                l = lastSeen + 1;
            }
            map.put(s.charAt(r), r);
        }
        maxSize = Math.max(r - l, maxSize);
        return maxSize;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
        String s = obj.readLine();
        System.out.printf("%d%n", lengthOfLongestSubstring(s));
    }
}

/* Notice:
 * lastSeen >= l (equal to part)
 * After loop action
 * Correct initialization of initial values (don't be lazy)
 */
