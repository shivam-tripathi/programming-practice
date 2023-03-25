package main

import "strings"

type Elem struct {
	char  rune
	count int
}

// k is never 1
func removeDuplicates(s string, k int) string {
	stack := make([]*DecodeStringElem, len(s))
	size := 0
	for _, char := range s {
		if size == 0 || char != stack[size-1].char {
			stack[size] = &DecodeStringElem{char, 1}
			size++
		} else {
			if stack[size-1].count+1 == k {
				size--
			} else {
				stack[size-1].count++
			}
		}
	}

	sb := strings.Builder{}
	for i := 0; i < size; i++ {
		for count := 0; count < stack[i].count; count++ {
			sb.WriteRune(stack[i].char)
		}
	}

	return sb.String()
}
