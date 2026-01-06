package main

func singleNumber(nums []int) int {
	dig := [32]int{}
	for _, num := range nums {
		pos := 0
		for pos < 32 {
			dig[pos] += num & 1
			pos++
			num = num >> 1
		}
	}
	var ans int32 = 0
	for i, count := range dig {
		ans = ans | int32(count%3)<<i
	}
	return int(ans)
}
