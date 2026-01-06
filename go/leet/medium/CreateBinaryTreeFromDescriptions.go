package main

/*
2196. Create Binary Tree From Descriptions
https://leetcode.com/problems/create-binary-tree-from-descriptions/
Medium

You are given a 2D integer array descriptions where descriptions[i] = [parenti, childi, isLefti]
indicates that parenti is the parent of childi in a binary tree of unique values. Furthermore,
    If isLefti == 1, then childi is the left child of parenti.
    If isLefti == 0, then childi is the right child of parenti.

Construct the binary tree described by descriptions and return its root.

The test cases will be generated such that the binary tree is valid.



Example 1:

Input: descriptions = [[20,15,1],[20,17,0],[50,20,1],[50,80,0],[80,19,1]]
Output: [50,20,80,15,17,19]
Explanation: The root node is the node with value 50 since it has no parent.
The resulting binary tree is shown in the diagram.

Example 2:

Input: descriptions = [[1,2,1],[2,3,0],[3,4,1]]
Output: [1,2,null,null,3,4]
Explanation: The root node is the node with value 1 since it has no parent.
The resulting binary tree is shown in the diagram.

Constraints:
    1 <= descriptions.length <= 104
    descriptions[i].length == 3
    1 <= parenti, childi <= 105
    0 <= isLefti <= 1
    The binary tree described by descriptions is valid.
*/

func createBinaryTree(descriptions [][]int) *TreeNode {
	hm := map[int]*TreeNode{}
	pr := map[int]bool{}
	for _, desc := range descriptions {
		parent, child, isLeft := desc[0], desc[1], desc[2]
		if _, ok := hm[parent]; !ok {
			hm[parent] = &TreeNode{parent, nil, nil}
		}
		if _, ok := hm[child]; !ok {
			hm[child] = &TreeNode{child, nil, nil}
		}
		if isLeft == 1 {
			hm[parent].Left = hm[child]
		} else {
			hm[parent].Right = hm[child]
		}
		if _, ok := pr[parent]; !ok {
			pr[parent] = false
		}
		pr[child] = true
	}
	for node, hasParent := range pr {
		if !hasParent {
			return hm[node]
		}
	}
	return nil
}
