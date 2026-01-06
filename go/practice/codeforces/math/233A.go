// https://codeforces.com/problemset/problem/233/A
package main

import "fmt"

func main() {
	var n int
	fmt.Scanf("%d", &n)
	if n%2 == 1 {
		fmt.Println(-1)
		return
	}
	for i := 1; i <= n; i++ {
		if i%2 == 1 {
			fmt.Printf("%d", i+1)
		} else {
			fmt.Printf("%d", i-1)
		}
		if i != n {
			fmt.Printf(" ")
		}
	}
}
