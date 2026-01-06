/**
1971. Find if Path Exists in Graph
https://leetcode.com/problems/find-if-path-exists-in-graph/

There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1
(inclusive). The edges in the graph are represented as a 2D integer array edges, where each
edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi. Every vertex pair
is connected by at most one edge, and no vertex has an edge to itself.

You want to determine if there is a valid path that exists from vertex source to vertex destination.

Given edges and the integers n, source, and destination, return true if there is a valid path from
source to destination, or false otherwise.

Example 1:
Input: n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
Output: true
Explanation: There are two paths from vertex 0 to vertex 2:
- 0 → 1 → 2
- 0 → 2

Example 2:
Input: n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], source = 0, destination = 5
Output: false
Explanation: There is no path from vertex 0 to vertex 5.

Constraints:
  1 <= n <= 2 * 105
  0 <= edges.length <= 2 * 105
  edges[i].length == 2
  0 <= ui, vi <= n - 1
  ui != vi
  0 <= source, destination <= n - 1
  There are no duplicate edges.
  There are no self edges.
 */

pub struct DisjointStruct {
  root: Vec<usize>,
  rank: Vec<usize>,
}

impl DisjointStruct {
  pub fn new(capacity: usize) -> Self {
    Self {
      root: (0..capacity).map(|e| e).collect(),
      rank: vec![0; capacity],
    }
  }

  pub fn union(&mut self, x: usize, y: usize) {
    let x_parent = self.find(x);
    let y_parent = self.find(y);
    if x_parent != y_parent {
      if self.rank[x_parent] < self.rank[y_parent] {
        self.root[x_parent] = y_parent;
      } else if self.rank[x_parent] > self.rank[y_parent] {
        self.root[y_parent] = x_parent;
      } else {
        self.root[y_parent] = x_parent;
        self.rank[x_parent] += 1;
      }
    }
  }

  pub fn find(&mut self, x: usize) -> usize {
    if x == self.root[x] {
      return x;
    }
    self.root[x] = self.find(self.root[x]);
    self.root[x]
  }
}

struct Solution {}
impl Solution {
  pub fn valid_path(n: i32, edges: Vec<Vec<i32>>, source: i32, destination: i32) -> bool {
    let mut ds = DisjointStruct::new(n as usize);
    for edge in edges {
      let (x, y) = (edge[0], edge[1]);
      ds.union(x as usize, y as usize);
    }
    ds.find(source as usize) == ds.find(destination as usize)
  }
}

fn main() {
  println!(
    "{}",
    Solution::valid_path(
      6,
      vec![
        vec![0, 1],
        vec![0, 2],
        vec![3, 5],
        vec![5, 4],
        vec![4, 3],
        vec![2, 4]
      ],
      0,
      5
    )
  );
}
