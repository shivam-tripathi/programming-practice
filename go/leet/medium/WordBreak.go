package main

// 0ms, 100%

var dp []int

type TrieNode struct {
  nodes [26]*TrieNode
  val string
  leaf bool
}

var root *TrieNode

func (trieNode *TrieNode) addWord(word string) {
  node := trieNode
  for _, char := range word {
    if node.nodes[char-'a'] == nil {
      node.nodes[char-'a'] = &TrieNode{}
    }
    node = node.nodes[char-'a']
  }
  node.leaf = true
  node.val = word
}

func wordBreakUtil(s string, pos int) bool {
  if pos >= len(s) {
    return true
  }
  if dp[pos] != 0 {
    return dp[pos] == 1
  }
  node := root
  ans := false
  for i := pos; i < len(s); i++ {
    char := s[i]
    if node.nodes[char-'a'] == nil {
      break
    }
    node = node.nodes[char-'a']
    if node.leaf {
      ans = wordBreakUtil(s, i+1)
    }
    if ans {
      break
    }
  }
  if ans {
    dp[pos] = 1
  } else {
    dp[pos] = -1
  }
  return ans
}

func wordBreak(s string, wordDict []string) bool {
  dp = make([]int, len(s))
  root = &TrieNode{}
  for _, word := range wordDict {
    root.addWord(word)
  }
  return wordBreakUtil(s, 0)
}
