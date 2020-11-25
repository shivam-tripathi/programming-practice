package com.dsAlgo.array;

import java.util.*;
import java.lang.Math;

class MaxSubarraySumSolution {
    void maxSubarraySum(int a[]) {
        int curMax = a[0], allMax = a[0], beginMax = 0, begin = 0, endMax = 0, end = 0; // Assuming a.length > 0
        for (int i = 1; i < a.length; i++) {
            if (curMax + a[i] < a[i]) {
                begin = end = i;
                curMax = a[i];
            } else {
                end = i;
                curMax += a[i];
            }

            if (allMax < curMax) {
                allMax = curMax;
                beginMax = begin;
                endMax = end;
            }
        }

        for (int i = beginMax; i <= endMax; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    void maxSubArraySum2(int a[]) {
        int size = a.length;
        int max_so_far = a[0];
        int curr_max = a[0];

        for (int i = 1; i < size; i++) {
            curr_max = Math.max(a[i], curr_max + a[i]);
            max_so_far = Math.max(max_so_far, curr_max);
        }
        System.out.println(max_so_far);
    }
}

public class MaxSubarraySum  {
    public static void main(String[] args) {
        int[] a = { -1, 3, 10, -100, 14 };
        MaxSubarraySumSolution solution = new MaxSubarraySumSolution();
        solution.maxSubarraySum(a);
        solution.maxSubArraySum2(a);
    }
}