package com.dsAlgo.array.arrange;
/**
 * Minimum swaps required to bring all elements less than or equal to k together
 * Use window sliding to keep track of difference between required and already in it
 */

import java.util.*;

class LessThanKTogetherSolution {
    public int minSwaps(ArrayList<Integer> arr, int k) {
        int count = 0;
        for (Integer integer : arr) {
            if (integer <= k) {
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

public class LessThanKTogether {
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
        LessThanKTogetherSolution solution = new LessThanKTogetherSolution();
        int swaps = solution.minSwaps(arr, lessThan);
        System.out.println("Swaps: " + swaps);
    }
}