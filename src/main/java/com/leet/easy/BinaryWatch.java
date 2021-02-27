package com.leet.easy;

/**
 * 401. Binary Watch
 * https://leetcode.com/problems/binary-watch/
 * Easy
 * A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the
 * minutes (0-59).
 * Each LED represents a zero or one, with the least significant bit on the right.
 * For example, the above binary watch reads "3:25".
 * Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times
 * the watch could represent.
 * Example:
 * Input: n = 1
 * Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
 * Note:
 *     The order of output does not matter.
 *     The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
 *     The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".
 */

import java.util.ArrayList;
import java.util.List;

public class BinaryWatch {
  public List<String> readBinaryWatch(int num) {
    var digits = new int[][]{
            new int[]{0}, // 0 set bits
            new int[]{1, 2, 4, 8, 16, 32}, // 1 set bit
            new int[]{3, 5, 6, 9, 10, 12, 17, 18, 20, 24, 33, 34, 36, 40, 48}, // 2 set bits
            new int[]{7, 11, 13, 14, 19, 21, 22, 25, 26, 28, 35, 37, 38, 41, 42, 44, 49, 50, 52, 56},
            new int[]{15, 23, 27, 29, 30, 39, 43, 45, 46, 51, 53, 54, 57, 58}, // 4 set bits
            new int[]{31, 47, 55, 59} // 5 set bits
    };

    var sb = new StringBuilder();
    var ans = new ArrayList<String>();
    for (int i = 0; i <= num; i++) {
      int hourBits = i;
      int minuteBits = num - hourBits;
      if (hourBits > 4) break; // cannot have more than 4 bits for hours anyways
      for (var h : digits[hourBits]) {
        // hours cannot be more than 11
        // minuteBits cannot be more than 6, and max possible set bits can only be 5
        if (h >= 12 || minuteBits >= 6) break;
        sb.append(h).append(":");
        for (var m : digits[minuteBits]) {
          if (m >= 60) break; // Minutes cannot be more than 59
          if (m < 10) sb.append('0'); // Minutes are 0 left padded
          sb.append(m);
          ans.add(sb.toString());
          sb.setLength(h / 10 + 2); // Reset length of string builder (3 if h > 10 else 2)
        }
        sb.setLength(0); // Reset for next hour iteration
      }
    }
    return ans;
  }
}


