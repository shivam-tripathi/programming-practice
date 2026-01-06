package com.dsAlgo.greedy;

/**
 * Split n into maximum composite numbers
 * https://www.geeksforgeeks.org/split-n-maximum-composite-numbers/
 * Given n, print the maximum number of composite numbers that sum up to n. First few composite numbers are
 * 4, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20 ...
 * Examples:
 *
 * Input: 90
 * Output: 22
 * Explanation: If we add 21 4's, then we
 * get 84 and then add 6 to it, we get 90.
 *
 * Input: 10
 * Output: 2
 * Explanation: 4 + 6 = 10
 *
 * Below are some important observations.
 * 1. If the number is less then 4, it won’t have any combinations.
 * 2. If the number is 5, 7, 11, it won't have any splitting.
 * 3. Since smallest composite number is 4, it makes sense to use maximum number of 4s.
 * 4. For numbers that don’t leave a composite remainder when divided by 4, we do following. If remainder is 1, we
 * subtract 9 from it to get the number which is perfectly divisible by 4. If the remainder is 2, then subtract 6 from
 * it to make n a number which is perfectly divisible by 4. If the remainder is 3, then subtract 15 from it to make n
 * perfectly divisible by 4, and 15 can be made up by 9 + 6.
 *
 * If we do a mod by 4 - the possible numbers we can get:
 * 1. M%4 == 0 In this case it is perfectly divisible by 4
 * 2. M%4 == 1 In this case it has 1 extra value. If we subtract 2*4 + 1 = 9 from it (which is the next composite number
 * which we can clearly subtract here with minimal increase in count), we can make it divisible by 4
 * 3. M%4 == 2 In this case we can subtract 4+2 = 6
 * 4. M%4 == 3 In this case we can subtract 4*3+3 = 15
 */

public class SplitNIntoMaximumCompositeNumbers {
  public int maximumCompositeCount(int n) {
    if (n < 4) return -1;
    int mod = n%4;
    switch (mod) {
      case 0: return n/4;
      case 1: return n < 9 ? -1 : (n-9)/4 + 1;
      case 2: return n < 6 ? -1 : (n-6)/4 + 1;
      case 3: return n < 15 ? -1 : (n-15)/4 + 2;
      default: return -1;
    }
  }
}
