struct Solution {}

impl Solution {
  pub fn dfs(adj: &Vec<Vec<usize>>, cur_idx: usize, color: i32, colors: &mut Vec<i32>) -> bool {
    if colors[cur_idx] != -1 && colors[cur_idx] != color {
      return false;
    }

    if colors[cur_idx] == color {
      return true;
    }

    colors[cur_idx] = color;
    let next_color = if color == 0 { 1 } else { 0 };

    for i in 0..adj[cur_idx].len() {
      let next_idx = adj[cur_idx][i];
      if !Solution::dfs(adj, next_idx, next_color, colors) {
        return false;
      }
    }

    true
  }

  pub fn possible_bipartition(n: i32, dislikes: Vec<Vec<i32>>) -> bool {
    let mut adj: Vec<Vec<usize>> = vec![];
    for i in 0..=n {
      adj.push(vec![]);
    }
    for edge in dislikes {
      let (a, b) = (edge[0] as usize, edge[1] as usize);
      adj[a].push(b);
      adj[b].push(a);
    }
    let mut colors = vec![-1; (n + 1) as usize];
    for i in 1..=(n as usize) {
      if colors[i] == -1 && !Solution::dfs(&adj, i, 0, &mut colors) {
        return false;
      }
    }
    true
  }
}
