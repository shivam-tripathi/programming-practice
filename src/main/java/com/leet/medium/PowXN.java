package com.leet.medium;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PowXN {
  public double myPow(double x, int n) {
    if (x == 1) {
      return 1;
    }

    double ans = 1, base = n < 0 ? 1d/x : x;
    int pow = n < 0 ? -n : n;
    while (pow != 0) {
      if ((pow & 1) == 1) {
        ans *= base;
      }
      if (base >= ~(Integer.MIN_VALUE >>> 1)) {
        return 0;
      }
      pow >>= 1;
      base = base * base;
    }

    return ans;
  }
}
