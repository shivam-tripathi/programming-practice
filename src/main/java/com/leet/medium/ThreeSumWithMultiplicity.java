package com.leet.medium;

public class ThreeSumWithMultiplicity {
  public int threeSumMulti(int[] arr, int target) {
    int[] sum = new int[201];
    int ans = 0, mod = (int)1e9+7;
    for (int i = arr.length - 2; i >= 0; i--) {
      int tmp = target - arr[i];
      if (tmp >= 0 && tmp < 201) {
        ans = (ans % mod + sum[target - arr[i]] % mod) % mod;
      }
      for (int j = i + 1; j < arr.length; j++) {
        sum[arr[i]+arr[j]]++;
      }
    }
    return ans;
  }
}
