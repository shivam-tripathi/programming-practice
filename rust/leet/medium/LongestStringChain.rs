use std::collections::{HashMap, VecDeque};

#[derive(Debug)]
struct Elem {
  len: i32,
  word_idx: usize,
}

struct Solution {}

impl Solution {
  pub fn is_valid_step(a: &Vec<char>, b: &Vec<char>) -> bool {
    if b.len() - a.len() > 1 {
      return false;
    }

    let mut a_pos = 0;
    let mut b_pos = 0;
    let mut allowed = true;

    while a_pos < a.len() && b_pos < b.len() {
      if a[a_pos] != b[b_pos] {
        if allowed {
          b_pos += 1;
        } else {
          return false;
        }
        allowed = false;
      } else {
        a_pos += 1;
        b_pos += 1;
      }
    }

    return true;
  }

  pub fn longest_str_chain(words: Vec<String>) -> i32 {
    let mut word_vecs = Vec::new();
    // O(n) * O(chars)
    for i in 0..words.len() {
      let chars = words[i].chars();
      word_vecs.push(chars.collect::<Vec<char>>());
    }
    // O(n)
    let mut ladder: Vec<Vec<usize>> = vec![vec![]; 16 + 1];
    for i in 0..words.len() {
      let word = &words[i];
      ladder[word.len() as usize].push(i);
    }
    let mut ans = 1;
    let mut queue: Vec<Elem> = Vec::new();
    for level in 0..ladder.len() {
      let mut next = Vec::new();
      let step = &ladder[level];

      // if nothing present currently, all running chains close
      if step.len() != 0 {
        for item in queue {
          for word_idx in step {
            if Solution::is_valid_step(&word_vecs[item.word_idx], &word_vecs[*word_idx]) {
              ans = std::cmp::max(ans, item.len + 1);
              next.push(Elem {
                len: item.len + 1,
                word_idx: *word_idx,
              });
            }
          }
        }
      }

      for word_idx in step {
        next.push(Elem {
          len: 1,
          word_idx: *word_idx,
        });
      }
      queue = next;
    }
    ans
  }
}

fn main() {
  let ans = Solution::longest_str_chain(vec![]);
  println!("{}", ans);
}
