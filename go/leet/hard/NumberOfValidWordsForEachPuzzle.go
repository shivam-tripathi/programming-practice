package main

/*
1178. Number of Valid Words for Each Puzzle

https://leetcode.com/problems/number-of-valid-words-for-each-puzzle/

Hard
With respect to a given puzzle string, a word is valid if both the following conditions are satisfied:

    word contains the first letter of puzzle.
    For each letter in word, that letter is in puzzle.
        For example, if the puzzle is "abcdefg", then valid words are "faced", "cabbage", and
				"baggage", while invalid words are "beefed" (does not include 'a') and "based" (includes 's'
				which is not in the puzzle).

Return an array answer, where answer[i] is the number of words in the given word list words that is
valid with respect to the puzzle puzzles[i].

Example 1:
Input: words = ["aaaa","asas","able","ability","actt","actor","access"], puzzles = ["aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"]
Output: [1,1,3,2,4,0]
Explanation:
1 valid word for "aboveyz" : "aaaa"
1 valid word for "abrodyz" : "aaaa"
3 valid words for "abslute" : "aaaa", "asas", "able"
2 valid words for "absoryz" : "aaaa", "asas"
4 valid words for "actresz" : "aaaa", "asas", "actt", "access"
There are no valid words for "gaswxyz" cause none of the words in the list contains letter 'g'.

Example 2:
Input:
words = ["apple","pleas","please"],
puzzles = ["aelwxyz","aelpxyz","aelpsxy","saelpxy","xaelpsy"]
Output: [0,1,3,2,0]

Constraints:
    1 <= words.length <= 105
    4 <= words[i].length <= 50
    1 <= puzzles.length <= 104
    puzzles[i].length == 7
    words[i] and puzzles[i] consist of lowercase English letters.
    Each puzzles[i] does not contain repeated characters.
*/

type Trie struct {
	subnodes [26]*Trie
	leaf     int
}

func NewTrie(words []string) *Trie {
	trie := &Trie{}
	for _, word := range words {
		chars := [26]bool{}
		for _, char := range word {
			chars[int(char-'a')] = true
		}
		node := trie
		for i, found := range chars {
			if !found {
				continue
			}
			if node.subnodes[i] == nil {
				node.subnodes[i] = &Trie{}
			}
			node = node.subnodes[i]
		}
		node.leaf++
	}
	return trie
}

type Puzzle struct {
	first  int
	status int
}

func getStatus(word string) int {
	status := 0
	for _, char := range word {
		status = status | (1 << int(char-'a'))
	}
	return status
}

func NewPuzzle(puzzle string) *Puzzle {
	return &Puzzle{(1 << (puzzle[0] - 'a')), getStatus(puzzle)}
}

func findNumOfValidWords(words []string, puzzles []string) []int {
	puzz := make([]*Puzzle, len(puzzles))
	ans := make([]int, len(puzzles))

	for i, puzzle := range puzzles {
		puzz[i] = NewPuzzle(puzzle)
	}

	wordMap := map[string]int{}
	for _, word := range words {
		wordMap[word]++
	}

	for word, count := range wordMap {
		status := getStatus(word)
		for i, puzzle := range puzz {
			if puzzle.status&status == status && puzzle.first&status != 0 {
				ans[i] += count
			}
		}
	}

	return ans
}
