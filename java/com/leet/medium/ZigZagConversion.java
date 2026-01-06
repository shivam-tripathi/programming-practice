package com.leet.medium;

/**
6. ZigZag Conversion
https://leetcode.com/problems/zigzag-conversion/
Medium

The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
(you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R

And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number of rows:

string convert(string s, int numRows);

Example 1:
Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"

Example 2:
Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:
P     I    N
A   L S  I G
Y A   H R
P     I

Example 3:
Input: s = "A", numRows = 1
Output: "A"

Constraints:
    1 <= s.length <= 1000
    s consists of English letters (lower-case and upper-case), ',' and '.'.
    1 <= numRows <= 1000
 */

import java.util.ArrayList;
import java.util.List;

public class ZigZagConversion {
	public String convert(String s, int numRows) {
		if (numRows == 1) {
			return s;
		}

		List<StringBuilder> strs = new ArrayList<>();

		for (int i = 0; i < numRows; i++) {
			strs.add(new StringBuilder());
		}

		int pos = 0, incr = 1;
		for (int i = 0; i < s.length(); i++) {
			strs.get(pos).append(s.charAt(i));
			if (pos == 0)
				incr = 1;
			else if (pos == numRows - 1)
				incr = -1;
			pos += incr;
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < numRows; i++) {
			sb.append(strs.get(i));
		}

		return sb.toString();
	}
}
