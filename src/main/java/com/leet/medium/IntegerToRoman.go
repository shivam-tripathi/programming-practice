package main

import "strings"

func intToRoman(num int) string {
	sb := strings.Builder{}
	mapper := func(num int, base string, mid string, end string) {
		switch num {
		case 5:
			sb.WriteString(base)
		case 4:
			sb.WriteString(base)
			sb.WriteString(mid)
		case 9:
			sb.WriteString(base)
			sb.WriteString(end)
		default:
			if num < 5 {
				sb.WriteString(strings.Repeat(base, num))
			} else {
				sb.WriteString(mid)
				sb.WriteString(strings.Repeat(base, num-5))
			}
		}
	}

	if num/1000 != 0 {
		mapper(num/1000, "M", "_", "_")
		num %= 1000
	}
	if num/100 != 0 {
		mapper(num/100, "C", "D", "M")
		num %= 100
	}
	if num/10 != 0 {
		mapper(num/10, "X", "L", "C")
		num %= 10
	}
	if num != 0 {
		mapper(num, "I", "V", "X")
	}
	return sb.String()
}
