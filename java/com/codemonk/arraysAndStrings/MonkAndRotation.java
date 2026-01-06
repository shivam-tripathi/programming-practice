// https://www.hackerearth.com/problem/algorithm/monk-and-rotation-3-bcf1aefe/

package com.codemonk.arraysAndStrings;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MonkAndRotation {
  static int gcd(int a, int b) {
    while (b != 0) {
      int temp = a % b;
      a = b;
      b = temp;
    }

    return a;
  }

  public static void rotate(int[] arr, int k) {
    int l = arr.length;
    int setSize = gcd(Math.max(l, k), Math.min(l, k));
    for (int i = 0; i < setSize; i++) {
      int curIdx = i, curVal = arr[i];
      do {
        int nextIdx = (curIdx + k) % l;
        int nextVal = arr[nextIdx];
        arr[nextIdx] = curVal;
        curVal = nextVal;
        curIdx = nextIdx;
      } while (curIdx != i);
    }
    System.out.println(Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
  }

  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
    int t = sc.nextInt();
    while (t-- > 0) {
      int size = sc.nextInt();
      int k = sc.nextInt();
      int[] a = new int[size];
      IntStream.range(0, size).forEach(i -> a[i] = sc.nextInt());
      rotate(a, k);
    }
  }
}
