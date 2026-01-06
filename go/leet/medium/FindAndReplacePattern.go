package main

func findAndReplacePattern(words []string, pattern string) []string {
  ans := []string{}
  for _, word := range words {
    cm, pm := [27]int{}, [27]int{}
    valid := true
    for i, ch := range word {
      cv, pv := int(ch - 'a' + 1), int(pattern[i] - 'a' + 1)
      if cm[cv] == 0 && pm[pv] == 0 {
        cm[cv], pm[pv] = pv, cv
      } else if cm[cv] != pv || pm[pv] != cv {
        valid = false
        break
      }
    }
    if valid {
      ans = append(ans, word)
    }
  }
  return ans
}
