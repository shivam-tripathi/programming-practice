package com.leet.easy;

/**
 * 1108. Defanging an IP Address
 * https://leetcode.com/problems/defanging-an-ip-address/
 * Easy
 *
 * Given a valid (IPv4) IP address, return a defanged version of that IP address.
 *
 * A defanged IP address replaces every period "." with "[.]".
 *
 *
 *
 * Example 1:
 *
 * Input: address = "1.1.1.1"
 * Output: "1[.]1[.]1[.]1"
 *
 * Example 2:
 *
 * Input: address = "255.100.50.0"
 * Output: "255[.]100[.]50[.]0"
 *
 *
 *
 * Constraints:
 *
 *     The given address is a valid IPv4 address.
 *
 * Accepted
 * 334,157
 * Submissions
 * 377,715
 */

public class DefangingAnIPAddress {
  public String defangIPaddr(String address) {
    var sb = new StringBuilder();
    for (char ch : address.toCharArray()) {
      if (ch == '.') sb.append("[.]");
      else sb.append(ch);
    }
    return sb.toString();
  }
}
