package main

func subtractProductAndSum(n int) int {
  prod, sum := 1, 0
  for n != 0 {
    r := n % 10
    n /= 10
    prod *= r
    sum += r
  }
  return prod - sum
}
