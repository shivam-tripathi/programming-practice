use std::collections::HashMap;

struct Solution {}

impl Solution {
  pub fn close_strings(word1: String, word2: String) -> bool {
    if word1.len() != word2.len() {
      return false;
    }

    let mut rev_map_a: HashMap<char, i32> = HashMap::new();
    let mut rev_map_b: HashMap<char, i32> = HashMap::new();

    let chars_1 = word1.chars().collect::<Vec<char>>();
    let chars_2 = word2.chars().collect::<Vec<char>>();

    for i in 0..chars_1.len() {
      let rev_1 = *rev_map_a.get(&chars_1[i]).unwrap_or(&-1);
      let rev_2 = *rev_map_b.get(&chars_2[i]).unwrap_or(&-1);
      if rev_1 != -1 && rev_2 != -1 {
        let rev_char_1 = chars_1[rev_1 as usize];
        let rev_char_2 = chars_2[rev_2 as usize];
        if rev_char_1 != rev_char_2 {
          return false;
        }
      } else if rev_1 == -1 && rev_2 == -1 {
      }
      if rev_1 == -1 && rev_2 == -1 {
        rev_map_a.insert(chars_1[i], i as i32);
        rev_map_b.insert(chars_2[i], i as i32);
      }
    }

    return true;
  }
}

fn main() {
  println!("hello");
}
