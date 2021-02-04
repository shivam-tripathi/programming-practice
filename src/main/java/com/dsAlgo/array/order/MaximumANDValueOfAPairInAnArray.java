package com.dsAlgo.array.order;

public class MaximumANDValueOfAPairInAnArray {
  static int checkBit(int pattern, int[] arr) {
    int count = 0;
    for (int i = 0; i < arr.length; i++) {
      if ((pattern & arr[i]) == pattern) {
        count++;
      }
    }
    return count;
  }

  static int maxAND(int[] arr) {
    int res = 0, count;
    for (int bit = 31; bit >= 0; bit--) {
      count = checkBit(res | (1<<bit), arr);
      if (count >= 2) {
        res |= (1<<bit);
      }
    }
    return res;
  }

  public static void main(String[] args) {
    int[] arr = new int[]{4, 8, 6, 2};
    System.out.println(maxAND(arr));
  }
}
