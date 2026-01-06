/**
 * Given an array of integers arr, return true if and only if it is a valid mountain array.
 *
 * Recall that arr is a mountain array if and only if:
 *
 *     arr.length >= 3
 *     There exists some i with 0 < i < arr.length - 1 such that:
 *         arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 *         arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [2,1]
 * Output: false
 *
 * Example 2:
 *
 * Input: arr = [3,5,5]
 * Output: false
 *
 * Example 3:
 *
 * Input: arr = [0,3,2,1]
 * Output: true
 *
 * Constraints:
 *
 *     1 <= arr.length <= 104
 *     0 <= arr[i] <= 104
 */

package com.leet.easy;

public class ValidMountainArray {
  static public boolean validMountainArray(int[] arr) {
    if (arr.length <= 2 || arr[1] - arr[0] <= 0) {
      return false;
    }

    int peak = arr[1];
    boolean downwards = false;

    for (int i = 2; i < arr.length; i++) {
      int diff = arr[i] - arr[i-1];
      if (diff == 0) {
        return false;
      } else if (diff > 0) {
        if (!downwards) peak = arr[i];
        if (downwards) return false;
      } else {
        downwards = true;
      }
    }

    return arr[arr.length - 1] != peak;
  }

  // Fast because we remove equality if checks, 100%
  public static boolean validMountainArrayTwoPointer(int[] arr) {
    int low = 0, high = arr.length - 1;
    while(low < high) {
      if (arr[low] < arr[low+1]) {
        low++;
      } else if (arr[high-1] > arr[high]) {
        high--;
      } else {
        break;
      }
    }
    return low != 0 && high != arr.length - 1 && high == low;
  }

  public static void main(String[] args) {
    System.out.println(validMountainArray(new int[] {2, 1}));
    System.out.println(validMountainArrayTwoPointer(new int[] {2, 1}));
    System.out.println(validMountainArray(new int[] {3, 5, 5}));
    System.out.println(validMountainArrayTwoPointer(new int[] {3, 5, 5}));
    System.out.println(validMountainArray(new int[] {0, 3, 2, 1}));
    System.out.println(validMountainArrayTwoPointer(new int[] {0, 3, 2, 1}));
    System.out.println(validMountainArray(new int[]{0, 1, 3, 2, 4, 3, 2, 1}));
    System.out.println(validMountainArrayTwoPointer(new int[]{0, 1, 3, 2, 4, 3, 2, 1}));
  }
}
