struct Solution {}

impl Solution {
  fn get_diff(a: &[u8], b: &[u8]) -> i32 {
    let mut diff = 0;
    for i in 0..8_usize {
      if a[i] != b[i] {
        diff += 1;
      }
    }
    diff
  }

  pub fn min_mutation(start_gene: String, end_gene: String, bank: Vec<String>) -> i32 {
    if start_gene == end_gene {
      return 0;
    }

    if bank.len() == 0 {
      return -1;
    }

    let candidates = bank.iter().map(|s| s.as_bytes()).collect::<Vec<_>>();

    let mut queue = vec![];

    queue.push(start_gene.as_bytes());
    let target = end_gene.as_bytes();

    let mut seen = vec![false; bank.len()];
    let mut idx = 0_usize;
    let mut steps = 0;

    while idx < queue.len() {
      let size = queue.len();
      while idx < size {
        let cur = queue[idx];
        if Solution::get_diff(cur, target) == 0 {
          return steps;
        }
        idx += 1;
        for i in 0..candidates.len() {
          if !seen[i] && Solution::get_diff(cur, candidates[i]) == 1 {
            queue.push(candidates[i]);
            seen[i] = true;
          }
        }
      }
      steps += 1;
    }

    -1
  }
}

fn main() {
  let bank = vec!["AACCGATT", "AACCGATA", "AAACGATA", "AAACGGTA"]
    .iter()
    .map(|e| e.to_string())
    .collect::<Vec<_>>();
  let start_gene = "AACCGGTT".to_string();
  let end_gene = "AAACGGTA".to_string();

  println!("{}", Solution::min_mutation(start_gene, end_gene, bank));
}
