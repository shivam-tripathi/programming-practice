package com.dsAlgo.array;

// Largest sum of positive elements subarray

import java.util.*;

class MaxSubarraySumPosSolution {
    public ArrayList<Integer> maxset(ArrayList<Integer> A) {
        ArrayList<Integer> sol = new ArrayList<>();

        // Declare variables
        int beginIndex = -1, endIndex = 0; // endIndex points to last element + 1 position
        int maxBegin = -1, maxEnd = 0; long maxSum = -1;

        // Begin this iteration
        while (beginIndex < A.size() && endIndex <= A.size()) {
            long sum = 0; // Set sum for this iteration = 0

            // Get the beginning index (the first postive since last endIndex - 1)
            for (beginIndex = endIndex; beginIndex < A.size() && A.get(beginIndex) < 0; beginIndex++);
            // Get the ending index (the first negative integer since beginIndex)
            for (endIndex = beginIndex; endIndex < A.size() && A.get(endIndex) >= 0; endIndex++) {
                sum += A.get(endIndex);
            };

            if ((sum > maxSum) || (sum == maxSum && (maxEnd - maxBegin) < (endIndex - beginIndex))) {
                maxSum = sum;
                maxBegin = beginIndex;
                maxEnd = endIndex;
            }
        }

        for (int i = maxBegin; i < maxEnd && i < A.size(); i++) {
            sol.add(A.get(i));
        }

        return sol;
    }
}

public class MaxSubarraySumPos {
    public static void main(String[] args) {
        MaxSubarraySumPosSolution solution = new MaxSubarraySumPosSolution();
        int[][] arr = {
            {0, 0, 0, 0, -1, 0, 0},
            { -1, -1, -1, -1, -1  },
            {1, -1, 2, -1, 0, 1, 1, -1},
            {3, -4, -23, 1, 2, 0, 1, -3, 1, 1, 1, 1}
        };

        for (int i = 0; i < arr.length; i++) {
            ArrayList<Integer> arrList = new ArrayList<>();
            for (int j = 0; j < arr[i].length; j++) {
                arrList.add(arr[i][j]);
            }
            ArrayList<Integer> maxSet = solution.maxset(arrList);
            for (Integer k: maxSet) {
                System.out.print(k + " ");
            }
            System.out.println("\n----\n");
        }
    }
}