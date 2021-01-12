package com.leet.easy;

public class KthMissingPositiveNumber {
  public int findKthPositive(int[] arr, int k) {
    int count = 0, num = 1, idx = 0;
    while (count < k) {
      if (idx < arr.length && arr[idx] == num) {
        idx++;
      } else {
        count++;
      }
      num++;
    }
    return num - 1;
  }

  // Find the lower bound - and calculate difference
  public int findKthPositiveLogn(int[] arr, int k) {
    int low = 0, high = arr.length - 1;
    while (low < high) {
      int mid = low + (high - low + 1) / 2;
      int count = arr[mid] - mid - 1;
      if (count < k) {
        low = mid;
      } else {
        high = mid - 1;
      }
    }

    // IMP! Case when low is greater than target
    if (low == 0 && arr[0] > k) {
      low--;
    }
    return (k + low + 1);
  }
}
