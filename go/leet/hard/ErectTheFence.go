package main

/*
587. Erect the Fence
https://leetcode.com/problems/erect-the-fence/
Hard

You are given an array trees where trees[i] = [xi, yi] represents the location of a tree in the
garden. You are asked to fence the entire garden using the minimum length of rope as it is expensive.
The garden is well fenced only if all the trees are enclosed.

Return the coordinates of trees that are exactly located on the fence perimeter.

Example 1:
Input: points = [[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]
Output: [[1,1],[2,0],[3,3],[2,4],[4,2]]

Example 2:
Input: points = [[1,2],[2,2],[4,2]]
Output: [[4,2],[2,2],[1,2]]

Constraints:
    1 <= points.length <= 3000
    points[i].length == 2
    0 <= xi, yi <= 100
    All the given points are unique.
*/

/*
Convex hull, solution using Jarvis Algorithm O(n^2)
*/

// Returns whether point q is anticlockwise compared to line p->q
func orientation(p, q, r []int) int {
	return (q[1]-p[1])*(r[0]-q[0]) - (q[0]-p[0])*(r[1]-q[1])
}

// Returns cartesian distance between two points
func distance(a, b []int) int {
	return (a[0]-b[0])*(a[0]-b[0]) + (a[1]-b[1])*(a[1]-b[1])
}

func outerTrees(points [][]int) [][]int {
	size := len(points)

	// If number of points are less than equal to 3, then there is only one result - all of them
	// All points are unique, we can directly return them
	if size <= 3 {
		return points
	}

	// First we try to find the left bottom most point
	leftmost := 0
	for i := 1; i < size; i++ {
		if points[i][0] < points[leftmost][0] ||
			points[i][0] == points[leftmost][0] && points[i][1] < points[leftmost][1] {
			leftmost = i
		}
	}

	cur := leftmost
	// hashset to hold a point only once
	ans, done := [][]int{}, map[int]bool{}

	for true {
		next := (cur + 1) % size

		// First we find the most anti clockwise point
		for i := 0; i < size; i++ {
			if orientation(points[cur], points[i], points[next]) < 0 {
				next = i
			}
		}

		// Next, we add all points which are collinear to the most anti clockwise point
		// At the same time, we try to calculate the furthest point from cur and save it as next
		for i := 0; i < size; i++ {
			o := orientation(points[cur], points[i], points[next])
			if o == 0 {
				d1, d2 := distance(points[cur], points[i]), distance(points[next], points[cur])
				if d1 > d2 {
					next = i
				}
				if !done[i] {
					done[i] = true
					ans = append(ans, points[i])
				}
			}
		}

		cur = next

		// if we are back to the leftmost point, we return
		if cur == leftmost {
			break
		}
	}

	return ans
}

// func main() {
// 	trees := [][]int{{1, 1}, {2, 2}, {2, 0}, {2, 4}, {3, 3}, {4, 2}}
// 	res := outerTrees(trees)
// 	for _, point := range res {
// 		fmt.Print("(", point, ");")
// 	}
// 	fmt.Println()
// }
