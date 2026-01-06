/**
1834. Single-Threaded CPU
https://leetcode.com/problems/single-threaded-cpu/
Medium

You are given n​​​​​​ tasks labeled from 0 to n - 1 represented by a 2D integer array tasks, where
tasks[i] = [enqueueTimei, processingTimei] means that the i​​​​​​th​​​​ task will be available to process at
enqueueTimei and will take processingTimei to finish processing.

You have a single-threaded CPU that can process at most one task at a time and will act in the
following way:

    If the CPU is idle and there are no available tasks to process, the CPU remains idle.
    If the CPU is idle and there are available tasks, the CPU will choose the one with the shortest
    processing time. If multiple tasks have the same shortest processing time, it will choose the
    task with the smallest index.
    Once a task is started, the CPU will process the entire task without stopping.
    The CPU can finish a task then start a new one instantly.

Return the order in which the CPU will process the tasks.

Example 1:
Input: tasks = [[1,2],[2,4],[3,2],[4,1]]
Output: [0,2,3,1]
Explanation: The events go as follows:
- At time = 1, task 0 is available to process. Available tasks = {0}.
- Also at time = 1, the idle CPU starts processing task 0. Available tasks = {}.
- At time = 2, task 1 is available to process. Available tasks = {1}.
- At time = 3, task 2 is available to process. Available tasks = {1, 2}.
- Also at time = 3, the CPU finishes task 0 and starts processing task 2 as it is the shortest. Available tasks = {1}.
- At time = 4, task 3 is available to process. Available tasks = {1, 3}.
- At time = 5, the CPU finishes task 2 and starts processing task 3 as it is the shortest. Available tasks = {1}.
- At time = 6, the CPU finishes task 3 and starts processing task 1. Available tasks = {}.
- At time = 10, the CPU finishes task 1 and becomes idle.

Example 2:
Input: tasks = [[7,10],[7,12],[7,5],[7,4],[7,2]]
Output: [4,3,2,0,1]
Explanation: The events go as follows:
- At time = 7, all the tasks become available. Available tasks = {0,1,2,3,4}.
- Also at time = 7, the idle CPU starts processing task 4. Available tasks = {0,1,2,3}.
- At time = 9, the CPU finishes task 4 and starts processing task 3. Available tasks = {0,1,2}.
- At time = 13, the CPU finishes task 3 and starts processing task 2. Available tasks = {0,1}.
- At time = 18, the CPU finishes task 2 and starts processing task 0. Available tasks = {1}.
- At time = 28, the CPU finishes task 0 and starts processing task 1. Available tasks = {}.
- At time = 40, the CPU finishes task 1 and becomes idle.

Constraints:
  tasks.length == n
  1 <= n <= 10^5
  1 <= enqueueTimei, processingTimei <= 109
 */
use std::cmp::Reverse;
use std::collections::BinaryHeap;

struct Solution {}

impl Solution {
  pub fn get_order(inp: Vec<Vec<i32>>) -> Vec<i32> {
    // tasks, sorted by {enqueue_time, processing_time, task_idx}
    let mut tasks = (0..inp.len())
      .map(|idx| (inp[idx][0], inp[idx][1], idx))
      .collect::<Vec<(i32, i32, usize)>>();
    tasks.sort();

    let mut ans = vec![];
    let mut timer = 0;
    let mut idx = 0;
    let mut heap = BinaryHeap::new();

    while idx < tasks.len() || heap.len() > 0 {
      // if heap is empty, we jump the timer to next task in line
      if heap.len() == 0 && idx < tasks.len() {
        timer = timer.max(tasks[idx].0);
      }
      // enqueue all task which are eligible to be processed at this point in time
      while idx < tasks.len() && timer >= tasks[idx].0 {
        // every eligible task is sorted lowest to highest based on {processing_time, task_idx}
        let elem = (Reverse(tasks[idx].1), Reverse(tasks[idx].2), idx);
        heap.push(elem);
        idx += 1;
      }
      // the current task to be processed
      let processed = heap.pop().unwrap();
      // sorted_task_idx
      let processed_idx = processed.2;
      // task_idx
      ans.push(tasks[processed_idx].2 as i32);
      // timer at the end of this task
      timer += tasks[processed_idx].1;
    }

    ans
  }
}

fn main() {
  let input = vec![
    vec![19, 13], // 0
    vec![16, 9],  // 1
    vec![21, 10], // 2
    vec![32, 25], // 3
    vec![37, 4],  // 4
    vec![49, 24], // 5
    vec![2, 15],  // 6
    vec![38, 41], // 7
    vec![37, 34], // 8
    vec![33, 6],  // 9
    vec![45, 4],  // 10
    vec![18, 18],
    vec![46, 39],
    vec![12, 24],
  ];
  println!("{:?}", Solution::get_order(input));
}
