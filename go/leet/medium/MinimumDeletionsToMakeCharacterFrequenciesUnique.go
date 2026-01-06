package main

// 100% faster
func minDeletions2(s string) int {
  count := make([]int, 26)
  for _, ch := range s {
    count[ch-'a']++
  }
  sort.Ints(count)
  ans := 0
  for i := len(count) - 2; i >= 0; i-- {
    if count[i+1] == 0 {
      if count[i] == 0 {
        break
      }
      ans += count[i]
      count[i] = 0
    } else if count[i] >= count[i+1] {
      ans += count[i] - count[i+1] + 1
      count[i] = count[i+1] - 1
    }
  }
  return ans
}

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
