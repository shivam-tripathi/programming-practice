/**
 * Given two integer arrays of same size, “arr[]” and “index[]”,
 * reorder elements in “arr[]” according to given index array.
 * It is not allowed to modify given array arr’s length.
 * Input:  arr[]   = [10, 11, 12];
 *         index[] = [1, 0, 2];
 * Output: arr[]   = [11, 10, 12]
 *         index[] = [0,  1,  2]
 */

import java.util.*;

class Solution {
    // O(n). Loop over all elements, check if index == i. If not, move it to it's correct place.
    // Repeat till we reach the initial index.
    void orderByIndexArr(ArrayList<Integer> arr, ArrayList<Integer> index) {
        for (int i = 0; i < arr.size(); i++) {
            int beginIndex = i;
            int idx = index.get(i);
            int item = arr.get(i);

            while (index.get(i) != i) {
                int tempItem = arr.get(idx);
                int tempIdx = index.get(idx);
                arr.set(idx, item);
                index.set(idx, idx);
                idx = tempIdx;
                item = tempItem;
            }
        }
    }
}

class Main {
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        ArrayList<Integer> index = new ArrayList<>();
        int size = 10;
        Random rand = new Random();


        for (int i = 0; i < size; i++) {
            arr.add(rand.nextInt(100));
            index.add(i);
        }

        // Shuffle by Durstenfeld algorithm
        for (int i = arr.size() - 1; i >= 0; i--) {
            int idx = rand.nextInt(i + 1);
            int item = index.get(idx);
            index.set(idx, index.get(i));
            index.set(i, item);
        }

        for (Integer item: arr) System.out.print(String.format("%3d", item)); System.out.println();
        for (Integer item: index) System.out.print(String.format("%3d", item)); System.out.println();

        Solution sol = new Solution();
        sol.orderByIndexArr(arr, index);

        for (Integer item: arr) System.out.print(String.format("%3d", item)); System.out.println();
        for (Integer item: index) System.out.print(String.format("%3d", item)); System.out.println();
    }
}
