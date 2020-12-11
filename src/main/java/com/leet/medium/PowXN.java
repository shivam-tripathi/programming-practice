package com.leet.medium;

public class PowXN {
  public double myPow(double x, int n) {
    double breakLimit = Math.floor(Math.sqrt(Integer.MAX_VALUE));

    if (n == Integer.MIN_VALUE) {
      return Math.abs(x) == 1 ? 1 : 0;
    }

    int p = Math.abs(n);
    double ans = 1, running = x;

    while (p != 0) {
      if ((p & 1) == 1) {
        ans *= running;
      }
      p = p >> 1;

      if (p > 0 && Math.abs(running) > breakLimit) {
        return 0;
      }
      running *= running; // overflow
    }

    return n < 0 ?  1d / ans : ans;
  }
}
