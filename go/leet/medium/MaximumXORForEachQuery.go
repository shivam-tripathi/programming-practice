package main

func getMaximumXor(nums []int, maximumBit int) []int {
  max := (1 << maximumBit) - 1
  size, prev := len(nums), max
  ans := make([]int, size)
  for i := 0; i < size; i++ {
    ans[size - i - 1] = nums[i] ^ prev
    prev = ans[size - i - 1]
  }
  return ans
}
