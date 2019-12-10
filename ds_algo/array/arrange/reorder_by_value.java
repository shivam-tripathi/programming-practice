/**
 * Given an array of size n where all elements are distinct and in range
 * from 0 to n-1, change contents of arr[] so that arr[i] = j is changed
 * to arr[j] = i.
 *
 * Similar: Rearrange an array so that arr[i] becomes arr[arr[i]] with
 * O(1) extra space
 */

import java.util.*;

class Solution {
    ArrayList<Integer> reorder(ArrayList<Integer> arr) {
        int n = arr.size();
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) < n) {
                int index = i, item = arr.get(i);
                int beginItem = item;
                while(item < n) { // Toughest part of the problems like these is to find the break point for the loop
                    int tempItem = arr.get(item);
                    arr.set(item, n + index);
                    index = item;
                    item = tempItem;
                }
            }
        }

        for (int i = 0; i < arr.size(); i++) {
            arr.set(i, arr.get(i) % n);
        }

        return arr;
    }

    ArrayList<Integer> reorderNoCycle(ArrayList<Integer> arr) {
        int n = arr.size();
        for (int i = 0; i < arr.size(); i++) {
            int item = arr.get(i) % n;
            arr.set(item, (arr.get(item) % n) + (i * n));
        }

        for (int i = 0; i < arr.size(); i++) {
            arr.set(i, arr.get(i) / n);
        }
        return arr;
    }
}

class Main {
    public static void main(String args[]) {
        Solution sol = new Solution();
        Integer a[]  = {2, 0, 1, 4, 5, 3};
        ArrayList<Integer> arr = new ArrayList<Integer>(Arrays.asList(a));
        for (Integer item: sol.reorder(new ArrayList<Integer>(arr))) System.out.print(item + " "); System.out.println();
        for (Integer item: sol.reorderNoCycle(new ArrayList<Integer>(arr))) System.out.print(item + " "); System.out.println();
    }
}
