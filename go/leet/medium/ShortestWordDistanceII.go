package main

import "fmt"

/*
244.

Shortest Word Distance II

https://leetcode.com/problems/shortest-word-distance-ii/

Design a class which receives a list of words in the constructor, and implements a method that takes
two words word1 and word2 and return the shortest distance between these two words in the list. Your
method will be called repeatedly many times with different parameters.

Example:
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Input: word1 = “coding”, word2 = “practice”
Output: 3

Input: word1 = "makes", word2 = "coding"
Output: 1

Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
*/

type ShortestWordDistanceII struct {
	words   []string
	mapping map[string][]int
}

func NewShortestWordDistanceII(words []string) ShortestWordDistanceII {
	solution := ShortestWordDistanceII{words, map[string][]int{}}
	for i, word := range words {
		solution.mapping[word] = append(solution.mapping[word], i)
	}
	return solution
}

func (this *ShortestWordDistanceII) getDistance(word1 string, word2 string) int {
	seq1, seq2 := this.mapping[word1], this.mapping[word2]
	pos1, pos2 := 0, 0
	ans := len(words)
	for pos1 < len(seq1) && pos2 < len(seq2) {
		diff := abs(seq1[pos1] - seq2[pos2])
	}
}

func main() {
	words := []string{"practice", "makes", "perfect", "coding", "makes"}
	solution := NewShortestWordDistanceII(words)
	fmt.Println(solution.mapping)
}
