// https://www.hackerearth.com/problem/algorithm/monk-and-inversions-arrays-strings-e5aaa427/

package com.codemonk.arraysAndStrings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MonkAndInversions {
  public static int solve(Integer[][] arr, int x, int y) {
    int item = arr[x][y];
    int count = 0;
    for (int i = x; i < arr.length; i++) {
      for (int j = y; j < arr.length; j++) {
        if (item > arr[i][j]) {
          count++;
        }
      }
    }
    return count;
  }

  public static int countInversion(Integer[][] arr) {
    int count = 0;
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr.length; j++) {
        count += solve(arr, i, j);
      }
    }
    return count;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
    int t = Integer.parseInt(obj.readLine());
    while (t-- > 0) {
      int size = Integer.parseInt(obj.readLine());
      Integer [][] arr = new Integer[size][size];
      for (int i = 0; i < size; i++) {
        arr[i] = Arrays.stream(obj.readLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
      }
      System.out.println(countInversion(arr));
    }
  }
}
