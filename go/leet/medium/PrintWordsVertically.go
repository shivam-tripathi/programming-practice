package main

/**

1324. Print Words Vertically

https://leetcode.com/problems/print-words-vertically/

Medium

Given a string s. Return all the words vertically in the same order in which they appear in s.
Words are returned as a list of strings, complete with spaces when is necessary. (Trailing spaces
are not allowed). Each word would be put on only one column and that in one column there will be
only one word.

Example 1:
Input: s = "HOW ARE YOU"
Output: ["HAY","ORO","WEU"]
Explanation: Each word is printed vertically.
 "HAY"
 "ORO"
 "WEU"

Example 2:
Input: s = "TO BE OR NOT TO BE"
Output: ["TBONTB","OEROOE","   T"]
Explanation: Trailing spaces is not allowed.
"TBONTB"
"OEROOE"
"   T"

Example 3:
Input: s = "CONTEST IS COMING"
Output: ["CIC","OSO","N M","T I","E N","S G","T"]

Constraints:
    1 <= s.length <= 200
    s contains only upper case English letters.
    It's guaranteed that there is only one space between 2 words.
*/

import "strings"

func printVertically(s string) []string {
	words := strings.Split(s, " ")

	// for every vertical column, we need to hit size of maxLengthRight (no trailing spaces)
	max, maxLenRight := 0, make([]int, len(words))

	// We find the length of the longest word, and the max length to the right of each word
	prevLen := 0
	for i := len(words) - 1; i >= 0; i-- {
		word := words[i]
		if len(word) > max {
			max = len(word)
		}
		if len(word) > prevLen {
			maxLenRight[i] = len(word)
			prevLen = len(word)
		} else {
			maxLenRight[i] = prevLen
		}
	}

	// We cannot construct dynamic slice of strings.Builder and copying strings.Builder
	// fails. So we have to statically contruct it.
	vert := make([]strings.Builder, max)

	// Process each word
	for i := 0; i < len(words); i++ {
		word := words[i]

		// Write all runes
		for j, ch := range word {
			vert[j].WriteRune(ch)
		}

		// Add spaces if required (some character will occur after these spaces)
		for j := len(word); j < maxLenRight[i]; j++ {
			vert[j].WriteString(" ")
		}
	}

	// Reconstruct all strings
	ans := make([]string, len(vert))
	for i, sb := range vert {
		ans[i] = sb.String()
	}

	return ans
}
