package com.leet.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FourSumII {
  public static int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int a : A) {
      for (int b : B) {
        map.merge(a + b, 1, Integer::sum);
        // map.put(a + b, 1 + map.getOrDefault(a + b, 0)); // slow
      }
    }

    int ans = 0;
    for (int c : C) {
      for (int d : D) {
        ans += map.getOrDefault(-1 * (c + d), 0);
      }
    }

    return ans;
  }

  // Slower
  public static int fourSumCount2(int[] A, int[] B, int[] C, int[] D) {
    Map<Integer, Integer> mapAB = new HashMap<>();
    Map<Integer, Integer> mapCD = new HashMap<>();
    int len = A.length, ans = 0;
    for (int i = 0; i < len; i++) {
      for (int j = 0; j < len; j++) {
        int sumAB = A[i] + B[j];
        int sumCD = C[i] + D[j];

        ans += mapCD.getOrDefault(-1 * sumAB, 0);
        ans += mapAB.getOrDefault(-1 * sumCD, 0);
        ans += sumAB + sumCD == 0 ? 1 : 0;

        mapAB.merge(sumAB, 1, Integer::sum);
        mapCD.merge(sumCD, 1, Integer::sum);
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    System.out.println(fourSumCount(
            new int[]{1, 2},
            new int[]{-2, -1},
            new int[]{-1, 2},
            new int[]{0, 2}
    ));
    System.out.println(fourSumCount2(
            new int[]{1, 2},
            new int[]{-2, -1},
            new int[]{-1, 2},
            new int[]{0, 2}
    ));
  }
}
