use std::{collections::HashMap, str::Chars, vec};

#[derive(Debug)]
struct TrieNode {
  children: HashMap<char, TrieNode>,
  is_word: bool,
}

impl TrieNode {
  pub fn new() -> Self {
    TrieNode {
      children: HashMap::new(),
      is_word: false,
    }
  }

  pub fn insert(&mut self, ch: char) -> &mut TrieNode {
    self.children.insert(ch, TrieNode::new());
    self.children.get_mut(&ch).unwrap()
  }
}

#[derive(Debug)]
struct Trie {
  root: TrieNode,
}

impl Trie {
  fn new() -> Self {
    Trie {
      root: TrieNode::new(),
    }
  }

  pub fn insert(&mut self, word: &str) {
    let mut node = &mut self.root;
    for c in word.chars() {
      if node.children.contains_key(&c) {
        node = node.children.get_mut(&c).unwrap();
      } else {
        node = node.insert(c);
      }
    }
  }

  pub fn search(&mut self, word: &str) -> bool {
    let mut node = &self.root;
    for c in word.chars() {
      if !node.children.contains_key(&c) {
        return false;
      }
      node = node.children.get(&c).unwrap();
    }
    true
  }
}

struct Solution {}

impl Solution {
  pub fn find_substring(s: String, words: Vec<String>) -> Vec<i32> {
    let root = &mut Trie::new();
    for word in words.iter() {
      root.insert(&word);
    }
    let ans = vec![];
    ans
  }
}

fn main() {
  let res = Solution::find_substring(
    "hello".to_string(),
    vec![
      "hello".to_string(),
      "word".to_string(),
      "world".to_string(),
      "name".to_string(),
    ],
  );
}
