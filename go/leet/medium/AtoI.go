package main

import "math"

func myAtoi(s string) int {
	pos := 0
	for pos < len(s) && s[pos] == ' ' {
		pos++
	}
	neg, cmp, breakpoint, offset := false, math.MaxInt32, math.MaxInt32/10, 7
	if pos < len(s) && (s[pos] == '-' || s[pos] == '+') {
		if s[pos] == '-' {
			neg = true
			cmp = math.MinInt32
			offset = -(cmp % 10)
		}
		pos++
	}

	collect := 0

	// remove trailing zeros as well - this improves performance for leetcode testcases
	for pos < len(s) && s[pos] == '0' {
		pos++
	}

	for pos < len(s) {
		ch := s[pos]
		if ch < '0' || ch > '9' {
			break
		}
		digit := int(ch - '0')
		if collect > breakpoint || (collect == breakpoint && digit > offset) {
			return cmp
		}
		collect = 10*collect + digit
		pos++
	}

	if neg {
		return -collect
	}
	return collect
}
