/*
212. Word Search II
https://leetcode.com/problems/word-search-ii
Hard

Given an m x n board of characters and a list of strings words, return all words on the board.

Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are
horizontally or vertically neighboring. The same letter cell may not be used more than once in a
word.

Example 1:
Input:
board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]],
words = ["oath","pea","eat","rain"]
Output: ["eat","oath"]

Example 2:
Input: board = [["a","b"],["c","d"]], words = ["abcb"]
Output: []

Constraints:
    m == board.length
    n == board[i].length
    1 <= m, n <= 12
    board[i][j] is a lowercase English letter.
    1 <= words.length <= 3 * 104
    1 <= words[i].length <= 10
    words[i] consists of lowercase English letters.
    All the strings of words are unique.
*/

struct Solution {}

#[derive(Debug, Default)]
struct Trie {
  children: [Option<Box<Trie>>; 26],
  is_word: bool,
}

impl Solution {
  fn backtrack(
    board: &mut Vec<Vec<char>>,
    trie: &mut Trie,
    pos: (usize, usize),
    s: &mut String,
    ans: &mut Vec<String>,
  ) {
    let c = board[pos.0][pos.1];
    if let Some(node) = &mut trie.children[(c as u8 - b'a') as usize] {
      s.push(c);
      board[pos.0][pos.1] = '*';

      if node.is_word {
        ans.push(s.clone());
        node.is_word = false;
      }

      for d in [0, 1, 0, !0, 0].windows(2) {
        let (i, j) = (pos.0.wrapping_add(d[0]), pos.1.wrapping_add(d[1]));
        if i < board.len() && j < board[0].len() && board[i][j] != '*' {
          Self::backtrack(board, node, (i, j), s, ans);
        }
      }

      board[pos.0][pos.1] = c;
      s.pop();
    }
  }

  pub fn find_words(board: Vec<Vec<char>>, words: Vec<String>) -> Vec<String> {
    let mut board = board;
    let mut trie = Trie::default();
    let mut ans = Vec::new();

    for word in &words {
      let mut node = &mut trie;
      for u in word.bytes() {
        node = node.children[(u - b'a') as usize].get_or_insert(Default::default());
      }
      node.is_word = true;
    }

    for i in 0..board.len() {
      for j in 0..board[0].len() {
        Self::backtrack(&mut board, &mut trie, (i, j), &mut String::new(), &mut ans);
      }
    }

    ans
  }
}

pub fn main() {
  let board: Vec<Vec<char>> = vec![
    vec!['o', 'a', 'a', 'n'],
    vec!['e', 't', 'a', 'e'],
    vec!['i', 'h', 'k', 'r'],
    vec!['i', 'f', 'l', 'v'],
  ];
  let words: Vec<String> = ["oath", "pea", "eat", "rain"]
    .iter()
    .map(|s| s.to_string())
    .collect();
  let ans = Solution::find_words(board.clone(), words.clone());
  print!("board={:?}\nwords={:?}\nans={:?}\n", board, words, ans);
  // let s = [1, 2, 3].iter().chain([4, 5, 6].iter()).collect::Vec<i32>();
}
