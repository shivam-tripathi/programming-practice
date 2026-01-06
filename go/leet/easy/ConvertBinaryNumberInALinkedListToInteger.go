package main

func getDecimalValue(head *ListNode) int {
	ans := 0
	for head != nil {
		ans = ans << 1
		ans += head.Val
		head = head.Next
	}
	return ans
}
