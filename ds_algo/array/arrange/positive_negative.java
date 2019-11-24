/**
 * Rearrange positive and negative numbers in O(n) time and O(1) extra space
 * 1. Rearrange: Negatives on one side, positive on the other
 * 2. Rearrange: Positive on odd positions, negative on even positive
 */

import java.util.*;

class Solution {
    void rearrange(ArrayList<Integer> arr) {
        int size = arr.size();
        int begin = 0, end = size - 1;
        while (begin < end) {
            if (arr.get(begin) >= 0 && arr.get(end) < 0) {
                int temp = arr.get(begin);
                arr.set(begin, arr.get(end));
                arr.set(end, temp);
                begin++;
                end--;
            } else if (arr.get(end) >= 0) {
                end--;
            } else if (arr.get(begin) < 0) {
                begin++;
            }
        }
    }

    void rearrangeAlt(ArrayList<Integer> arr) {
        int size = arr.size();
        int pos = 0, neg = 1;
        while (pos < size && neg < size) {
            // System.out.println(pos + " " + neg);
            if (arr.get(pos) >= 0) {
                pos += 2;
            } else if (arr.get(neg) < 0) {
                neg += 2;
            } else if (arr.get(pos) < 0 && arr.get(neg) >= 0) {
                int temp = arr.get(pos);
                arr.set(pos, arr.get(neg));
                arr.set(neg, temp);
                pos += 2;
                neg += 2;
            }
        }
    }
}

class Main {
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 25; i++) {
            arr.add(random.nextInt(100) * (random.nextBoolean() ? -1 : 1));
        }

        for (Integer item: arr) System.out.print(item + " "); System.out.println();

        Solution solution = new Solution();

        solution.rearrangeAlt(arr);
        for (Integer item: arr) System.out.print(item + " "); System.out.println();

        solution.rearrange(arr);

        for (Integer item: arr) System.out.print(item + " "); System.out.println();
    }
}