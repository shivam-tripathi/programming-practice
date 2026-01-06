package main

/*
987. Vertical Order Traversal of a Binary Tree
https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
Hard

Given the root of a binary tree, calculate the vertical order traversal of the binary tree.

For each node at position (row, col), its left and right children will be at positions
(row + 1, col - 1) and (row + 1, col + 1) respectively. The root of the tree is at (0, 0).

The vertical order traversal of a binary tree is a list of top-to-bottom orderings for each
column index starting from the leftmost column and ending on the rightmost column. There may
be multiple nodes in the same row and same column. In such a case, sort these nodes by their
values.

Return the vertical order traversal of the binary tree.

Example 1:

Input: root = [3,9,20,null,null,15,7]
Output: [[9],[3,15],[20],[7]]
Explanation:
Column -1: Only node 9 is in this column.
Column 0: Nodes 3 and 15 are in this column in that order from top to bottom.
Column 1: Only node 20 is in this column.
Column 2: Only node 7 is in this column.

Example 2:

Input: root = [1,2,3,4,5,6,7]
Output: [[4],[2],[1,5,6],[3],[7]]
Explanation:
Column -2: Only node 4 is in this column.
Column -1: Only node 2 is in this column.
Column 0: Nodes 1, 5, and 6 are in this column.
          1 is at the top, so it comes first.
          5 and 6 are at the same position (2, 0), so we order them by their value, 5 before 6.
Column 1: Only node 3 is in this column.
Column 2: Only node 7 is in this column.

Example 3:

Input: root = [1,2,3,4,6,5,7]
Output: [[4],[2],[1,5,6],[3],[7]]
Explanation:
This case is the exact same as example 2, but with nodes 5 and 6 swapped.
Note that the solution remains the same since 5 and 6 are in the same location and should be
ordered by their values.

Constraints:
    The number of nodes in the tree is in the range [1, 1000].
    0 <= Node.val <= 1000
*/

import "sort"

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

type LevelNode struct {
	depth int
	node  *TreeNode
}

type Level []*LevelNode

func minInt(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func traverse(node *TreeNode, depth, pos int, levels map[int]Level) int {
	if node == nil {
		// We add one as the column "before" this is probably the minimum level
		// It will not work if we looking to find the "range" of columns, we are not interested in that
		return pos + 1
	}
	levels[pos] = append(levels[pos], &LevelNode{depth, node})
	left, right := pos, pos
	left = traverse(node.Left, depth+1, pos-1, levels)
	right = traverse(node.Right, depth+1, pos+1, levels)
	return minInt(pos, minInt(left, right))
}

func verticalTraversal(root *TreeNode) [][]int {
	// levels store result for a given "column" - which can be negative
	levels := map[int]Level{}
	// min stores the minimum possible depth
	minPossibleColumn := traverse(root, 0, 0, levels)
	ans := make([][]int, len(levels))
	// columnNo is minimum possible column and done is normalized column number (starts with 0)
	curColumn, normalizedColumn, totalColumns := minPossibleColumn, 0, len(levels)
	for normalizedColumn < totalColumns {
		level := levels[curColumn]
		// Final result is weird - we need to sort all nodes on depth and then on value
		sort.Slice(level, func(a, b int) bool {
			levelNode1, levelNode2 := level[a], level[b]
			if levelNode1.depth == levelNode2.depth {
				return levelNode1.node.Val < levelNode2.node.Val
			}
			return levelNode1.depth < levelNode2.depth
		})
		// Collect all values from the sorted list for this level
		resultForLevel := make([]int, len(level))
		for i, nodePos := range level {
			resultForLevel[i] = nodePos.node.Val
		}
		ans[normalizedColumn] = resultForLevel
		curColumn++
		normalizedColumn++
	}
	return ans
}
