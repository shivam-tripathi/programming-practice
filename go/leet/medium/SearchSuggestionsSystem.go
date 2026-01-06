package main

import (
	"fmt"
	"sort"
)

type TrieNodeSearchSuggestions struct {
	subnodes [26]*TrieNodeSearchSuggestions
	idxs     []int
}

func NewTrieNodeSearchSuggestions() *TrieNodeSearchSuggestions {
	return &TrieNodeSearchSuggestions{}
}

func (this *TrieNodeSearchSuggestions) insert(word string, idx int) {
	node := this
	for _, ch := range word {
		code := int(ch) - int('a')
		if node.subnodes[code] == nil {
			node.subnodes[code] = NewTrieNodeSearchSuggestions()
		}
		node = node.subnodes[code]
		node.idxs = append(node.idxs, idx)
	}
}

func (this *TrieNodeSearchSuggestions) search(prefix string, products []string) [][]string {
	ans := [][]string{}
	ptr, parent := 0, this
	for ptr < len(prefix) {
		code := int(prefix[ptr]) - int('a')
		if parent != nil && parent.subnodes[code] != nil {
			node := parent.subnodes[code]
			cur, curIdx := []string{}, 0
			for len(cur) < 3 && curIdx < len(node.idxs) {
				cur = append(cur, products[node.idxs[curIdx]])
				curIdx++
			}
			ans = append(ans, cur)
			parent = node
		} else {
			ans = append(ans, []string{})
		}
		fmt.Println(string(prefix[ptr]), parent == nil)
		ptr += 1
	}

	return ans
}

func suggestedProducts(products []string, searchWord string) [][]string {
	sort.Strings(products)
	root := NewTrieNodeSearchSuggestions()
	for idx, product := range products {
		root.insert(product, idx)
	}
	return root.search(searchWord, products)
}
