import java.util.*;
import java.io.*;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int size = s.length();
        HashMap<Character, Integer> map = new HashMap<>();
        Integer l = 0, r = 0, maxSize = 1;
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
}

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
        String s = obj.readLine();
        Solution solve = new Solution();
        int ans = solve.lengthOfLongestSubstring(s);
        System.out.printf("%d%n", ans);
    }
}

/* Notice:
 * lastSeen >= l (equal to part)
 * After loop action
 * Correct initialization of initial values (don't be lazy)
 */
