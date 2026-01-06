package main

func lengthOfLongestSubstring(s string) int {
	ans, cur, occ := 0, 0, [256]int{}
	for idx, ch := range s {
		if occ[ch] != 0 && occ[ch] >= idx-cur {
			if ans < cur {
				ans = cur
			}
			cur = idx - occ[ch] + 1
		} else {
			cur++
		}
		occ[ch] = idx + 1
	}
	if ans < cur {
		ans = cur
	}
	return ans
}
