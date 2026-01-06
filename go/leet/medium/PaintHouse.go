package main

/*
256. Paint House

https://leetcode.ca/all/256.html

There are a row of n houses, each house can be painted with one of the three colors: red, blue or
green. The cost of painting each house with a certain color is different. You have to paint all the
houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For
example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of
painting house 1 with color green, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Example:

Input: [[17,2,17],[16,16,5],[14,3,19]]
Output: 10
Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue.
             Minimum cost: 2 + 5 + 3 = 10.
*/

type PaintHouse struct {
	dp map[int]int
}

func min(a ...int) int {
	ans := a[0]
	for i := 1; i < len(a); i++ {
		if ans > a[i] {
			ans = a[i]
		}
	}
	return ans
}

func (this *PaintHouse) paintHouse(costs [][]int) int {
	size := len(costs)
	prev, cur := [3]int{}, [3]int{}

	for i := 0; i < size; i++ {
		cur[0] = costs[i][0] + min(prev[1], prev[2])
		cur[1] = costs[i][1] + min(prev[0], prev[2])
		cur[2] = costs[i][2] + min(prev[0], prev[1])
		prev = cur
	}

	return min(cur[0], cur[1], cur[2])
}

/*func main() {
	// red, blue, green
	inp := [][]int{{17, 2, 17}, {16, 16, 5}, {14, 3, 19}}
	solution := PaintHouse{}
	fmt.Println(solution.paintHouse(inp))
}*/
