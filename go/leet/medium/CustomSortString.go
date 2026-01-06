package main

func customSortString(order string, str string) string {
	m := map[byte]int{}
	for i := 0; i < len(order); i++ {
		ch := order[i]
		m[ch] = 0
	}

	count := 0
	for i := 0; i < len(str); i++ {
		ch := str[i]
		if _, ok := m[ch]; ok {
			m[ch]++
			count++
		}
	}

	pos, i := 0, 0
	res := make([]byte, len(str))
	for i < len(str) && pos < len(order) {
		for pos < len(order) && m[order[pos]] == 0 {
			pos++
		}

		if pos >= len(order) {
			break
		}

		if _, ok := m[str[i]]; ok {
			res[i] = order[pos]
			m[order[pos]]--
			i++
		} else {
			res[i] = str[i]
			i++
		}
	}

	for i < len(str) {
		res[i] = str[i]
		i++
	}

	return string(res)
}
