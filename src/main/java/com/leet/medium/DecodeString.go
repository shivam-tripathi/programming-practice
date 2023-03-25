package main

import (
	"strings"
)

func isDigit(r byte) bool {
	return r >= '0' && r <= '9'
}

type DecodeStringElem struct {
	count int
	sb    *strings.Builder
}

func decodeStringSolve(stack *Stack, s string, pos int, ans *strings.Builder) *strings.Builder {
	if pos == len(s) {
		return ans
	}

	// fmt.Println(stack.String())

	if isDigit(s[pos]) {
		num := 0
		for pos < len(s) && s[pos] != '[' {
			num = num*10 + int(s[pos]-'0')
			pos++
		}
		stack.Push(&DecodeStringElem{num, &strings.Builder{}})
	} else if s[pos] == ']' {
		elem := stack.Pop().(*DecodeStringElem)
		count, str := elem.count, elem.sb.String()
		sb := ans
		if stack.Size() != 0 {
			sb = stack.Peek().(*Elem).sb
		}
		for count > 0 {
			sb.WriteString(str)
			count--
		}
	} else {
		if stack.Size() == 0 {
			ans.WriteByte(s[pos])
		} else {
			stack.Peek().(*Elem).sb.WriteByte(s[pos])
		}
	}
	decodeStringSolve(stack, s, pos+1, ans)
	return ans
}

func decodeString(s string) string {
	stack := &Stack{}
	ans := decodeStringSolve(stack, s, 0, &strings.Builder{})
	return ans.String()
}
