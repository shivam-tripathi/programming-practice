package com.leet.medium;

/**
 * 93. Restore IP Addresses
 * https://leetcode.com/problems/restore-ip-addresses/
 * Medium
 * Given a string s containing only digits, return all possible valid IP addresses that can be obtained from s. You can
 * return them in any order.
 * A valid IP address consists of exactly four integers, each integer is between 0 and 255, separated by single dots and
 * cannot have leading zeros. For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses and "0.011.255.245",
 * "192.168.1.312" and "192.168@1.1" are invalid IP addresses.
 * Example 1:
 * Input: s = "25525511135"
 * Output: ["255.255.11.135","255.255.111.35"]
 * Example 2:
 * Input: s = "0000"
 * Output: ["0.0.0.0"]
 * Example 3:
 * Input: s = "1111"
 * Output: ["1.1.1.1"]
 * Example 4:
 * Input: s = "010010"
 * Output: ["0.10.0.10","0.100.1.0"]
 * Example 5:
 * Input: s = "101023"
 * Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 * Constraints:
 * 0 <= s.length <= 3000
 * s consists of digits only.
 */

import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddress {
  // Backtracking DFS (with StringBuilder). Very fast ~100%
  // Do not worry about DP here, it will just make things more complex and give little in return
  // Goal is to generate restore the IP piece by piece, discarding invalid ip pieces.
  public List<String> restoreIpAddresses(String ip, List<String> ans, int idx, StringBuilder restored, int count) {
    // If more pieces than possible, discard this flow
    if (count > 4) {
      return ans;
    }
    // If all pieces have been extracted, and the entire string has been exhausted - add it to the ans
    if (count == 4 && idx == ip.length()) {
      ans.add(restored.toString());
      return ans;
    }
    int len = restored.length();
    for (int i = 1; i <= 3; i++) {
      if (idx + i > ip.length()) break;
      String s = ip.substring(idx, idx + i); // Extract one piece of ip in one go
      // Discard this piece if it is invalid
      // It is invalid if it has (trailing zero and size != 1) or (value is greater than 255)
      if ((s.charAt(0) == '0' && s.length() > 1) || (i == 3 && Integer.parseInt(s) > 255)) {
        continue;
      }
      // Append value to builder with a ".". Do not append for the last piece.
      restored.append(s).append(count == 3 ? "" : ".");
      // Get the next pieces
      restoreIpAddresses(ip, ans, idx + i, restored, count + 1);
      // Reset size for next iteration
      restored.setLength(len);
    }
    return ans;
  }

  public List<String> restoreIpAddresses2(String s) {
    return restoreIpAddresses(s, new ArrayList<>(), 0, new StringBuilder(), 0);
  }

  // Insane iterative method. This is a finite alternatives
  public List<String> restoreIpAddresses(String s) {
    List<String> ans = new ArrayList<>();
    for (int i = 1; i <= 3; i++) {
      for (int j = 1; j <= 3; j++) {
        for (int k = 1; k <= 3; k++) {
          for (int l = 1; l <= 3; l++) {
            if (i + j + k + l == s.length()) {
              // a.b.c.d
              String a = s.substring(0, i);
              String b = s.substring(i, i + j);
              String c = s.substring(i + j, i + j + k);
              String d = s.substring(i + j + k, i + j + k + l);

              if (((a.charAt(0) != '0' || a.length() == 1) && Integer.parseInt(a) <= 255) &&
                      ((b.charAt(0) != '0' || b.length() == 1) && Integer.parseInt(b) <= 255) &&
                      ((c.charAt(0) != '0' || c.length() == 1) && Integer.parseInt(c) <= 255) &&
                      ((d.charAt(0) != '0' || d.length() == 1) && Integer.parseInt(d) <= 255)
              ) {
                ans.add(a + "." + b + "." + c + "." + d);
              }
            }
          }
        }
      }
    }
    return ans;
  }
}
