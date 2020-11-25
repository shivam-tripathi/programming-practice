package com.dsAlgo.array.rotations; /**
 * Reversal algorithm for right rotated array
 */

import java.util.*;

class ReverseRightRotateSolution {
    void rotate(ArrayList<Integer> arr, int begin, int end) {
        while(begin < end) {
            int temp = arr.get(end);
            arr.set(end--, arr.get(begin));
            arr.set(begin++, temp);
        }
    }
    void reverseRightRotate(ArrayList<Integer> arr, int d) {
        int size = arr.size();
        d = d % size;
        if (d == 0) {
            return;
        }
        rotate(arr, size - d, size - 1);
        rotate(arr, 0, size - d - 1);
        rotate(arr, 0, size - 1);
    }
}

public class ReverseRightRotate {
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            arr.add(i);
        }

        for (Integer item: arr) System.out.print(item + " "); System.out.println();
        (new ReverseRightRotateSolution()).reverseRightRotate(arr, 10);
        for (Integer item: arr) System.out.print(item + " "); System.out.println();
    }
}