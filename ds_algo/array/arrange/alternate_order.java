/**
 * Given an array of integers, task is to print the array in the order – smallest number,
 * largest number, 2nd smallest number, 2nd largest number, 3rd smallest number, 3rd largest
 * number and so on
 */

import java.util.*;

class Solution {
    /* Comment from the post:
     * Those who didn't understand how we are storing two values at one location,
     * try using constant value 10 or 100 or 1000 to multiply and modulus instead
     * of max element which is set to (arr[n-1] + 1). Things will become clearer that way.
     * For example, [1, 2, 3, 4, 5, 6] will become {[6]1, [1]2, [5]3, [2]4, [4]5, [3]6}. As you
     * can see the if we divide the new entry by constant (which is 10 or 100 or 1000)
     * we get the desired value at that place. If we take modulus of new entry with constant,
     * we get the original value back.
     */
    void order(ArrayList<Integer> arr) {
        Collections.sort(arr);
        int base = (int)Math.pow(10, Math.ceil(Math.log(arr.get(arr.size() - 1)) / Math.log(10)));
        int min_index = 0, max_index = arr.size() - 1;
        for (int i = 0; i < arr.size(); i++) {
            if (i % 2 == 0) {
                arr.set(i, arr.get(i) + arr.get(max_index--) % base * base);
            } else {
                arr.set(i, arr.get(i) + arr.get(min_index++) % base * base);
            }
        }

        for (int i = 0; i < arr.size(); i++) {
            arr.set(i, arr.get(i) / base);
        }
    }

}


class Main {
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        int size = 19;
        for (int i = 0; i < size; i++) {
            arr.add(i);
        }

        for (Integer item: arr) System.out.print(String.format("%3d ", item)); System.out.println();

        Solution solution = new Solution();
        solution.order(arr);
        for (Integer item: arr) System.out.print(String.format("%3d ", item)); System.out.println();
    }
}
