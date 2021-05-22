package main

func sumZero(n int) []int {
  low, mid, high := 0, n / 2, n - 1
  ans := make([]int, n)
  for low <= high {
    ans[low] = mid * -1
    ans[high] = mid
    low, mid, high = low+1, mid-1, high-1
  }
  return ans
}
