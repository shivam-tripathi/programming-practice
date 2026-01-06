package main

func reorderedPowerOf2(N int) bool {
	n := int64(N)
	Ndigits := [10]int{}
	for n != 0 {
		Ndigits[n%10]++
		n /= 10
	}

	nums := []int64{}
	prev := int64(1)
	for prev <= int64(1e9+10) {
		nums = append(nums, prev)
		prev <<= 1
	}

	cmpFunc := func(num int64) bool {
		digits := [10]int{}
		for num != 0 {
			digits[num%10]++
			num /= 10
		}
		for i, val := range digits {
			if val-Ndigits[i] != 0 {
				return false
			}
		}
		return true
	}

	for i := len(nums) - 1; i >= 0; i-- {
		if cmpFunc(nums[i]) {
			return true
		}
	}
	return false
}
