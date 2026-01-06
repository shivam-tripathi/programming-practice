package main

import "strings"

func removeDuplicates(s string) string {
	stack := make([]rune, len(s))
	size := 0
	for _, char := range s {
		if size == 0 || stack[size-1] != char {
			stack[size] = char
			size++
		} else {
			for size > 0 && stack[size-1] == char {
				size--
			}
		}
	}
	sb := strings.Builder{}
	for i := 0; i < size; i++ {
		sb.WriteRune(stack[i])
	}
	return sb.String()
}
