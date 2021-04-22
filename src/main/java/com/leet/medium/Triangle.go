package main

func minInt(a int, b int) int {
  if a <= b {
    return a
  }
  return b
}

func minimumTotal(triangle [][]int) int {
  prev := make([]int, len(triangle) + 1)
  for i := len(triangle) - 1; i >= 0; i-- {
    row := triangle[i]
    cur := make([]int, i + 1)
    for j, elem := range row {
      cur[j] = elem + minInt(prev[j], prev[j+1])
    }
    prev = cur
  }
  return prev[0]
}
