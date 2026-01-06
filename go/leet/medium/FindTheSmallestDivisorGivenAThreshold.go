func smallestDivisor(nums []int, threshold int) int {
	low, high := 1, int(1e6)+1
	// O(log n)
	for low < high {
		mid := low + (high-low)/2
		sum := 0
		// O(n)
		for _, num := range nums {
			sum += int(math.Ceil(float64(num) / float64(mid)))
		}
		if sum <= threshold {
			high = mid
		} else {
			low = mid + 1
		}
	}
	return int(low)
}
