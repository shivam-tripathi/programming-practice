package main

type Subsets struct{}

func (this *Subsets) subsetsSolve(nums []int, pos int, set []int, ans [][]int) [][]int {
	if pos == len(nums) {
		collect := make([]int, len(set))
		copy(collect, set)
		return append(ans, collect)
	}

	ans = this.subsetsSolve(nums, pos+1, set, ans)
	set = append(set, nums[pos])
	return this.subsetsSolve(nums, pos+1, set, ans)
}

func (this *Subsets) subsets(nums []int) [][]int {
	return this.subsetsSolve(nums, 0, []int{}, [][]int{})
}
