package com.leet.medium;

import java.util.Arrays;

public class BeautifulArrangementII {
  int[] beautifulArrangement_accepted(int n, int k) {
    int[] ans = new int[n];
    ans[0] = 1;
    int ptr = 1, kk = k;
    while (ptr < n && k > 0) {
      ans[ptr] = ptr % 2 == 1 ? ans[ptr - 1] + k : ans[ptr - 1] - k;
      k--;
      ptr++;
    }
    if (ptr < n) {
      ans[ptr++] = kk + 2; // maximum value last seen was 1 + k
    }
    while (ptr < n) {
      ans[ptr] = ans[ptr - 1] + 1;
      ptr++;
    }
    return ans;
  }

  public static void main(String[] args) {
    var b = new BeautifulArrangementII();
    System.out.println(Arrays.toString(b.beautifulArrangement_accepted(8, 4)));
  }
}
