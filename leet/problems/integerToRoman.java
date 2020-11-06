/*
Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000

For example, 2 is written as II in Roman numeral, just two one's added together. 12 is written as
XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for
four is not IIII. Instead, the number four is written as IV. Because the one is before the five we
subtract it making four. The same principle applies to the number nine, which is written as IX.
There are six instances where subtraction is used:
    I can be placed before V (5) and X (10) to make 4 and 9.
    X can be placed before L (50) and C (100) to make 40 and 90.
    C can be placed before D (500) and M (1000) to make 400 and 900.

Given an integer, convert it to a roman numeral.

## Example 1:
Input: num = 3
Output: "III"

## Example 2:
Input: num = 4
Output: "IV"

## Example 3:
Input: num = 9
Output: "IX"

## Example 4:
Input: num = 58
Output: "LVIII"
Explanation: L = 50, V = 5, III = 3.

## Example 5:
Input: num = 1994
Output: "MCMXCIV"
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.

## Constraints:
    1 <= num <= 3999
*/

class Solution {
    String convert(int r, char base, char side, char next) {
        switch(r) {
            case 0 : return ""; // zero is not handled, because X, C, M already hold power of ten
            case 1 : return ""+side; // eg I
            case 2 : return ""+side+side; // eg II
            case 3 : return ""+side+side+side; // eg III
            case 4 : return ""+side+base; // eg IV
            case 5 : return ""+base; // eg V
            case 6 : return ""+base+side; // eg VI
            case 7 : return ""+base+side+side; // eg VII
            case 8 : return ""+base+side+side+side; // eg VIII
            case 9 : return ""+side+next; // IX
            default: return "";
        }
    }

    String intToRoman(int num) {
        String ans = "";
        int tensPosition=1;
        while(num != 0) {
            int r = num % 10; // Extract first digit
            switch(tensPosition) { // Based on ten's position
                case 1 : ans = convert(r, 'V', 'I', 'X') + ans; break;
                case 2 : ans = convert(r, 'L', 'X', 'C') + ans; break;
                case 3 : ans = convert(r, 'D', 'C', 'M') + ans; break;
                case 4 : ans = convert(r, '_', 'M', '_') + ans; break;
            }
            num /= 10; // Remove last digit as it's done
            tensPosition++; // Increase for next iteration
        }
        return ans;
    }
}


/*
- For each power of ten representation is same, just the characters differ. For example in power
of 1, characters are I, V and X. For power of 2, characters are L, C and M.
*/