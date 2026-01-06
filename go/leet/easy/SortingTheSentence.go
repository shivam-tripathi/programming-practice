package main

import (
	"regexp"
	"strconv"
	"strings"
)

func sortSentence(s string) string {
	words := strings.Split(s, " ")
	sentence := make([]string, len(words))
	rg := regexp.MustCompile(`\d+$`)
	for _, word := range words {
		c := rg.FindAllString(word, -1)[0]
		index, _ := strconv.Atoi(c)
		sentence[index-1] = word[:len(word)-len(c)]
	}
	return strings.Join(sentence, " ")
}
