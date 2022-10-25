/**
1662. Check If Two String Arrays are Equivalent
https://leetcode.com/problems/check-if-two-string-arrays-are-equivalent/
Easy

Given two string arrays word1 and word2, return true if the two arrays represent the same string,
and false otherwise.
A string is represented by an array if the array elements concatenated in order forms the string.

Example 1:
Input: word1 = ["ab", "c"], word2 = ["a", "bc"]
Output: true
Explanation:
word1 represents string "ab" + "c" -> "abc"
word2 represents string "a" + "bc" -> "abc"
The strings are the same, so return true.

Example 2:
Input: word1 = ["a", "cb"], word2 = ["ab", "c"]
Output: false

Example 3:
Input: word1  = ["abc", "d", "defg"], word2 = ["abcddefg"]
Output: true

Constraints:
	1 <= word1.length, word2.length <= 103
	1 <= word1[i].length, word2[i].length <= 103
	1 <= sum(word1[i].length), sum(word2[i].length) <= 103
	word1[i] and word2[i] consist of lowercase letters.
 */

interface Iterator<T> {
  next(): T;
  has_next(): boolean;
}

class WordsIterator implements Iterator<string> {
  private idx = 0;
  private str_idx = 0;
  constructor(private strings: string[]) {}

  has_next() {
    if (
      this.str_idx < this.strings.length &&
      this.idx >= this.strings[this.str_idx].length
    ) {
      this.str_idx++;
      this.idx = 0;
    }
    return this.str_idx < this.strings.length;
  }

  next() {
    if (
      this.str_idx < this.strings.length &&
      this.idx >= this.strings[this.str_idx].length
    ) {
      this.str_idx++;
      this.idx = 0;
    }
    return this.strings[this.str_idx][this.idx++];
  }
}

function arrayStringsAreEqual(word1: string[], word2: string[]): boolean {
  const it1: Iterator<string> = new WordsIterator(
    word1.filter((w) => w.length),
  );
  const it2: Iterator<string> = new WordsIterator(
    word2.filter((w) => w.length),
  );
  while (it1.has_next() && it2.has_next()) {
    if (it1.next() !== it2.next()) {
      return false;
    }
  }
  return it1.has_next() === false && it2.has_next() === false;
}
