package main

func abs(a int) int {
	if a < 0 {
		return -a
	}
	return a
}

func shortestWordDistance(words []string, word1 string, word2 string) int {
	posA, posB := -1, -1

	minDistance := len(words)

	for i, word := range words {
		if word == word1 {
			posA = i
		}

		if word == word2 {
			posB = i
		}

		if posA != -1 && posB != -1 {
			dist := abs(posA - posB)
			if dist < minDistance {
				minDistance = dist
			}
		}
	}

	return minDistance
}
