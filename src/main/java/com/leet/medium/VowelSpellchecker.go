package main

import (
	"fmt"
	"unicode"
)

func isVowel(m byte) bool {
	return m == 'a' || m == 'e' || m == 'i' || m == 'o' || m == 'u' || m == 'A' || m == 'E' || m == 'I' || m == 'O' || m == 'U'
}

type TrieNode struct {
	subNodes []*TrieNode
	word     string
}

type Trie struct {
	root         *TrieNode
	getIdx       func(byte) int
	subNodesSize int
}

func (trie *Trie) NewTrieNode() *TrieNode {
	trieNode := &TrieNode{}
	trieNode.subNodes = make([]*TrieNode, trie.subNodesSize)
	return trieNode
}

func NewTrie(getIdx func(byte) int, subNodesSize int) *Trie {
	trie := &Trie{}
	trie.subNodesSize = subNodesSize
	trie.root = trie.NewTrieNode()
	trie.getIdx = getIdx
	return trie
}

func flip(char rune) rune {
	if unicode.IsLower(char) {
		return unicode.ToUpper(char)
	}
	return unicode.ToLower(char)
}

func (trie *Trie) insert(words []string, getIdx func(byte) int) {
	for _, word := range words {
		node := trie.root
		for i := 0; i < len(word); i++ {
			idx := getIdx(word[i])
			if node.subNodes[idx] == nil {
				node.subNodes[idx] = trie.NewTrieNode()
			}
			node = node.subNodes[idx]
		}
		if node.word == "" {
			node.word = word
		}
	}
}

func (trie *Trie) insertDefault(words []string) {
	trie.insert(words, trie.getIdx)
}

func (trie *Trie) match(word string, getIdx func(byte) int) string {
	node := trie.root
	for i := 0; i < len(word); i++ {
		idx := getIdx(word[i])
		if node.subNodes[idx] == nil {
			return ""
		}
		node = node.subNodes[idx]
	}
	return node.word
}

func (trie *Trie) matchDefault(word string) string {
	return trie.match(word, trie.getIdx)
}

func spellchecker(wordlist []string, queries []string) []string {
	words := NewTrie(func(ch byte) int {
		switch {
		case ch >= 'a':
			return int(ch - 'a' + 26)
		default:
			return int(ch - 'A')
		}
	}, 26+26)
	lowerWords := NewTrie(func(ch byte) int {
		switch {
		case ch >= 'a':
			return int(ch - 'a')
		default:
			return int(ch - 'A')
		}
	}, 26+1)

	words.insertDefault(wordlist)
	lowerWords.insertDefault(wordlist)
	mangledIdx := func(ch byte) int {
		switch {
		case isVowel(ch):
			return 26
		case ch >= 'a':
			return int(ch - 'a')
		default:
			return int(ch - 'A')
		}
	}
	lowerWords.insert(wordlist, mangledIdx)

	ans := []string{}

	for _, query := range queries {
		if matched := words.matchDefault(query); matched != "" {
			ans = append(ans, matched)
		} else if matched := lowerWords.matchDefault(query); matched != "" {
			ans = append(ans, matched)
		} else {
			ans = append(ans, lowerWords.match(query, mangledIdx))
		}
	}
	return ans
}

func main() {
	ins := []string{"KiTe", "kite", "hare", "Hare"}
	match := []string{"kite", "Kite", "KiTe", "Hare", "HARE", "Hear", "hear", "keti", "keet", "keto"}
	fmt.Printf("%#v\n", ins)
	fmt.Printf("%#v\n", match)
	fmt.Printf("%#v\n", spellchecker(ins, match))
}
