package com.leet.medium;

import java.util.Arrays;

public class PairOfSongsWithTotalDurationDivisibleBy60 {
  public static int numPairsDivisibleBy60(int[] time) {
    int[] mod = new int[60];
    for (int j : time) {
      mod[j % 60]++;
    }
    int ans = (mod[0] * (mod[0] - 1) / 2) + (mod[30] * (mod[30] - 1) / 2);
    for (int idx = 1; idx < 30; idx++) {
      ans += mod[idx] * mod[60-idx];
    }
    return ans;
  }

  public static int numPairsDivisibleBy60_2(int[] time) {
    int[] mod = new int[60];
    int ans = 0;
    for (int t: time) {
      if (t % 60 == 0) {
        ans += mod[0]; // what ever exists till now can make a pair with this element
      } else {
        ans += mod[60-(t % 60)]; // what ever exists till now can make a pair with this element
      }
      mod[t % 60]++;
    }
    return ans;
  }

  public static void main(String[] args) {
    int ans = numPairsDivisibleBy60(new int[] {418,204,77,278,239,457,284,263,372,279,476,416,360,18});
    System.out.println(ans);

    ans = numPairsDivisibleBy60_2(new int[] {418,204,77,278,239,457,284,263,372,279,476,416,360,18});
    System.out.println(ans);
  }
}
