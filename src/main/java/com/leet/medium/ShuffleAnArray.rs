struct Solution {
	nums: Vec<i32>,
}

use rand::Rng;

/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl Solution {
	fn new(nums: Vec<i32>) -> Self {
		Solution { nums }
	}

	fn reset(&self) -> Vec<i32> {
		self.nums.clone()
	}

	fn shuffle(&self) -> Vec<i32> {
		let mut arr = self.nums.clone();
		for i in 0..self.nums.len() {
			let random = rand::thread_rng().gen_range(i..arr.len());
			println!("{} {} {}", i, random, arr.len());
			arr.swap(i, random);
		}
		arr
	}
}

/**
 * Your Solution object will be instantiated and called as such:
 * let obj = Solution::new(nums);
 * let ret_1: Vec<i32> = obj.reset();
 * let ret_2: Vec<i32> = obj.shuffle();
 */
fn main() {
	let v: Vec<i32> = (0..10_i32).map(|e| e).collect();
	let obj = Solution::new(v);
	let ret_1: Vec<i32> = obj.reset();
	println!("{:?}", ret_1.len());
	let ret_2: Vec<i32> = obj.shuffle();
	println!("{:?}", ret_2.len());
}
