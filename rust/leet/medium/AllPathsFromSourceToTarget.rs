struct Solution {}

impl Solution {
  fn dfs(
    graph: &Vec<Vec<i32>>,
    node: i32,
    idx: usize,
    path: &mut Vec<i32>,
    ans: &mut Vec<Vec<i32>>,
  ) {
    if idx >= path.len() {
      path.push(node);
    } else {
      path[idx] = node;
    }

    if node == graph.len() as i32 - 1 {
      // println!("ans = {:?}", &path[0..=idx]);
      ans.push(path[0..=idx].to_vec().clone());
    } else {
      for &next in graph[node as usize].iter() {
        Self::dfs(graph, next, idx + 1, path, ans);
      }
    }
  }

  pub fn all_paths_source_target(graph: Vec<Vec<i32>>) -> Vec<Vec<i32>> {
    let mut ans = vec![];
    Self::dfs(&graph, 0, 0, &mut vec![], &mut ans);
    ans
  }
}

fn main() {
  let graph = vec![vec![4, 3, 1], vec![3, 2, 4], vec![3], vec![4], vec![]];
  let ans = Solution::all_paths_source_target(graph);
  println!("{:?}", ans);
}
