package com.leet.medium;

/**
 * 797. All Paths From Source to Target
 * https://leetcode.com/problems/all-paths-from-source-to-target/
 * Medium
 * Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths from node 0 to node
 * n - 1, and return them in any order.
 * The graph is given as follows: graph[i] is a list of all nodes you can visit from node i (i.e., there is a directed
 * edge from node i to node graph[i][j]).
 * Example 1:
 * Input: graph = [[1,2],[3],[3],[]]
 * Output: [[0,1,3],[0,2,3]]
 * Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
 * Example 2:
 * Input: graph = [[4,3,1],[3,2,4],[3],[4],[]]
 * Output: [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
 * Example 3:
 * Input: graph = [[1],[]]
 * Output: [[0,1]]
 * Example 4:
 * Input: graph = [[1,2,3],[2],[3],[]]
 * Output: [[0,1,2,3],[0,2,3],[0,3]]
 * Example 5:
 * Input: graph = [[1,3],[2],[3],[]]
 * Output: [[0,1,2,3],[0,3]]
 * Constraints:
 * n == graph.length
 * 2 <= n <= 15
 * 0 <= graph[i][j] < n
 * graph[i][j] != i (i.e., there will be no self-loops).
 * The input graph is guaranteed to be a DAG.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllPathsFromSourceToTarget {
  // Memo is bad here, because it increases space cost as well time cost (all answers need to be merged)
  // It will be a good solution if it asked about "number" of paths
  Map<Integer, List<List<Integer>>> dp = new HashMap<>();

  public List<List<Integer>> dfs(int src, int[][] g) {
    if (src == g.length - 1) return List.of(List.of(src));
    if (dp.containsKey(src)) return dp.get(src);
    List<List<Integer>> ans = new ArrayList<>();
    for (int i = 0; i < g[src].length; i++) {
      List<List<Integer>> lis = dfs(g[src][i], g);
      if (lis != null) {
        for (var l : lis) {
          List<Integer> path = new ArrayList<>();
          path.add(src);
          path.addAll(l);
          ans.add(path);
        }
      }
    }
    if (ans.size() == 0) return null;
    dp.put(src, ans);
    return ans;
  }

  // Backtracking - fast 100%
  public List<List<Integer>> dfs(int src, int[][] graph, List<Integer> path, List<List<Integer>> ans) {
    path.add(src);

    if (src == graph.length - 1) {
      ans.add(new ArrayList<>(path));
    } else {
      for (int nextNode : graph[src]) {
        dfs(nextNode, graph, path, ans);
      }
    }
    int size = path.size();
    path.remove(size - 1);
    return ans;
  }

  public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
//    return dfs(0, graph);
    return dfs(0, graph, new ArrayList<>(), new ArrayList<>());
  }
}
