package main

func minDeletions(s string) int {
  count := make([]int, 26)
  for _, ch := range s {
    count[ch-'a']++
  }
  sizeMap := map[int]int{}
  sizesMap := map[int]bool{}
  for _, size := range count {
    sizesMap[size] = true
    sizeMap[size]++
  }

  sizes := []int{}
  for size := range sizesMap {
    sizes = append(sizes, size)
  }

  sort.Ints(sizes)


  ans := 0
  for i := max; i > 0; i-- {
    sizeCount := sizeMap[i]
    if sizeCount > 1 {
      ans += sizeCount - 1
      sizeMap[i-1] += sizeCount-1
    }
  }

  return ans
}
