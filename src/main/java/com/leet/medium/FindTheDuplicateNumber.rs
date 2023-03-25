pub fn solve(nums: &mut Vec<i32>, start: usize, end: usize) -> i32 {
	if start == end {
		return nums[start];
	}

	let pivot: i32 = nums[start];
	let mut split: usize = start;
	for pos in start..(end + 1) {
		if nums[pos] < pivot {
			nums.swap(split, pos);
			split += 1;
		}
	}

	println!("{:?}", nums);
	println!(
		"start={} split={} pivot={} end={}",
		start, split, pivot, end
	);

	if (split as i32) > pivot {
		return solve(nums, start, split - 1);
	}

	return solve(nums, split, end);
}

pub fn find_duplicate(nums: Vec<i32>) -> i32 {
	let mut clone = nums.clone();
	let ans: i32 = solve(&mut clone, 0, nums.len() - 1);
	println!("{}", ans);
	return ans;
}

fn main() {
	// [1,3,4,2,2]
	// [3,1,3,4,2]
	let nums: Vec<i32> = vec![1, 3, 4, 2, 2];
	let ans: i32 = find_duplicate(nums);
	println!("{}", ans);
}
