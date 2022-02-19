/**
2101. Detonate the Maximum Bombs
https://leetcode.com/problems/detonate-the-maximum-bombs/
Medium

You are given a list of bombs. The range of a bomb is defined as the area where its effect can be
felt. This area is in the shape of a circle with the center as the location of the bomb.

The bombs are represented by a 0-indexed 2D integer array bombs where bombs[i] = [xi, yi, ri]. xi
and yi denote the X-coordinate and Y-coordinate of the location of the ith bomb, whereas ri denotes
the radius of its range.

You may choose to detonate a single bomb. When a bomb is detonated, it will detonate all bombs that
lie in its range. These bombs will further detonate the bombs that lie in their ranges.

Given the list of bombs, return the maximum number of bombs that can be detonated if you are allowed
to detonate only one bomb.

Example 1:
Input: bombs = [[2,1,3],[6,1,4]]
Output: 2
Explanation:
The above figure shows the positions and ranges of the 2 bombs.
If we detonate the left bomb, the right bomb will not be affected.
But if we detonate the right bomb, both bombs will be detonated.
So the maximum bombs that can be detonated is max(1, 2) = 2.

Example 2:
Input: bombs = [[1,1,5],[10,10,5]]
Output: 1
Explanation:
Detonating either bomb will not detonate the other bomb, so the maximum number of bombs that can be detonated is 1.

Example 3:
Input: bombs = [[1,2,3],[2,3,1],[3,4,2],[4,5,3],[5,6,4]]
Output: 5
Explanation:
The best bomb to detonate is bomb 0 because:
- Bomb 0 detonates bombs 1 and 2. The red circle denotes the range of bomb 0.
- Bomb 2 detonates bomb 3. The blue circle denotes the range of bomb 2.
- Bomb 3 detonates bomb 4. The green circle denotes the range of bomb 3.
Thus all 5 bombs are detonated.

Constraints:
    1 <= bombs.length <= 100
    bombs[i].length == 3
    1 <= xi, yi, ri <= 105
 */

/**
 * Get distance between two points. This has to be in i64 as the input can be large so as to
 * overflow in i32 size integers.
 */
fn get_distance_between(x1: i64, y1: i64, x2: i64, y2: i64) -> i64 {
  (x1 - x2).pow(2) + (y1 - y2).pow(2)
}

/**
 * We find all nodes connected to origin node provided using dfs.
 */
pub fn dfs(graph: &Vec<Vec<usize>>, node: usize, visited: &mut Vec<bool>) -> i32 {
  if visited[node] {
    return 0;
  }

  visited[node] = true;

  let mut rem = 1;

  for i in 0..graph[node].len() {
    let next = graph[node][i];
    if !visited[next] {
      rem += dfs(&graph, next, visited);
    }
  }

  rem
}

/** Main function */
pub fn maximum_detonation(bombs: Vec<Vec<i32>>) -> i32 {
  let mut graph: Vec<Vec<usize>> = vec![];

  if bombs.is_empty() {
    return 0;
  }

  // First we build the adjacency graph
  for i in 0..bombs.len() {
    let bomb = &bombs[i];
    let (x1, y1, range) = (bomb[0], bomb[1], bomb[2]);
    graph.push(vec![]);
    for j in 0..bombs.len() {
      if i == j {
        continue;
      }
      let (x2, y2) = (bombs[j][0], bombs[j][1]);
      if get_distance_between(x1 as i64, y1 as i64, x2 as i64, y2 as i64) <= (range as i64).pow(2) {
        graph[i].push(j);
      }
    }
  }

  // After we build adjacency graph, we find the largest chunk of connected components starting from
  // each node.
  let mut ans = 0;
  for i in 0..bombs.len() {
    let mut visited = vec![false; bombs.len()];
    ans = std::cmp::max(ans, dfs(&graph, i, &mut visited));
  }

  ans
}

fn main() {
  vec![
    vec![vec![1, 1, 5], vec![10, 10, 5]],
    vec![
      vec![1, 2, 3],
      vec![2, 3, 1],
      vec![3, 4, 2],
      vec![4, 5, 3],
      vec![5, 6, 4],
    ],
    vec![vec![2, 1, 3], vec![6, 1, 4]],
    vec![vec![1, 1, 100000], vec![100000, 100000, 1]],
  ]
  .iter()
  .for_each(|bombs| {
    println!("{:?}", bombs);
    let ans = maximum_detonation(bombs.clone());
    println!("ans={}", ans)
  })
}
