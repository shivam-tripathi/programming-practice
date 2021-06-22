package medium

func subsetsSolve(nums []int, pos int, set []int, ans [][]int) [][]int {
	if pos == len(nums) {
		collect := make([]int, len(set))
		copy(collect, set)
		return append(ans, collect)
	}

	ans = subsetsSolve(nums, pos+1, set, ans)
	set = append(set, nums[pos])
	return subsetsSolve(nums, pos+1, set, ans)
}

func subsets(nums []int) [][]int {
	return subsetsSolve(nums, 0, []int{}, [][]int{})
}
