/**
 * Reversal algorithm for rotating arrays
 *      - Reverse first d numbers in place
 *      - Reverse remaining numbers (size - d numbers) in place
 *      - Reverse whole list
 */

import java.util.*;

class Solution {
    void reverse(ArrayList<Integer> arr, int begin, int end) {
        int begint = begin, endt = end;
        while (begin < end) {
            int temp = arr.get(end);
            arr.set(end, arr.get(begin));
            arr.set(begin, temp);
            begin++;
            end--;
        }

        // for (int i = begint; i <= endt; i++) {
        //     System.out.print(arr.get(i) + " ");
        // }
        // System.out.println();
    }

    void rotate(ArrayList<Integer> arr, int d) {
        reverse(arr, 0, d - 1);
        reverse(arr, d, arr.size() - 1);
        reverse(arr, 0, arr.size() - 1);
        for (int i = 0; i < arr.size(); i++) {
            System.out.print(arr.get(i) + " ");
        }
        System.out.println();
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Random random = new Random();
        int size = 12;
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            // arr.add(random.nextInt(100));
            arr.add(i);
        }

        for (int i = 0; i < 10; i++) {
            ArrayList<Integer> tempArr = new ArrayList<>();
            for (Integer item: arr) {
                tempArr.add(item);
            }
            solution.rotate(tempArr, i);
        }
    }
}