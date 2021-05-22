package main

func canReachUtil(arr []int, pos int, visited []bool) bool {
  if pos < 0 || pos >= len(arr) || visited[pos] {
    return false
  }
  if arr[pos] == 0 {
    return true
  }
  visited[pos] = true
  return canReachUtil(arr, pos + arr[pos], visited) || canReachUtil(arr, pos - arr[pos], visited)
}

func canReach(arr []int, start int) bool {
  visited := make([]bool, len(arr))
  return canReachUtil(arr, start, visited)
}
