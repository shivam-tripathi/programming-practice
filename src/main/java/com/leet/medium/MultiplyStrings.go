package main

func AddChars(a, b byte, carry int, sb []byte) ([]byte, int) {
	x, y := int(a-'0'), int(b-'0')
	sum := x + y + carry
	base := sum % 10
	carry = sum / 10
	sb = append(sb, byte(base+'0'))
	return sb, carry
}

func Add(a, b string) string {
	carry := 0
	i, j := len(a)-1, len(b)-1
	ans := []byte{}
	for i >= 0 && j >= 0 {
		ans, carry = AddChars(a[i], b[j], carry, ans)
		i--
		j--
	}
	for i >= 0 {
		ans, carry = AddChars(a[i], '0', carry, ans)
		i--
	}
	for j >= 0 {
		ans, carry = AddChars(b[j], '0', carry, ans)
		j--
	}

	if carry != 0 {
		ans, _ = AddChars('0', '0', carry, ans)
	}

	for i := 0; i < len(ans); i++ {
		ans[i], ans[len(ans)-i-1] = ans[len(ans)-i-1], ans[i]
	}

	return string(ans)
}
