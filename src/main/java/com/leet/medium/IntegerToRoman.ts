/**

Example 1:

Input: num = 3
Output: "III"
Explanation: 3 is represented as 3 ones.

Example 2:

Input: num = 58
Output: "LVIII"
Explanation: L = 50, V = 5, III = 3.

Example 3:

Input: num = 1994
Output: "MCMXCIV"
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 */

import { assertEquals } from "https://deno.land/std/testing/asserts.ts";

const digitToRoman = (
  digit: number,
  start: string,
  mid: string,
  end: string
): string => {
  switch (digit) {
    case 1:
      return start;
    case 2:
      return start + start;
    case 3:
      return start + start + start;
    case 4:
      return start + mid;
    case 5:
      return mid;
    case 6:
      return mid + start;
    case 7:
      return mid + start + start;
    case 8:
      return mid + start + start + start;
    case 9:
      return start + end;
    default:
      return "";
  }
};
const mapping: [string, number][] = [
  ["M", 1000],
  ["D", 500],
  ["C", 100],
  ["L", 50],
  ["X", 10],
  ["V", 5],
  ["I", 1],
];

function intToRoman(num: number): string {
  let ans: string = "";
  for (let i = 0; i < mapping.length; i += 2) {
    const [lowRoman, value] = mapping[i];
    const [midRoman] = mapping[i - 1] || ["", 0];
    const [highRoman] = mapping[i - 2] || ["", 0];

    const digit = Math.floor(num / value);
    num = num % value;
    ans += digitToRoman(digit, lowRoman, midRoman, highRoman);
  }
  return ans;
}

Deno.test({
  name: "intToRoman",
  fn: () => {
    assertEquals(intToRoman(1345), "MCCCXLV");
    assertEquals(intToRoman(3), "III");
    assertEquals(intToRoman(1764), "MDCCLXIV");
  },
});
