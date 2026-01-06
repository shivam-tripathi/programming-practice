fn brute_force(arr: &mut Vec<i32>) -> i32 {
  let mut ans = 0;
  for i in 0..arr.len() {
    for j in (i + 1)..arr.len() {
      if arr[i] > arr[j] {
        ans += 1;
      }
    }
  }
  ans
}

fn merge_sort_util(arr: &mut Vec<i32>, left: usize, right: usize) -> i32 {
  let mut inversion_count = 0;
  let mid = left + (right - left) / 2;

  if right > left && mid != left {
    inversion_count += merge_sort_util(arr, left, mid);
    inversion_count += merge_sort_util(arr, mid, right);

    let mut tmp: Vec<i32> = vec![0; right - left];
    let mut ptr: usize = 0;

    let (mut i, mut j) = (left, mid);

    while i < mid && j < right {
      if arr[i] < arr[j] {
        tmp[ptr] = arr[i];
        i += 1;
      } else {
        tmp[ptr] = arr[j];
        j += 1;
        inversion_count += (mid - i) as i32;
      }
      ptr += 1;
    }

    while i < mid {
      tmp[ptr] = arr[i];
      i += 1;
      ptr += 1;
    }

    while j < right {
      tmp[ptr] = arr[j];
      j += 1;
      ptr += 1;
    }

    for i in 0..ptr {
      arr[left + i] = tmp[i];
    }
  }

  inversion_count
}

fn enhanced_merge_sort(arr: &mut Vec<i32>) -> i32 {
  let ans = merge_sort_util(arr, 0, arr.len());
  println!("{:?}", arr);
  ans
}

fn main() {
  for arr in vec![vec![1, 5, 4, 3, 2]] {
    let brute_force_ans = brute_force(&mut arr.clone());
    let enhanded_merge_sort_ans = enhanced_merge_sort(&mut arr.clone());
    println!(
      "#{:?}\n\tbrute_force {}\n\tenhanced_merge_sort {}",
      arr, brute_force_ans, enhanded_merge_sort_ans
    )
  }
}
