package main

func findDuplicates(nums []int) []int {
  ans := []int{}
  mask := ^math.MinInt32
  for _, n := range nums {
    idx := (n & mask) - 1
    if nums[idx] < 0 {
      ans = append(ans, idx+1)
    } else {
      nums[idx] = nums[idx] | math.MinInt32
    }
  }
  return ans
}
