package main

type TrieNode struct {
	subNodes [26]*TrieNode
	val      *string
	idx      int
}

func (this *TrieNode) match(word string) *TrieNode {
	node := this
	for i := 0; i < len(word); i++ {
		if node.subNodes[word[i]] == nil {
			return nil
		}
		node = node.subNodes[word[i]]
	}
	return node
}

func AddString(prefixTrie *TrieNode, suffixTrie *TrieNode, word string, idx int) {
	size := len(word)
	for i := 0; i < len(word); i++ {
		if prefixTrie.subNodes[word[i]] == nil {
			prefixTrie.subNodes[word[i]] = &TrieNode{}
		}
		if suffixTrie.subNodes[word[size-i-1]] == nil {
			suffixTrie.subNodes[word[size-i-1]] = &TrieNode{}
		}
		prefixTrie = prefixTrie.subNodes[word[i]]
		suffixTrie = suffixTrie.subNodes[word[size-i-1]]
	}
	prefixTrie.val = &word
	suffixTrie.val = &word
	prefixTrie.idx = idx
	suffixTrie.idx = idx
}

type WordFilter struct {
	words      []string
	prefixTrie *TrieNode
	suffixTrie *TrieNode
}

func Constructor(words []string) *WordFilter {
	wordFilter := WordFilter{}
	wordFilter.words = words
	wordFilter.prefixTrie = &TrieNode{}
	wordFilter.suffixTrie = &TrieNode{}
	for idx, word := range words {
		AddString(wordFilter.prefixTrie, wordFilter.suffixTrie, word, idx+1)
	}
	return &wordFilter
}

func (this *WordFilter) F(prefix string, suffix string) int {
	matchPrefixNode := this.prefixTrie.match(prefix)
	if matchPrefixNode == nil {
		return -1
	}
	matchSuffixNode := this.suffixTrie.match(suffix)
	if matchSuffixNode == nil {
		return -1
	}
	prefixMap := map[int]bool{}
	nodes := []*TrieNode{matchPrefixNode}
	for len(nodes) != 0 {
		tmp := []*TrieNode{}
		for _, node := range nodes {
			for _, subNode := range node.subNodes {
				if subNode != nil {
					tmp = append(tmp, subNode)
				}
			}
			if node.val != nil {
				prefixMap[node.idx] = true
			}
		}
		nodes = tmp
	}
	nodes = []*TrieNode{matchSuffixNode}
	ans, ansWord := 0, ""
	for len(nodes) != 0 {
		tmp := []*TrieNode{}
		for _, node := range nodes {
			for _, subNode := range node.subNodes {
				if subNode != nil {
					tmp = append(tmp, subNode)
				}
			}
			if node.val != nil && prefixMap[node.idx] && len(*node.val) > len(ansWord) {
				ans = node.idx
				ansWord = *node.val
			}
		}
		nodes = tmp
	}
	return ans
}

func main() {

}
