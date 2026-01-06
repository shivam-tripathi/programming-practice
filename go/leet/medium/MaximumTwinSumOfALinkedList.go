package main

/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func pairSumUtil(node *ListNode, head *ListNode) (int, *ListNode) {
	if node == nil {
		return 0, head
	}
	ans, compl := pairSumUtil(node.Next, head)
	if ans < (node.Val + compl.Val) {
		ans = node.Val + compl.Val
	}
	return ans, compl.Next
}

func pairSum(head *ListNode) int {
	ans, _ := pairSumUtil(head, head)
	return ans
}
