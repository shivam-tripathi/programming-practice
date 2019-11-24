/**
 * Keeping ordering the same.
 * Given an array of random numbers, Push all the zero’s of a given array to the
 * end of the array. For example, if the given arrays is
 * {1, 9, 8, 4, 0, 0, 2, 7, 0, 6, 0}, it should be changed to
 * {1, 9, 8, 4, 2, 7, 6, 0, 0, 0, 0}. The order of all other elements should be
 * same. Expected time complexity is O(n) and extra space is O(1).
 *
 * Keep two pointers: begin and end. End should point to next non zero number and begin points to
 * present number. Numbers before begin are all non zero. Thus end should always be begin + 1
 * incrementing to find next arr[end] != 0.
 *
 * Simpler solution is far better.
 *
 */

import java.util.*;

class Solution {
    void moveZeros(ArrayList<Integer> arr) {
        int size = arr.size();
        int begin = 0, end = 1;
        while (begin < size && end < size) {
            if (end < begin) {
                end = begin + 1;
            } else if (arr.get(begin) == 0 && arr.get(end) != 0) {
                arr.set(begin, arr.get(end));
                arr.set(end, 0);
                begin++;
                end++;
            } else if (arr.get(end) == 0) {
                end++;
            } else if (arr.get(begin) != 0) {
                begin++;
                end = begin + 1;
            }
        }
    }

    void simplerMoveZeros(ArrayList<Integer> arr) {
        int count = 0;
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) != 0) {
                int temp = arr.get(count);
                arr.set(count++, arr.get(i));
                arr.set(i, temp);
            }
        }
    }
}

class Main {
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 25; i++) {
            arr.add(random.nextInt(10) * (random.nextBoolean() ? 0 : 1));
        }
        for (Integer item: arr) System.out.print(item + " "); System.out.println();
        Solution solution = new Solution();
        solution.simplerMoveZeros(arr);
        for (Integer item: arr) System.out.print(item + " "); System.out.println();
    }
}