package main

func threeSumMulti(arr []int, target int) int {
	size, mod := len(arr), int(1e9+7)
	twoSumCount := make([]map[int]int, size+1)
	ans := 0

	// for i, there exists a j such that j > i -> arr[i] + arr[j] => map rooted at index i
	for i := size - 2; i >= 0; i-- {
		twoSumCount[i] = map[int]int{}
		for j := i + 1; j < size; j++ {
			sum := arr[i] + arr[j]
			twoSumCount[i][sum]++
		}
		diff := target - arr[i]
		for j := i + 1; j < size; j++ {
			ans = (ans%mod + twoSumCount[j][diff]%mod) % mod
		}
	}
	return ans
}

func threeSumMulti2(arr []int, target int) int {
	size, mod := len(arr), int(1e9+7)
	twoSumCount := make([][201]int, size)
	ans := 0

	for i := size - 2; i >= 0; i-- {
		for j := i + 1; j < size; j++ {
			sum := arr[i] + arr[j]
			twoSumCount[i][sum]++
		}
		diff := target - arr[i]
		for j := i + 1; j < size; j++ {
			if diff < 0 || diff > 200 {
				continue
			}
			ans = (ans%mod + twoSumCount[j][diff]%mod) % mod
		}
	}
	return ans
}

func threeSumMulti3(arr []int, target int) int {
	size, mod, twoSumCount, ans := len(arr), int(1e9+7), [201]int{}, 0

	for i := size - 1; i >= 0; i-- {
		diff := target - arr[i]
		if diff >= 0 && diff < 201 {
			ans = (ans%mod + twoSumCount[diff]%mod) % mod
		}
		for j := i + 1; j < size; j++ {
			sum := arr[i] + arr[j]
			twoSumCount[sum]++
		}
	}

	return ans
}
