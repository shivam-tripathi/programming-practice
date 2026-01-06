package main

type Permute struct {
	last int
	nums []int
	ans  [][]int
}

func (this *Permute) solve(ans []int, done []bool, pos int, state int) {
	if state == this.last {
		clone := make([]int, len(this.nums))
		copy(clone, ans)
		this.ans = append(this.ans, clone)
		return
	}

	for idx, num := range this.nums {
		if !done[idx] {
			done[idx] = true
			ans[pos] = num
			this.solve(append(ans, num), done, pos+1, state|(1<<idx))
			done[idx] = false
		}
	}
}

func permute(nums []int) [][]int {
	p := Permute{
		last: (1 << len(nums)) - 1,
		nums: nums,
		ans:  [][]int{},
	}
	p.solve(make([]int, len(nums)), make([]bool, len(nums)), 0, 0)
	return p.ans
}
