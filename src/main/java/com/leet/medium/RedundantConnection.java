package com.leet.medium;

/**
 * 684. Redundant Connection Medium
 * https://leetcode.com/problems/redundant-connection/
 *
 * In this problem, a tree is an undirected graph that is connected and has no cycles.
 *
 * You are given a graph that started as a tree with n nodes labeled from 1 to n, with one
 * additional edge added. The added edge has two different vertices chosen from 1 to n, and was not
 * an edge that already existed. The graph is represented as an array edges of length n where
 * edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the graph.
 * Return an edge that can be removed so that the resulting graph is a tree of n nodes. If there
 * are multiple answers, return the answer that occurs last in the input.
 *
 * Example 1:
 * Input: edges = [[1,2],[1,3],[2,3]] Output: [2,3]
 *
 * Example 2:
 * Input: edges = [[1,2],[2,3],[3,4],[1,4],[1,5]] Output: [1,4]
 *
 * Constraints:
 * n == edges.length 3 <= n <= 1000 edges[i].length == 2 1 <= ai < bi <= edges.length ai != bi
 * There are no repeated edges. The given graph is connected.
 */
public class RedundantConnection {
  // Recursively try to find the parent of the node
  // A node has not parent when:
  // 1. It has parent equal to itself
  // 2. It's parent is zero - i.e. it has not been processed yet and therefore has not parent
  // It is important to keep in mind here that nodes are 1 indexed
  int getParent(int[] parents, int id) {
    if (parents[id] == 0 || parents[id] == id) return id;
    return getParent(parents, parents[id]);
  }

  public int[] findRedundantConnection(int[][] edges) {
    // This arrays stores the parent for a given node
    int[] parents = new int[edges.length+2];

    for(int[] edge : edges) {
      int a = edge[0], b = edge[1];

      // Get parents of a and b
      int parentA = getParent(parents, a);
      int parentB = getParent(parents, b);

      // Both have same parents - cycle and hence this edge is not required
      if (parentA == parentB) {
        return edge;
      }
      // Both do not have any parents - merge them into a single parent group
      if (parentA == a && parentB == b) {
        parents[a] = a;
        parents[b] = a;
      } else if (parentA == a) {
        // A doesn't have any parent but B has - we assign A the same parent as B
        parents[a] = parentB;
      } else if (parentB == -1) {
        // B doesn't have any parent but A has - we assign B the same parent as A
        parents[b] = parentA;
      } else {
        // A and B have different parents - we merge then by making parent of one of them parent of the other one
        parents[parentB] = parentA;
      }
    }

    // No cycles - no extra edges
    return null;
  }
}

/**
 Mock run
 3,4 -> <-1,-1> {3:0, 4:0}, [-1]
 1,2 -> <-1,-1> {3:0, 4:0, 1:1, 2:1}, [-1, -1]
 2,4 -> <1,0> {3:0, 4:0, 1:1, 2:1}, [1, -1]
 3,5 -> <0,-1> {3:0, 4:0, 1:1, 2:1, 5:0} [1, -1]
 2,5 -> <1,0> <1,1> return
*/
