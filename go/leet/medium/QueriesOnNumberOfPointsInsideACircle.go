package main

func countPoints(points [][]int, queries [][]int) []int {
  arr := make([]int, len(queries))
  for idx, query := range queries {
    ans, rDist := 0, query[2] * query[2]
    for _, point := range points {
      xDist, yDist := point[0] - query[0], point[1] - query[1]
      if xDist*xDist + yDist*yDist <= rDist {
        ans++
      }
    }
    arr[idx] = ans
  }
  return arr
}
