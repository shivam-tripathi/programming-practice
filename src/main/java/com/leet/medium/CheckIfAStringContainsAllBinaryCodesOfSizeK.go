package main

import (
	"fmt"
	"strings"
)

// 1<<1 => 2,  1<<2 => 4,  1<<3 => 8,  1<<4 => 16

func checkIfAStringContainsAllBinaryCodesOfSizeK(s string, k int) bool {
	limit := 1 << k
	if len(s) < k {
		return false
	}
	for i := limit - 1; i >= 0; i++ {
		bin := fmt.Sprintf("%b", i)
		str := strings.Repeat("0", k-len(bin)) + bin
		if strings.Index(s, str) == -1 {
			return false
		}
	}
	return true
}
