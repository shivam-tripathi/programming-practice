package com.leet.easy;

import java.util.stream.IntStream;

public class Fibonacci {
  public int fib_extra_space(int n) {
    int[] arr = new int[31];
    arr[0] = 0;
    arr[1] = 1;
    for (int i = 2; i <= n; i++) {
      arr[i] = arr[i - 2] + arr[i - 1];
    }
    return arr[n];
  }

  public int fibSpaceOptimized(int n) {
    if (n == 0)
      return 0;
    if (n == 1 || n == 2)
      return 1;
    int a = 1, b = 1;
    for (int i = 3; i <= n; i++) {
      int temp = a + b;
      a = b;
      b = temp;
    }
    return b;
  }

  /*
   * a b w x aw + by ax + bz c d y z cw + dy cx + dz
   */
  int[][] multMatrix(int[][] arr, int[][] brr) {
    int a = arr[0][0], b = arr[0][1], c = arr[1][0], d = arr[1][1];
    int w = brr[0][0], x = brr[0][1], y = brr[1][0], z = brr[1][1];
    return new int[][] { new int[] { a * w + b * y, a * x + b * z }, new int[] { c * w + d * y, c * x + d * z } };
  }

  // Log(n)
  public int fibMatrix(int n) {
    if (n == 0)
      return 0;
    if (n == 1)
      return 1;
    int[][] mat = new int[][] { { 1, 1 }, { 1, 0 } };
    int[][] ans = new int[][] { { 1, 1 }, { 1, 1 } };
    n--;
    while (n != 0) {
      if (n % 2 == 1) {
        ans = multMatrix(ans, mat);
      }
      mat = multMatrix(mat, mat);
      n = n >>> 1;
    }
    return ans[0][0];
  }

  public static void main(String[] args) {
    var fib = new Fibonacci();
    IntStream.range(0, 10)
        .forEach(i -> System.out.println(i + " -> " + fib.fib_extra_space(i) + " -> " + fib.fibMatrix(i)));
  }
}
