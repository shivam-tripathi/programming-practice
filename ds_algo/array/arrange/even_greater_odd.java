/**
 * Rearrange array such that even index elements are smaller and odd index elements are greater
 *
 * Given an array, rearrange the array such that :
 * If index i is even, arr[i] <= arr[i+1]
 * If index i is odd, arr[i] >= arr[i+1]
 *
 * \/\/\/\/\/\/ pattern
 */

import java.util.*;

class Solution {
    void rearrange(ArrayList<Integer> arr) {
        for (int i = 1; i < arr.size(); i++) {
            // If even position element is less than it's previous one, then swap
            // If odd position element is greater than it's previous one, then swap
            if ((i % 2 == 0 && arr.get(i) < arr.get(i - 1)) || (i % 2 == 1 && arr.get(i) > arr.get(i - 1))) {
                int temp = arr.get(i);
                arr.set(i, arr.get(i - 1));
                arr.set(i - 1, temp);
            }
        }
    }
}

class Main {
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        Random random = new Random();

        for(int i = 0; i < 10 + random.nextInt(10); i++) {
            arr.add(random.nextInt(1000));
        }

        for (Integer item: arr) System.out.print(item + " "); System.out.println();
        Solution solution = new Solution();
        solution.rearrange(arr);
        for (Integer item: arr) System.out.print(item + " "); System.out.println();
    }
}
