package main

import "strings"

func findDuplicate(paths []string) [][]string {
	m := map[string][]string{}
	for _, path := range paths {
		files := strings.Split(path, " ")
		prefix := files[0] + "/"
		for i := 1; i < len(files); i++ {
			file := files[i]
			splitArr := strings.Split(file, "(")
			name, content := splitArr[0], splitArr[1]
			m[content] = append(m[content], prefix+name)
		}
	}
	ans := [][]string{}
	for _, val := range m {
		if len(val) > 1 {
			ans = append(ans, val)
		}
	}
	return ans
}
