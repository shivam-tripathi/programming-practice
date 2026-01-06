use std::collections::HashMap;

struct Solution {}

impl Solution {
  pub fn len_longest_fib_subseq(arr: Vec<i32>) -> i32 {
    let largest = *arr.iter().max().unwrap();

    let items = &mut HashMap::<i32, usize>::new();
    let mut items_arr = vec![1];
    let (mut size, mut a, mut b) = (1, 1, 1);

    while b < largest {
      items.insert(b, size);
      items_arr.push(b);
      let c = a + b;
      size += 1;
      a = b;
      b = c;
    }

    let mut ans_map = HashMap::<i32, i32>::new();
    let mut ans = 0;

    for num in arr {
      if let Some(idx_ref) = items.get(&num) {
        let idx = *idx_ref;
        let mut value = 1;
        if idx > 0 {
          value = ans_map.get(&items_arr[idx - 1]).or(Some(&0)).unwrap() + 1;
        }
        if value > *ans_map.get(&num).or(Some(&0)).unwrap() {
          ans_map.insert(num, value);
        }
        if value > ans {
          ans = value;
        }
      }
    }
    ans
  }
}

fn main() {
  let input = vec![1, 1, 2, 3, 5, 7, 11, 12, 8, 14, 18, 13];
  println!("{:?}", input);
  let ans = Solution::len_longest_fib_subseq(input);
  println!("ans = {}", ans);
}
