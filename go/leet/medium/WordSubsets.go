package main

func getCharCount(str string) [26]int {
	chars := [26]int{}
	for _, char := range str {
		chars[char-'a']++
	}
	return chars
}

func wordSubsets(A []string, B []string) []string {
	chars := [26]int{}
	for _, str := range B {
		_chars := getCharCount(str)
		for i := 0; i < 26; i++ {
			if _chars[i] > chars[i] {
				chars[i] = _chars[i]
			}
		}
	}
	ans := []string{}
	for _, str := range A {
		_chars := getCharCount(str)
		isValid := true
		for i := 0; i < 26; i++ {
			if _chars[i] < chars[i] {
				isValid = false
				break
			}
		}
		if isValid {
			ans = append(ans, str)
		}
	}
	return ans
}
