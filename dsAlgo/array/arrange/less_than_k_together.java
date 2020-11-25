/**
 * Minimum swaps required to bring all elements less than or equal to k together
 * Use window sliding to keep track of difference between required and already in it
 */

import java.util.*;

class Solution {
    public int minSwaps(ArrayList<Integer> arr, int k) {
        int count = 0;
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) <= k) {
                count++;
            }
        }

        System.out.println("Count " + count);

        int window = 0;
        for (int i = 0; i < count; i++) {
            if (arr.get(i) <= k) {
                window++;
            }
        }

        int minSwaps = count - window;
        for (int i = count; i < arr.size(); i++) {
            window = window - (arr.get(i - count) <= k ? 1 : 0) + (arr.get(i) <= k ? 1 : 0);
            minSwaps = Math.min(minSwaps, count - window);
        }
        return minSwaps;
    }
}

class Main {
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        Random random = new Random();
        int size = 10;
        int lessThan = 3;
        System.out.println("Less than equal to " + lessThan);
        for (int i = 0; i < size; i++) {
            arr.add(random.nextInt(10));
        }
        for (Integer item: arr) System.out.print(item + " "); System.out.println();
        Solution solution = new Solution();
        int swaps = solution.minSwaps(arr, lessThan);
        System.out.println("Swaps: " + swaps);
    }
}