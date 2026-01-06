package main

/*
310. Minimum Height Trees
https://leetcode.com/problems/minimum-height-trees/
Medium

A tree is an undirected graph in which any two vertices are connected by exactly one path. In other
words, any connected graph without simple cycles is a tree.

Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] =
[ai, bi] indicates that there is an undirected edge between the two nodes ai and bi in the tree, you
can choose any node of the tree as the root. When you select a node x as the root, the result tree
has height h. Among all possible rooted trees, those with minimum height (i.e. min(h))  are called
minimum height trees (MHTs).

Return a list of all MHTs' root labels. You can return the answer in any order.
The height of a rooted tree is the number of edges on the longest downward path between the root and
a leaf.

Example 1:
Input: n = 4, edges = [[1,0],[1,2],[1,3]]
Output: [1]
Explanation: As shown, the height of the tree is 1 when the root is the node with label 1 which is
the only MHT.

Example 2:
Input: n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
Output: [3,4]

Example 3:
Input: n = 1, edges = []
Output: [0]

Example 4:
Input: n = 2, edges = [[0,1]]
Output: [0,1]

Constraints:
    1 <= n <= 2 * 104
    edges.length == n - 1
    0 <= ai, bi < n
    ai != bi
    All the pairs (ai, bi) are distinct.
    The given input is guaranteed to be a tree and there will be no repeated edges.
*/
func FindMinHeightTreesUtil() func(n int, edges [][]int) []int {
	// Top level node object which saves details for each node
	type Node struct {
		id      int         // id for the node
		heights map[int]int // The maximum heights from connected nodes (excluding path to this node)
		adj     []int       // Adjacency list for this nodes
		ans     int         // Height for the subtree rooted at this node
	}

	// Core utility function. It finds the maximum height among all it's connected nodes except the
	// one connecting it to the input parent id.
	var findMinHeightTreesUtil func(id int, parent int, cache []*Node) int
	findMinHeightTreesUtil = func(id, parent int, cache []*Node) int {
		ans := 0
		for _, to := range cache[id].adj {
			// ignore parent edge
			if to != parent {
				// check if length has been calculated to `to` from id. If not, compute it and cache it.
				// It is important to note here that there will be no cycles so no infinite loops.
				if _, ok := cache[id].heights[to]; !ok {
					cache[id].heights[to] = 1 + findMinHeightTreesUtil(to, id, cache)
				}
				if ans < cache[id].heights[to] {
					ans = cache[id].heights[to]
				}
			}
		}
		return ans
	}

	// Main function which will be finally run
	findMinHeightTrees := func(n int, edges [][]int) []int {
		cache := make([]*Node, n) // Dp object

		// Create nodes
		for i := 0; i < n; i++ {
			cache[i] = &Node{i, map[int]int{}, []int{}, 0}
		}

		// Create adj graph
		for _, edge := range edges {
			a, b := edge[0], edge[1]
			cache[a].adj = append(cache[a].adj, b)
			cache[b].adj = append(cache[b].adj, a)
		}

		// Loop over all nodes and calculate each one's height (this is fast because we cache
		// individual heights)
		min := n
		for i := 0; i < n; i++ {
			cache[i].ans = findMinHeightTreesUtil(i, -1, cache)
			if min > cache[i].ans {
				min = cache[i].ans
			}
		}

		// Finally record all nodes with minimum heights
		res := []int{}
		for nodeId, node := range cache {
			if node.ans == min {
				res = append(res, nodeId)
			}
		}

		return res
	}

	return findMinHeightTrees
}
