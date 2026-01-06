package com.dsAlgo.dc;

import java.util.Map;
import java.util.Scanner;

public class Pow {
  private static double pow(double n, int p) {
    double ans = 1, running = n, breakLimit = Math.floor(Math.sqrt(Integer.MAX_VALUE));
    boolean neg = p < 0;
    p = Math.abs(p); // p is NOT always positive, Integer.MIN_VALUE leaks

    // One way to potentially handle Integer.MIN_VALUE
    /*
    if (p == Integer.MIN_VALUE) {
      return Math.abs(n) == 1 ? 1 : 0;
    }
     */

    while (p != 0) {
      // If bit is set, collect this result
      if ((p & 1) == 1) {
        ans *= running;
      }

      // Shift for next result
      p = Math.abs(p >> 1); // Take absolute from this shift to handle Integer.MIN_VALUE

      // Handle potential overflow in generating next running. If not required, ignore (p == 0)
      if (p != 0 && Math.abs(running) > breakLimit) {
        return 0;
      }
      running *= running;
    }
    return neg ? 1 / ans : ans;
  }

  static double power(double x, double y) {
    if(y == 0)
      return 1;
    double temp = power(x, Math.floor(y / 2));
    if (y % 2 == 0) {
      return temp * temp;
    } else {
      if(y > 0) {
        return x * temp * temp;
      } else {
        return (temp * temp) / x;
      }
    }
  }

  public static void run() {
    for (int i = 0; i < 50; i++) {
      int n = (int)(Math.random() * 50);
      int p = (int)(Math.random() * 30);
      if (!Double.valueOf(Math.pow(n, p)).equals(pow(n, p))) {
        System.out.println(n + " " + p + " => " + Math.pow(n, p) + " " + pow(n, p));
      }
      if (!Double.valueOf(Math.pow(n, p)).equals(power(n, p))) {
        System.out.println(n + " " + p + " -> " + Math.pow(n, p) + " " + power(n, p));
      }
    }
  }

  public static void run2() {
    Scanner sc = new Scanner(System.in);
    double n = sc.nextDouble();
    int p = sc.nextInt();
    System.out.println(pow(n, p) + " " + Math.pow(n, p) + " " + power(n, p));
  }

  public static void main(String[] args) {
    run2();
  }
}
