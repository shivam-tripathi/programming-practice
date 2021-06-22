package com.leet.easy;
import java.util.*;

/**
 * 118. Pascal's Triangle
 * https://leetcode.com/problems/pascals-triangle/
 * Easy
 *
 * Given an integer numRows, return the first numRows of Pascal's triangle.
 * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
 *
 * Example 1:
 * Input: numRows = 5
 * Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 *
 * Example 2:
 * Input: numRows = 1
 * Output: [[1]]
 *
 * Constraints:
 *     1 <= numRows <= 30
 */

public class PascalsTriangle {
  int getVal(List<Integer> row, int pos) {
    if (pos < 0 || pos >= row.size()) return 0;
    return row.get(pos);
  }

  public List<List<Integer>> generate(int numRows) {
    List<List<Integer>> ans = new ArrayList<>();
    ans.add(List.of(1));
    while (ans.size() < numRows) {
      var prev = ans.get(ans.size() - 1);
      var next = new ArrayList<Integer>();
      for (int i = 0; i <= prev.size(); i++) {
        next.add(getVal(prev, i - 1) + getVal(prev, i));
      }
      ans.add(next);
    }
    return ans;
  }
}
