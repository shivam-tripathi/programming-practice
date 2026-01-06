package main

import (
	"fmt"
	"math"
)

func myPow(x float64, n int) float64 {
	if x == float64(1) {
		return 1
	}
	base := x
	if n < 0 {
		base = 1 / x
		n *= -1
	}
	ans, pow := float64(1), n
	for pow != 0 {
		if pow&1 == 1 {
			ans = ans * base
		}
		pow >>= 1
		base = base * base
	}
	return ans
}

func main() {
	fmt.Println(myPow(2, 31), math.Pow(2, 31))
}
