package main

func leastBricks(wall [][]int) int {
  width := 0
  count := map[int]int{}
  for _, bricks := range wall {
    pos := 0
    for _, brick := range bricks {
      pos += brick
      count[pos]++
    }
    width = pos
  }
  count[width]=0
  ans := 0
  for _, value := range count {
    if value > ans {
      ans = value
    }
  }
  return len(wall) - ans
}
